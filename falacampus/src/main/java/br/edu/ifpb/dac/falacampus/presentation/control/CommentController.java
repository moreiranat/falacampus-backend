package br.edu.ifpb.dac.falacampus.presentation.control;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.dac.falacampus.business.service.CommentConverterService;
import br.edu.ifpb.dac.falacampus.business.service.CommentService;
import br.edu.ifpb.dac.falacampus.business.service.DetailsCommentConverterService;
import br.edu.ifpb.dac.falacampus.configuration.ConfigPagination;
import br.edu.ifpb.dac.falacampus.exceptions.CommentCannotUpdateException;
import br.edu.ifpb.dac.falacampus.exceptions.NotFoundException;
import br.edu.ifpb.dac.falacampus.model.entity.Comment;
import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.model.entity.User;
import br.edu.ifpb.dac.falacampus.model.enums.StatusComment;
import br.edu.ifpb.dac.falacampus.model.repository.CommentRepository;
import br.edu.ifpb.dac.falacampus.presentation.dto.CommentDto;
import br.edu.ifpb.dac.falacampus.presentation.dto.DetailsCommentDto;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	private CommentConverterService commentConverterService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private DetailsCommentConverterService detailsCommentConverterService;
	
//	@Autowired
//	private DetailsCommentDto detailsCommentDto;
	
	@Autowired
	private ModelMapper mapper;


	// SAVE
	@PostMapping
	public ResponseEntity save(@RequestBody @Valid DetailsCommentDto dto) {
		try {
			Comment entity = detailsCommentConverterService.dtoToDetailsComment(dto);
						
			entity = commentService.save(entity);
					
			dto = detailsCommentConverterService.detailsCommentToDTO(entity);

			return new ResponseEntity(dto, HttpStatus.CREATED);

	} catch (Exception e) {
		
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}

	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody @Valid DetailsCommentDto dto) {

		try {
			dto.setId(id);
			Comment entity = detailsCommentConverterService.dtoToDetailsComment(dto); 
			
			if (entity.getStatusComment().equals(StatusComment.NOT_SOLVED)){
				entity = commentService.update(entity);
			} else {
				throw new CommentCannotUpdateException();
			}
			
			dto = detailsCommentConverterService.detailsCommentToDTO(entity); 

			return ResponseEntity.ok(dto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Long id, @RequestBody @Valid DetailsCommentDto dto) {
		try {
			
			dto.setId(id);
			Comment entity = detailsCommentConverterService.dtoToDetailsComment(dto);
			
			if (entity.getStatusComment().equals(StatusComment.NOT_SOLVED)){
				commentService.deleteById(id);
			} else {
				throw new CommentCannotUpdateException();
			}
			 

			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	//FIND BY FILTER
	@GetMapping
	public ResponseEntity findByFilter (
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "message", required = false) String message,
			@RequestParam(value = "creationDate", required = false) LocalDateTime creationDate) {
		
		try {

			Comment filter = new Comment();
			filter.setId(id);
			filter.setTitle(title);
			filter.setMessage(message);
			filter.setCreationDate(creationDate);
			
			List<Comment> entities = commentService.find(filter);
			List<CommentDto> dtos = commentConverterService.commentToDTOList(entities);
			return ResponseEntity.ok(dtos);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}

	private DetailsCommentDto mapToDetailsCommentDto(Comment comment) {
		return mapper.map(comment, DetailsCommentDto.class);
	}

	// FIND ALL
	@GetMapping("/all")
	public ResponseEntity<?> findAll() throws Exception {

		List<DetailsCommentDto> dtos = commentService.findAll().stream().map(this::mapToDetailsCommentDto).toList();

		return ResponseEntity.ok(dtos);

	}
	
	
	@GetMapping("/{id}")
	public Comment findById(@PathVariable("id") Long id) throws Exception {

		Comment result = commentService.findById(id);

		if (result == null){
			throw new Exception("Comment not exist!");

		} else {
			return result;	
		}
	}
	
//	@GetMapping(value = "/sortByName")
//	public ResponseEntity<List<Comment>> findOrderByName() throws Exception {
//
//		List<Comment> result = commentService.findAll(Sort.by(Sort.Direction.ASC, "name"));
//
//		return ResponseEntity.ok(result);
//
//	}
	
	@GetMapping("/pages")
	public ResponseEntity<Page<DetailsCommentDto>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size

			){
		
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<DetailsCommentDto> list = commentService.findByPage(pageRequest);

		return ResponseEntity.ok(list);

	}
	
	@GetMapping("/pagesAll")
	private ConfigPagination readAll(@PageableDefault(sort="id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable){

	    Page<Comment> commentPage = commentRepository.findAll(pageable);
	    return new ConfigPagination(commentPage);
	}
	
}
