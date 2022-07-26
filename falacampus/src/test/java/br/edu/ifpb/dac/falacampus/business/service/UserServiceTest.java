package br.edu.ifpb.dac.falacampus.business.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifpb.dac.falacampus.exceptions.UserIdException;
import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.model.entity.User;
import br.edu.ifpb.dac.falacampus.model.enums.Role;
import br.edu.ifpb.dac.falacampus.model.repository.UserRepository;
import br.edu.ifpb.dac.falacampus.presentation.dto.UserDto;

@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
 
class UserServiceTest {
	private static final Long ID = 1L;
	private static final String NAME = "patrícia";
	private static final String EMAIL = "@patricia";
	private static final Long REGISTRATION = 223L;
	private static final Role ROLE = Role.STUDENTS;
	private static final String PASSWORD = "111";

	private static final Departament DEPARTAMENT = new Departament();

	@InjectMocks
	private UserService userService;

	@Mock
	@Autowired
	private UserRepository userRepository;

	@Mock
	private ModelMapper mapper;

	private UserDto userDto;

	private Optional<User> optionalUser;
	private User user;
	private OngoingStubbing<Optional<User>> thenThrow;

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

		user = new User(ID, NAME, EMAIL, REGISTRATION, ROLE, PASSWORD, DEPARTAMENT);

		userDto = new UserDto();

		optionalUser = Optional.of(new User(ID, NAME, EMAIL, REGISTRATION, ROLE, PASSWORD, DEPARTAMENT));
	}

	@Test
	public void save() {
		user.setName(NAME);
		user.setDepartament(DEPARTAMENT);
		user.setId(ID);
		user.setEmail(EMAIL);
		user.setPassword(PASSWORD);
		user.setRegistration(REGISTRATION);
		user.setRole(ROLE);
		userService.save(user);
		verify(userRepository, times(0)).save(null);

	}

	@Test
	public void saveCommentDto() {

	}
	@Test
	public void testCamposObrigatorio() {
		 user=new  User();
		 userService.save(user);
	}

	@Test
	public void whenUpdate() {
		User user = new User();
		user.setId(19L);
		user.setName("paty");
		userService.update(user); // service.update(user);
		Mockito.verify(userRepository, Mockito.times(1)).save(user);
	}

	@Test
	public void checkUserServicedReturnNull() {
		user = new User(ID, NAME, EMAIL, REGISTRATION, ROLE, PASSWORD, DEPARTAMENT);
		userRepository.save(user);
		Integer countUser = userRepository.findAll().size();
		// assertEquals(1,countUser);
		User user1 = userRepository.getById(ID);
		assertNull(user1);
	}
	//quando for buscar todos retornar uam list de
	//usuarios
	@Test
	public void whenFindAll() {
		when(userRepository.findAll()).thenReturn(List.of(user));
		List<User>respose = userService.findAll();
		assertNotNull(respose);
		assertEquals(1, respose.size());
		assertEquals(User.class, respose.get(0).getClass());
		assertEquals(ID, respose.get(0).getId());
		assertEquals(NAME, respose.get(0).getName());
		assertEquals(PASSWORD, respose.get(0).getPassword());
		assertEquals(EMAIL, respose.get(0).getEmail());
	}
	@Test
	public void whenFindByIdUser() {
		Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(optionalUser);

		User response = (User) userService.findById(ID);
		// .findById(ID);
		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(ID, (response).getId());
		assertEquals(EMAIL, (response).getEmail());
		assertEquals(NAME, response.getName());
		assertEquals(ROLE, (response).getRole());
		assertEquals(PASSWORD, response.getPassword());
		assertEquals(REGISTRATION, (response).getRegistration());
		assertEquals(DEPARTAMENT, (response).getDepartament());

	}
	///test unitario
	@Test
	void deve_salvar_user_repositorio()throws Exception {
		userService.save(user);
		verify(userRepository).save(user);
		
	}
	@Test
	void nao_deve_salvar_user_com_mesmo_id()throws Exception {
		when(userRepository.findById(ID)).thenReturn(Optional.of(user));
		//passando uma user que ja existe
		userRepository.save(user);
		
	}

}
