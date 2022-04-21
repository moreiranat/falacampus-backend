package br.edu.ifpb.dac.falacampus.presentation.control;

import java.util.List;

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

import br.edu.ifpb.dac.falacampus.business.service.AnswerConverterService;
import br.edu.ifpb.dac.falacampus.business.service.AnswerService;
import br.edu.ifpb.dac.falacampus.business.service.CommentService;
import br.edu.ifpb.dac.falacampus.business.service.DepartamentService;
import br.edu.ifpb.dac.falacampus.business.service.UserService;
import br.edu.ifpb.dac.falacampus.model.entity.Answer;
import br.edu.ifpb.dac.falacampus.presentation.dto.AnswerDto;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {
	
	@Autowired
	private AnswerConverterService answerConverterService;
	@Autowired
	private AnswerService answerService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	@Autowired
	private DepartamentService departamentService;
	
	@PostMapping
	public ResponseEntity save(@RequestBody AnswerDto dto) {
		return null;
		
	}
	
	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody AnswerDto dto) {
		return null;
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		return null;
		
	}
	
	@GetMapping
	public ResponseEntity find (
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "message", required = false) String message
		) {
			return null;
		//completar
	}
	
	@GetMapping("/all")
	public List<Answer> findAll() throws Exception {
		return null;
		
	}
	
	@GetMapping("{id}")
    public ResponseEntity<AnswerDto> getAnswerById(@PathVariable Long id) {
		return null;
       
    }

}
