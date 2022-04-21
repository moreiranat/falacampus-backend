package br.edu.ifpb.dac.falacampus.presentation.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.edu.ifpb.dac.falacampus.model.entity.Comment;
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
	private AnswerService answerService;
	@Autowired
	private UserService userService;
	@Autowired
	private DepartamentService departamentService;
	@Autowired
	private CommentRepository commentRepository;// ver essa injeção para o método detail
	
	@PostMapping
	public ResponseEntity save(@RequestBody CommentDto dto) {
		return null;
		
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
	
	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody CommentDto dto) {
		return null;
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		return null;
		
	}
	
	@GetMapping
	public ResponseEntity find (
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "message", required = false) String message
		) {
			return null;
		//completar
	}
	
	@GetMapping("/all")
	public List<Comment> findAll() throws Exception {
		return null;
		
	}
	
	@GetMapping("{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long id) {
		return null;
       
    }

}
