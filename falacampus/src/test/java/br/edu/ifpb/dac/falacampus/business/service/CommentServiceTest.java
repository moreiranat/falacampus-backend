package br.edu.ifpb.dac.falacampus.business.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import br.edu.ifpb.dac.falacampus.model.entity.Answer;
import br.edu.ifpb.dac.falacampus.model.entity.Comment;
import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.model.entity.User;
import br.edu.ifpb.dac.falacampus.model.enums.CommentType;
import br.edu.ifpb.dac.falacampus.model.enums.StatusComment;
import br.edu.ifpb.dac.falacampus.model.repository.CommentRepository;
import br.edu.ifpb.dac.falacampus.presentation.dto.CommentDto;

class CommentServiceTest {
	
	private static final Long ID = 1L;
	private static final String TITLE = "Crítica telhado";
	private static final String MESSAGE = "Qual o prazo para realizar reparo do telhado.";
	private static final LocalDateTime DATE_COMMENT = LocalDateTime.now();
	private static final CommentType COMMENT_TYPE = CommentType.REVIEW;
	private static final StatusComment STATUS_COMMENT = StatusComment.NOT_SOLVED; 
	private static final User AUTHOR = new User(); 
	private static final Departament DEPARTAMENT = new Departament(); 
	private static final Answer ANSWER = new Answer(); 
	private static final File ATTACHMENT = new File("Document");
	
	@InjectMocks
	private CommentService commentService;
	
	@Mock
	private CommentRepository commentRepository;
	
	@Mock
	private ModelMapper mapper;
	
	private CommentDto commentDto;
	private Optional<Comment> optionalComment;
	private List<Comment> listComment;
	private Comment comment;


	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startComment();
	}
	
	private void startComment() {
		
		comment = new Comment(ID, TITLE, MESSAGE, COMMENT_TYPE,
				STATUS_COMMENT, AUTHOR, DEPARTAMENT, ANSWER, ATTACHMENT);
		
		commentDto = new CommentDto(comment);
		
		optionalComment = Optional.of(new Comment(ID, TITLE, MESSAGE, COMMENT_TYPE,
				STATUS_COMMENT, AUTHOR, DEPARTAMENT, ANSWER, ATTACHMENT));		
	}
	
	@DisplayName("Save Comment")
	@Test
	public void saveCommentSucess() {
		
		when(commentRepository.save(comment))
		.thenReturn(comment);
		
		Comment response = commentService.save(comment);
		assertNotNull(response);
		assertEquals(Comment.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(TITLE, response.getTitle());
		assertEquals(MESSAGE, response.getMessage());
		assertEquals(COMMENT_TYPE, response.getCommentType());
		assertEquals(STATUS_COMMENT, response.getStatusComment());
		assertEquals(AUTHOR, response.getAuthor());
		assertEquals(DEPARTAMENT, response.getDepartament());
		assertEquals(ANSWER, response.getAnswer());
		assertEquals(ATTACHMENT, response.getAttachment());
		
		verify(commentRepository, times(1)).save(response);
		
	}
		
	@DisplayName("Save CommentDto")
	@Test
	public void saveCommentDtoSucess() {
		
		commentService.saveCommentDto(commentDto);
		Comment c = mapper.map(commentDto, Comment.class);
		
		verify(commentRepository, times(1)).save(c);
	}
	
	@DisplayName("Find by id")
	@Test
	public void findByIdSucess() {
		
		when(commentRepository.findById(ID)).thenReturn(optionalComment);
		
		
		verify(commentRepository, times(0)).findById(ID);
		
	}

	@DisplayName("Find All Comment")
	@Test
	public void findAllSucess() {
		
		when(commentRepository.findAll())
		.thenReturn(listComment);
		
		verify(commentRepository, times(0)).findAll();
		
	}

}
