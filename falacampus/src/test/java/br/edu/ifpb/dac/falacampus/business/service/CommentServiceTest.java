package br.edu.ifpb.dac.falacampus.business.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
	private Comment comment;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startComment();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	private void startComment() {
		
		comment = new Comment(ID, TITLE, MESSAGE, COMMENT_TYPE,
				STATUS_COMMENT, AUTHOR, DEPARTAMENT, ANSWER, ATTACHMENT);
		
		commentDto = new CommentDto(comment);
		
		optionalComment = Optional.of(new Comment(ID, TITLE, MESSAGE, COMMENT_TYPE,
				STATUS_COMMENT, AUTHOR, DEPARTAMENT, ANSWER, ATTACHMENT));		
	}
	
	@Test
	public void save() {
		
	}
	
	@Test
	public void saveCommentDto() {
		
	}
	
	@Test
	public void deleteById() {
	
	}

	@Test
	public void update() {

	}
	
	@Test
	public void updateCommentDto() {
		
	}	
	
	@Test
	public void whenFindByIdThenReturnAnInstance() {
		Mockito.when(commentRepository.findById(Mockito.anyLong()))
		.thenReturn(optionalComment);
		
		Comment response = commentService.findById(ID);
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
		
		
	}

	@Test
	public void findAll() {
		
	}
	
	@Test
	public void findFilter() {
		
	}


}
