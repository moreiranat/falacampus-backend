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

	public void deleteByRegistration(Long registration) {

		Login login = findByRegistration(registration);

		if (login == null) {
			throw new IllegalStateException(String.format("Could not find a entity with id=%1", registration));
		}

		loginRepository.deleteById(registration);
	}

	private Login findByRegistration(Long registration) {
		if (registration == null) {
			throw new IllegalStateException("registration cannot be null");
		}

		return loginRepository.findById(registration).get();

	}

	public Login update(Login login) {
		return loginRepository.save(login);
	}

	public Login update(Long registration) {
		Login login = loginRepository.getById(registration);
		if (registration == null) {
			throw new IllegalStateException("registration cannot be null");
		}
		return loginRepository.save(login);
	}

	public List<Login> findAll() {
		return loginRepository.findAll();
	}

	public List<Login> find(Login filter) {
		Example example = Example.of(filter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));

		return loginRepository.findAll(example);

	}

}
