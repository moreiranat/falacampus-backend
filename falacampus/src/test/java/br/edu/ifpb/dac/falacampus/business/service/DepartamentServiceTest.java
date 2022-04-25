package br.edu.ifpb.dac.falacampus.business.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.hibernate.mapping.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.model.entity.User;
import br.edu.ifpb.dac.falacampus.model.enums.Role;
import br.edu.ifpb.dac.falacampus.model.repository.DepartamentRepository;
import br.edu.ifpb.dac.falacampus.presentation.control.DepartamentController;

class DepartamentServiceTest {
	
	@Autowired
	private DepartamentService departamentService;

	@Autowired
	private DepartamentRepository departamentRepository;
	
	@Autowired
	private Departament departament;
	
	@Autowired
	private User user;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		departament = new Departament();
		ArrayList<User> users = new ArrayList<>();
		user = new User();
		
		user.setId(1L);
		user.setName("Maria");
		user.setEmail("maria@email.com");
		user.setRegistration(12345L);
		user.setPassword("12345678");
		user.setRole(Role.STUDENT);
		user.setDepartament(departament);
		
		users.add(user);
		
		departament.setId(1L);
		departament.setName("Biblioteca");
		departament.setUsers(users);
	    
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void cadastroTest() {
		departamentService.save(departament);

	    Departament dep = departamentRepository.findAll().stream()
	        .filter(depar -> depar.getName().equals("Biblioteca")).findFirst().get();

	    assertEquals("1L", dep.getId());
	    assertEquals("Biblioteca", dep.getName());
	    assertEquals("1L", user.getId());
	    assertEquals("Maria", user.getName());
	    assertEquals("maria@email.com",user.getEmail());
	    assertEquals(12345L,user.getRegistration());
	    assertEquals("12345678",user.getPassword());
	    assertEquals(Role.STUDENT,user.getRole());
	    assertEquals(departament,user.getDepartament());
	    
	    
	  }

}
