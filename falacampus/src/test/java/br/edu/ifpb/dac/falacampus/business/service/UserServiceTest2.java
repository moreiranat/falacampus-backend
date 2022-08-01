package br.edu.ifpb.dac.falacampus.business.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.model.entity.User;

import br.edu.ifpb.dac.falacampus.model.repository.UserRepository;

public class UserServiceTest2 {

	@Mock
	private UserRepository userRepository;
	
	@Mock
	private UserService userCrudService ;;
	@Mock
	private List<User> users;
	
	@BeforeEach
	public void before() {
		this.userRepository =  mock(UserRepository.class);
		this.users =  mock(List.class);		
	}
 
	@Test
	void testFindAllService() {
		
		when(userRepository.findAll()).thenReturn(users);		
		when(users.size()).thenReturn(10);		
		assertEquals(10, users.size());
		verify(userRepository.findAll()).size();//saber se o método findAll foi chamado durante
												//a execução do teste
	}
	
	@Test
	void testFindById () { 
		
		assertThrows(IllegalStateException.class,
				() -> userCrudService.findById(null));
	}
	
	@Test
	void testDeleteById() {		
		assertThrows(IllegalStateException.class, 
				() -> ((CrudRepository<User, Long>) userCrudService).deleteById(null));
	}
	
	@Test
	void testUpate() {
		when(users.get(0)).thenReturn(new User(1l, "teste", "email@teste","2l" , "password123", new Departament()));
		//Mockando a chamada para o método save
		//Quando tentar salvar qualquer usuário (any), vai ser retornado esse usuário
		//instanciado como parâmetro
		when(userRepository.save(any(User.class))).thenReturn(new User(1l, "teste", "emailupdate@teste","2l" , "password123", new Departament()));
		
		String UpdateEmail ="emailupdate@teste";
		
		User user = users.get(0);
		user.setEmail(UpdateEmail);		
		
		User userUpdate = userRepository.save(user);
		String email = userUpdate.getEmail();
		
		assertEquals(UpdateEmail, userUpdate.getEmail());
		
	}

}
