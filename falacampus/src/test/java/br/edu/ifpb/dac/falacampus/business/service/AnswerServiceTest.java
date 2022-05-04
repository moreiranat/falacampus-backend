package br.edu.ifpb.dac.falacampus.business.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifpb.dac.falacampus.model.entity.Answer;
import br.edu.ifpb.dac.falacampus.model.entity.Comment;
import br.edu.ifpb.dac.falacampus.model.entity.User;
import br.edu.ifpb.dac.falacampus.model.repository.AnswerRepository;

class AnswerServiceTest {
	
	@Mock
	private AnswerRepository answerRepository;
	
	@Mock
	AnswerService answerService = new AnswerService();
	
	@Mock
	private List<Answer> answers;
	
	@BeforeEach
	public void before() {
		this.answerRepository =  mock(AnswerRepository.class);
		this.answers =  mock(List.class);		
	}

	@Test
	void testFindAllService() {
		
		when(answerRepository.findAll()).thenReturn(answers);		
		when(answers.size()).thenReturn(10);		
		assertEquals(10, answers.size());
		verify(answerRepository.findAll()).size();//saber se o método findAll foi chamado durante
												//a execução do teste
	}
	
	@Test
	void testDeleteById() {		
		assertThrows(IllegalStateException.class, 
				() -> answerService.deleteById(null));
	}
	
	@Test
	void testFindById () { 
		
		assertThrows(IllegalStateException.class,
				() -> answerService.findById(null));
	}
	
	@Test
	void testUpate() {
		when(answers.get(0)).thenReturn(new Answer(1l, "teste1", new Comment(), new User()));
		when(answerRepository.save(any(Answer.class))).thenReturn(new Answer(1l, "updateteste1", new Comment(), new User()));
		
		String updateMessage = "updateteste1";
		
		Answer answer = answers.get(0);
		answer.setMessage(updateMessage); 		
		
		Answer answerUpdate = answerRepository.save(answer);
		String message = answerUpdate.getMessage();
		
		assertEquals(updateMessage, answerUpdate.getMessage()); 
		
	}

}
