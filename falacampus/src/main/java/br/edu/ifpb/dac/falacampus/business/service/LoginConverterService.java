package br.edu.ifpb.dac.falacampus.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;

import br.edu.ifpb.dac.falacampus.model.entity.Login;
import br.edu.ifpb.dac.falacampus.model.entity.User;
import br.edu.ifpb.dac.falacampus.model.repository.LoginRepository;
import br.edu.ifpb.dac.falacampus.model.repository.UserRepository;

public class LoginConverterService {
	
	
	@Autowired
	private LoginRepository loginRepository;
//	@Autowired
//    private UserMapper userMapper;
	
	public Login save(Login login) {
		return loginRepository.save(login);
	}

	public void deleteById(Long matricula) {
		
		Login  login = findById(matricula);
		
		if(login == null) {
			throw new IllegalStateException(String.format("Could not find a entity with id=%1", matricula));
		}
		
		loginRepository.deleteById(matricula);
	}

	public Login update(Login login) {
		return loginRepository.save(login);
	}
	
	public Login update(Long matricula) {
		Login login= loginRepository.getById(matricula);
		if(matricula == null) {
			throw new IllegalStateException("Matricula cannot be null");
		}		
		return loginRepository.save(login);
	}
	
	public Login findById(Long matricula) {
		
		if(matricula == null) {
			throw new IllegalStateException("Matricula cannot be null");
		}
		return loginRepository.findById(matricula).get();
	}
	public Login findByName(String name) {
		return null;
		
	}

	public List<Login> findAll() {
		return loginRepository.findAll();
	}
	
	public List<Login> find(Login filter) {
		Example example = Example.of(filter, ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		
		return loginRepository.findAll(example);
		
	}


}
