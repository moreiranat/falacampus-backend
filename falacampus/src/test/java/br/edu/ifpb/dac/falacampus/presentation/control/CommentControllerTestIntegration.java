package br.edu.ifpb.dac.falacampus.presentation.control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.File;
import java.time.LocalDateTime;

import org.hibernate.ObjectNotFoundException;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import br.edu.ifpb.dac.falacampus.business.service.CommentService;
import br.edu.ifpb.dac.falacampus.business.service.DetailsCommentConverterService;
import br.edu.ifpb.dac.falacampus.business.service.impl.DetailsCommentConverterServiceImpl;
import br.edu.ifpb.dac.falacampus.exceptions.CommentCannotUpdateException;
import br.edu.ifpb.dac.falacampus.model.entity.Answer;
import br.edu.ifpb.dac.falacampus.model.entity.Comment;
import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.model.entity.User;
import br.edu.ifpb.dac.falacampus.model.enums.CommentType;
import br.edu.ifpb.dac.falacampus.model.enums.StatusComment;
import br.edu.ifpb.dac.falacampus.model.repository.CommentRepository;
import br.edu.ifpb.dac.falacampus.presentation.dto.DetailsCommentDto;

class CommentControllerTestIntegration {

	@InjectMocks
	private CommentController commentController;
	@InjectMocks
	private CommentService commentService;
	@InjectMocks
	private DetailsCommentConverterService detailsCommentConverter;
	@Mock
	private CommentRepository commentRepository;
	@Captor
	private ArgumentCaptor<Comment> captureComment = ArgumentCaptor.forClass(Comment.class);;

	private Comment comment;
	private DetailsCommentDto detailsCommentDto;
	private ResponseEntity responseEntity;

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		commentController = new CommentController();
		commentService = new CommentService();
		detailsCommentConverter = new DetailsCommentConverterServiceImpl();

		ReflectionTestUtils.setField(commentService, "commentRepository", commentRepository);
		ReflectionTestUtils.setField(commentController, "commentService", commentService);
		ReflectionTestUtils.setField(commentController, "detailsCommentConverter", detailsCommentConverter);

		comment = new Comment();
		comment.setId(1L);
		comment.setTitle("Crítica telhado");
		comment.setMessage("Qual o prazo para realizar reparo do telhado.");
		comment.setCreationDate(LocalDateTime.now());
		comment.setCommentType(CommentType.REVIEW);
		comment.setStatusComment(StatusComment.NOT_SOLVED);
		comment.setAuthor(new User());
		comment.setDepartament(new Departament());
		comment.setAnswer(new Answer());
		comment.setAttachment(new File("Document"));

		detailsCommentDto = new DetailsCommentDto();
		detailsCommentDto.setId(1L);
		detailsCommentDto.setTitle("Crítica telhado");
		detailsCommentDto.setMessage("Qual o prazo para realizar reparo do telhado.");
		detailsCommentDto.setCreationDate(LocalDateTime.now());
		detailsCommentDto.setCommentType(CommentType.REVIEW);
		detailsCommentDto.setStatusComment(StatusComment.NOT_SOLVED);
		detailsCommentDto.setAuthorId(1L);
		detailsCommentDto.setDepartamentId(1L);
		detailsCommentDto.setAnswerId(1L);

	}

	@Test
	public void saveCommentDataBase() {
		try {
			responseEntity = commentController.save(detailsCommentDto);
			Mockito.verify(commentService).save(captureComment.capture());
			Comment commentData = captureComment.getValue();

			assertAll("Test for save Comment using CommentDto and Comment.",
					() -> assertEquals(detailsCommentDto.getId(), commentData.getId()),
					() -> assertEquals(detailsCommentDto.getTitle(), commentData.getTitle()),
					() -> assertEquals(detailsCommentDto.getMessage(), commentData.getMessage()),
					() -> assertEquals(detailsCommentDto.getCreationDate(), commentData.getCreationDate()),
					() -> assertEquals(detailsCommentDto.getCommentType(), commentData.getCommentType()),
					() -> assertEquals(detailsCommentDto.getStatusComment(), commentData.getStatusComment()),
					() -> assertEquals(detailsCommentDto.getAuthorId(), commentData.getAuthor().getId()),
					() -> assertEquals(detailsCommentDto.getDepartamentId(), commentData.getDepartament().getId()),
					() -> assertEquals(detailsCommentDto.getAnswerId(), commentData.getAnswer().getId()));
		} catch (Exception e) {
			
		}

	}

	@Test
	public void saveComment() {
		try {

			Mockito.when(commentService.save(Mockito.any())).thenReturn(comment);
			responseEntity = commentController.save(detailsCommentDto);

			assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void updateCommentByIdDataBase() throws Exception {
		try {
			responseEntity = commentController.update(1L, detailsCommentDto);
			Mockito.verify(commentService, Mockito.times(1)).update(captureComment.capture());
			Comment commentData = captureComment.getValue();

			assertEquals(detailsCommentDto.getId(), commentData.getId());

		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Test
	public void updateCommentNotPossible() {

		try {
			responseEntity = commentController.update(1L, detailsCommentDto);
			Mockito.when(commentService.update(comment)).thenThrow(new CommentCannotUpdateException());
			String t = String.valueOf(responseEntity.getBody());
			Comment commentData = captureComment.getValue();

			assertAll("Not found Entity", 
					() -> assertTrue(t.contains("Comment cannot be updated!")),
					() -> assertEquals(comment.getStatusComment().SOLVED, commentData.getStatusComment().SOLVED),
					() -> assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode()));

		} catch (Exception e) {
			e.getMessage();
		}

	}
	
	@Test
	public void updateCommentHttpStatusOk() {
			try {
				Mockito.when(commentService.update(Mockito.any())).thenReturn(comment);
				responseEntity = commentController.update(comment.getId(), detailsCommentDto);
				
				assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	}
	
	@Test
	public void deleteCommentById() {
		
		try {
			responseEntity = commentController.delete(1L);
			
			assertAll("Not found Entity",
					() -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
					() -> assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode()),
					() -> assertEquals(null, responseEntity.getBody()));
			
			Mockito.verify(commentController, Mockito.times(1)).delete(1L);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	

}
