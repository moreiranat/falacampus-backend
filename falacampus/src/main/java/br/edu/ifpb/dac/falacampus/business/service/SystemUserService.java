package br.edu.ifpb.dac.falacampus.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;

import br.edu.ifpb.dac.falacampus.model.entity.SystemUser;
import br.edu.ifpb.dac.falacampus.model.repository.SystemUserRepository;

public interface SystemUserService {
	
	@Autowired
	private SystemUserRepository systemUserRepository;
	
//	@Autowired
//    private UserMapper userMapper;
	
	public SystemUser save(SystemUser systemUser) {
		return systemUserRepository.save(systemUser);
	}

	public void deleteById(Long id) {
		
		SystemUser systemUser = findById(id);
		
		if(systemUser == null) {
			throw new IllegalStateException(String.format("Could not find a entity with id=%1", id));
		}
		
		systemUserRepository.deleteById(id);
	}

	public SystemUser update(SystemUser systemUser) {
		return systemUserRepository.save(systemUser);
	}
	
	public SystemUser update(Long id) {
		SystemUser systemUserSave = systemUserRepository.getById(id);
		if(id == null) {
			throw new IllegalStateException("Id cannot be null");
		}		
		return systemUserRepository.save(systemUserSave);
	}
	
	public SystemUser findById(Long id) {
		
		if(id == null) {
			throw new IllegalStateException("Id cannot be null");
		}
		return systemUserRepository.findById(id).get();
	}

	public List<SystemUser> findAll() {
		return systemUserRepository.findAll();
	}
	
	public List<SystemUser> find(SystemUser filter) {
		Example example = Example.of(filter, ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		
		return systemUserRepository.findAll(example); 
		
	}
	
	
	
	/*
	 * public SystemUser save(SystemUser systemUser); public SystemUser
	 * update(SystemUser systemUser);
	 * 
	 * public SystemUser delete(SystemUser systemUser); public SystemUser
	 * findById(Long id); public SystemUser findByEmail(String email);
	 * 
	 * public SystemUser findByUserName(String username); public
	 * Iterable<SystemUser>findAll(); public List<SystemUser> find(SystemUser
	 * filter);
	 */
	
	
	
	
	
	

}
