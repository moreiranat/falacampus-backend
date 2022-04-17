package br.edu.ifpb.dac.falacampus.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.falacampus.model.entity.User;
import br.edu.ifpb.dac.falacampus.model.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) {
		return userRepository.save(user);
	}

	public void deleteById(Long id) {
		
		User user = findById(id);
		
		if(user == null) {
			throw new IllegalStateException(String.format("Could not find a entity with id=%1", id));
		}
		
		userRepository.deleteById(id);
	}

	public User update(User user) {
		return userRepository.save(user);
	}
	
	public User update(Long id) {
		User userSalvo = userRepository.getById(id);
		if(id == null) {
			throw new IllegalStateException("Id cannot be null");
		}		
		return userRepository.save(userSalvo);
	}
	
	public User findById(Long id) {
		if(id == null) {
			throw new IllegalStateException("Id cannot be null");
		}
		return userRepository.findById(id).get();
	}

	public Iterable<User> findAll() {
		return userRepository.findAll();
	}
	
	public List<User> find(User filter) {
		Example example = Example.of(filter, ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		
		return userRepository.findAll(example);
		
	}
}
