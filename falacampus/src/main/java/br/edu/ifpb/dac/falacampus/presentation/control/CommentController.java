package br.edu.ifpb.dac.falacampus.presentation.control;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.dac.falacampus.business.service.CommentConverterService;
import br.edu.ifpb.dac.falacampus.business.service.CommentService;
import br.edu.ifpb.dac.falacampus.business.service.DetailsCommentConverterService;
import br.edu.ifpb.dac.falacampus.model.entity.Comment;
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
	private DetailsCommentConverterService detailsCommentConverterService;
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
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody @Valid CommentDto dto) {

		try {
			dto.setId(id);
			Comment entity = commentConverterService.dtoToComment(dto); 
			entity = commentService.update(entity);
			dto = commentConverterService.commentToDTO(entity); 

			return ResponseEntity.ok(dto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		try {
			commentService.deleteById(id);

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

	// ESSE MÉTODO TORNOU-SE DESNECESSÁRIO, O MÉTODO FINDBYFILTER RESOLVE ESSA PESQUISA
	
//	@GetMapping("{id}")
//	public ResponseEntity<CommentDto> getCommentById(@PathVariable Long id) {
//		Comment comment = commentService.findById(id);
//		if (comment != null) {
//			CommentDto commentDto = new CommentDto();
//			BeanUtils.copyProperties(comment, commentDto);
//			return ResponseEntity.ok(commentDto);
//		}
//
//		return ResponseEntity.notFound().build();
//	}

}
