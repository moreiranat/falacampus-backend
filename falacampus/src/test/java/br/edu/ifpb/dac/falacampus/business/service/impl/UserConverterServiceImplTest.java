package br.edu.ifpb.dac.falacampus.business.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifpb.dac.falacampus.business.service.UserConverterService;
import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.model.entity.User;
import br.edu.ifpb.dac.falacampus.model.enums.Role;
import br.edu.ifpb.dac.falacampus.presentation.dto.UserDto;

class UserConverterServiceImplTest {

	@Autowired
	private static UserConverterService userConverterServiceImpl;
	@Autowired
	private static UserConverterService userConverterService;
	@Autowired
	private static UserDto dto;
	@Autowired
	private static User user;
	@Autowired
	private static Departament dep;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		userConverterServiceImpl = new UserConverterServiceImpl();
		user = new User();
		dto = new UserDto();
		dep = new Departament();
		userConverterService = new UserConverterServiceImpl();

		dep.setId(1L);
		dep.setName("Biblioteca");

		user.setId(1L);
		user.setName("Maria");
		user.setEmail("maria@email.com");
		user.setRegistration(1234L);
		user.setRole(Role.STUDENT);
		user.setPassword("1234");
		user.setDepartament(dep);


		dto.setId(1L);
		dto.setName("Maria");
		dto.setEmail("maria@email.com");
		dto.setRegistration(1234L);
		dto.setRole(Role.STUDENT);
		dto.setPassword("1234");
		dto.setDepartamentId(dep.getId());

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {

	}

	@BeforeEach
	void setUp() throws Exception {

	}

	@AfterEach
	void tearDown() throws Exception {

	}

	@Test
	void userToDTOTest() {

		assertEquals(UserDto.class, userConverterServiceImpl.userToDTO(user).getClass());

	}

	@Test
	void dtoToUserTest() {

		assertEquals(user.getClass(), userConverterServiceImpl.dtoToUser(dto).getClass());

	}

	@Test
	public void userToDtoItens() {
		UserDto userDto = userConverterService.userToDTO(user);

		assertAll("userToDto", () -> assertEquals(userDto.getId(), user.getId()),
				() -> assertEquals(userDto.getName(), user.getName()),
				() -> assertEquals(userDto.getEmail(), user.getEmail()),
				() -> assertEquals(userDto.getRegistration(), user.getRegistration()),
				() -> assertEquals(userDto.getRole(), user.getRole()));
				
	}

	@Test
	public void dtoToUserItens() {
		User user = userConverterService.dtoToUser(dto);

		assertAll("dtoToUser", () -> assertEquals(user.getId(), dto.getId()),
				() -> assertEquals(user.getName(), dto.getName()), () -> assertEquals(user.getEmail(), dto.getEmail()),
				() -> assertEquals(user.getRegistration(), dto.getRegistration()),
				() -> assertEquals(user.getRole(), dto.getRole()));

	}

}
