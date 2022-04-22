package br.edu.ifpb.dac.falacampus.presentation.control;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
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

import br.edu.ifpb.dac.falacampus.business.service.AnswerService;
import br.edu.ifpb.dac.falacampus.business.service.CommentConverterService;
import br.edu.ifpb.dac.falacampus.business.service.CommentService;
import br.edu.ifpb.dac.falacampus.business.service.DepartamentService;
import br.edu.ifpb.dac.falacampus.business.service.UserService;
import br.edu.ifpb.dac.falacampus.model.entity.Answer;
import br.edu.ifpb.dac.falacampus.model.entity.Comment;
import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.model.entity.User;
import br.edu.ifpb.dac.falacampus.model.repository.CommentRepository;
import br.edu.ifpb.dac.falacampus.presentation.dto.CommentDto;
import br.edu.ifpb.dac.falacampus.presentation.dto.DetailsCommentDto;
import br.edu.ifpb.dac.falacampus.presentation.dto.UserDto;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	private CommentConverterService commentConverterService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DepartamentService departamentService;
	
	@Autowired
	private CommentRepository commentRepository;// ver essa injeção para o método detail

	@Autowired
	private ModelMapper mapper;
	
	//SAVE
	@PostMapping
	public ResponseEntity save(@RequestBody CommentDto dto) {
		try {
			Comment entity = commentConverterService.dtoToComment(dto);
			entity = commentService.create(entity);
			dto = commentConverterService.commentToDTO(entity);

			return new ResponseEntity(dto, HttpStatus.CREATED);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		
	}
	


//	@GetMapping("{id}")
//	public ResponseEntity<DetailsCommentDto> detail(@PathVariable Long id) {
//		Optional<Comment> comment = commentRepository.findById(id);
//		if(comment.isPresent()) {
//			return ResponseEntity.ok(new DetailsCommentDto(comment.get()));
//		}
//		
//		return ResponseEntity.notFound().build();
//		// Ver esse método
//	}

	/*
	 * @PutMapping("{id}") public ResponseEntity update(@PathVariable("id") Long
	 * id, @RequestBody CommentDto dto) {
	 * 
	 * 
	 * try { dto.setId(id);
	 * 
	 * Long answerId = dto.getId();
	 * 
	 * Answer answer = answerService.findById(answerId); if (answer == null) { throw
	 * new
	 * IllegalStateException(String.format("Cound not find any comment with id=%1",
	 * id)); }
	 * 
	 * Comment entity = commentConverterService.dtoToComment(dto);
	 * entity.setAnswer(answer); entity = commentService.update(entity);
	 * dto=commentConverterService.commentToDTO(entity);
	 * 
	 * } catch (Exception e) { return
	 * ResponseEntity.badRequest().body(e.getMessage()); }
	 * 
	 * 
	 * }
	 */

	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		try {
			commentService.deleteById(id);

			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping
	public ResponseEntity findByFilter(@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "message", required = false) String message) {
		return null;
		// completar
	}
	
	private CommentDto mapToCommentDto(Comment comment) {
		return mapper.map(comment, CommentDto.class);
	}

	//FIND ALL
	@GetMapping("/all")
	public ResponseEntity<?> findAll() throws Exception {

		List<CommentDto> dtos = commentService.findAll()
				.stream()
				.map(this::mapToCommentDto)
				.toList();
		
		return ResponseEntity.ok(dtos);

	}

	//FIND BY ID
	@GetMapping("{id}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable Long id) {
		Comment comment = commentService.findById(id);
		if (comment != null) {
			CommentDto commentDto = new CommentDto();
			BeanUtils.copyProperties(comment, commentDto);
			return ResponseEntity.ok(commentDto);
		}

		return ResponseEntity.notFound().build();
	}

}
