package br.edu.ifpb.dac.falacampus.presentation.control;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import br.edu.ifpb.dac.falacampus.business.service.AnswerConverterService;
import br.edu.ifpb.dac.falacampus.business.service.AnswerService;
import br.edu.ifpb.dac.falacampus.business.service.CommentService;
import br.edu.ifpb.dac.falacampus.business.service.UserService;

import br.edu.ifpb.dac.falacampus.business.service.impl.AnswerConverterServiceImpl;
import br.edu.ifpb.dac.falacampus.business.service.impl.DetailsCommentConverterServiceImpl;
import br.edu.ifpb.dac.falacampus.business.service.impl.UserServiceImpl;
import br.edu.ifpb.dac.falacampus.exceptions.CommentCannotUpdateException;
import br.edu.ifpb.dac.falacampus.model.entity.Answer;
import br.edu.ifpb.dac.falacampus.model.entity.Comment;
import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.model.entity.User;
import br.edu.ifpb.dac.falacampus.model.enums.CommentType;
import br.edu.ifpb.dac.falacampus.model.enums.StatusComment;
import br.edu.ifpb.dac.falacampus.model.repository.AnswerRepository;
import br.edu.ifpb.dac.falacampus.model.repository.CommentRepository;
import br.edu.ifpb.dac.falacampus.model.repository.UserRepository;
import br.edu.ifpb.dac.falacampus.presentation.dto.AnswerDto;
import br.edu.ifpb.dac.falacampus.presentation.dto.DetailsCommentDto;


class AnswerControllerTestIntegration {
	
	@InjectMocks
	private static AnswerController answerController;
	@InjectMocks
	private static AnswerService answerService;
	@InjectMocks
	private static AnswerConverterService answerConverter;
	@Mock
	private static AnswerRepository answerRepository;
	@Mock
	private static CommentRepository commentRepository;
	@Mock
	private static UserRepository userRepository;
	@Mock
	private static UserService userCrudService; 
	@Mock
	private static CommentService commentService;
	@Captor
	private ArgumentCaptor<Answer> captureAnswer = ArgumentCaptor.forClass(Answer.class);;
	@Captor
	private ArgumentCaptor<Comment> captureComment = ArgumentCaptor.forClass(Comment.class);;

	private static Answer answer;
	private static AnswerDto answerDto;
	private static Comment comment;
	private static User user;
	private ResponseEntity responseEntity;

	@BeforeEach
	public void beforeach() {
		MockitoAnnotations.openMocks(this);
	}
	
	@BeforeAll
	public static void setUp() {
		
		answerController = new AnswerController();
		answerService = new AnswerService();
		answerConverter = new AnswerConverterServiceImpl();
		comment = new Comment();
		commentService = new CommentService();
		//userCrudService = new UserCrudServiceImpl()
		ReflectionTestUtils.setField(answerService, "answerRepository", answerRepository);
		ReflectionTestUtils.setField(answerController, "ans werService", answerService);
		ReflectionTestUtils.setField(answerController, "answerConverter", answerConverter);
		ReflectionTestUtils.setField(userCrudService, "userRepository", userRepository);
		ReflectionTestUtils.setField(answerController, "userService", userCrudService);
		ReflectionTestUtils.setField(commentService, "commentRepository", commentRepository);
		ReflectionTestUtils.setField(answerController, "commentService", commentService);
		
		user = new User();
		user.setId(1L);
		comment = new Comment();
		comment.setId(1L);
		
		answer = new Answer();
		answer.setId(1L);
		answer.setMessage("Qual o prazo para realizar reparo do telhado.");
		answer.setCreationDate(LocalDateTime.now());
		answer.setAuthor(user);
		answer.setComment(comment);
		
		answerDto = new AnswerDto();
		answerDto.setId(1L);
		answerDto.setMessage("Qual o prazo para realizar reparo do telhado.");
		answerDto.setCreationDate(LocalDateTime.now());
		answerDto.setAuthorId(1L);
		answerDto.setCommentId(1L);
		
	}

	@Test
	public void saveAnswerDataBase() {
		try {
			
			responseEntity = answerController.save(answerDto);
			Mockito.verify(answerService).save(captureAnswer.capture());
			Answer answerData = captureAnswer.getValue();
			
			Mockito.verify(commentService).update(captureComment.capture());
			Comment commentData = captureComment.getValue();

			assertAll("Test for save Answer using AnswerDto and Answer.",
					() -> assertEquals(answerDto.getId(), answerData.getId()),
					() -> assertEquals(answerDto.getMessage(), answerData.getMessage()),
					() -> assertEquals(answerDto.getCreationDate(), answerData.getCreationDate()),
					() -> assertEquals(answerDto.getAuthorId(), answerData.getAuthor().getId()),
					() -> assertEquals(answerDto.getCommentId(), answerData.getComment().getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void saveAnswer() {
		try {

			Mockito.when(answerService.save(Mockito.any())).thenReturn(answer);
			responseEntity = answerController.save(answerDto);

			assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
			
			Mockito.verify(answerService, Mockito.times(1)).save(captureAnswer.capture());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void answerIllegalStateException() {
		
		try {
			responseEntity = answerController.save(answerDto);
			Mockito.when(answerService.save(answer)).thenThrow(new IllegalStateException());
			String a = String.valueOf(responseEntity.getBody());
			Answer answerData = captureAnswer.getValue();
			
			assertAll("Not found Entity", 
					() -> assertTrue(a.contains("Cound not find any comment with id=%1")),
					() -> assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void answerIllegalStateExceptionWhenCommentIsNull() {
		
		try {
			responseEntity = answerController.save(answerDto);
			Mockito.when(answerService.save(answer)).thenThrow(new IllegalStateException());
			String a = String.valueOf(responseEntity.getBody());
			Answer answerData = captureAnswer.getValue();
			
			assertAll("Case: commentId is null", 
					() -> assertTrue(a.contains("commentId cannot be null")),
					() -> assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
