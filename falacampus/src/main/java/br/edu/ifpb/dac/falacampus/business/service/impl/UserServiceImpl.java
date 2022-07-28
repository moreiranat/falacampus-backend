package br.edu.ifpb.dac.falacampus.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.falacampus.business.service.PasswordEnconderService;
import br.edu.ifpb.dac.falacampus.business.service.SystemRoleService;
import br.edu.ifpb.dac.falacampus.business.service.UserService;
import br.edu.ifpb.dac.falacampus.model.entity.SystemRole;
import br.edu.ifpb.dac.falacampus.model.entity.User;
import br.edu.ifpb.dac.falacampus.model.repository.UserRepository;

//Classe service que executa a lógica de autenticação 
//(contém a lógica para validar as credenciais de um usuário que está se autenticando)
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SystemRoleService roleService;
	@Autowired
	private PasswordEnconderService passwordEnconderService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("Could not find any user with username %s", username));
		}
		return user.get();
	}
	
	@Override
	public User save(User user) {
		if (user.getId() != null) {
			throw new IllegalStateException("User is already in the database");
		}
		passwordEnconderService.encryptPassword(user);
		List<SystemRole> roles = new ArrayList<>();
		roles.add(roleService.findDefault());
	
		user.setRoles(roles);
		return userRepository.save(user);

	}

	@Override
	public User update(User user) {
		if (user.getId() != null) {
			throw new IllegalStateException("User is already in the database");
		}
		passwordEnconderService.encryptPassword(user);
		List<SystemRole> roles = new ArrayList<>();
		roles.add(roleService.findDefault());
		user.setRoles(roles);
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		User user = findById(id);

		if (user == null) {
			throw new IllegalStateException(String.format("Could not find a entity with id=%1", id));
		}

		userRepository.deleteById(id);
	}

	@Override
	public User findById(Long id) {
		if(id == null) {
			throw new IllegalStateException("Id cannot be null");
		}
		return userRepository.findById(id).get();
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findByName(String name) {
		return userRepository.findByName(name);
	}
	
	@Override
	public User findByUserName(String name) {
		return userRepository.findByName(name);
	}
	
	@Override
	public User findByRegistration(Long registration) {
		return userRepository.findByRegistration(registration);
	}

	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Iterable<User> find(User filter) {
		Example example = Example.of(filter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));

		return userRepository.findAll(example);

	}

}
