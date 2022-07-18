package br.edu.ifpb.dac.falacampus.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.edu.ifpb.dac.falacampus.business.service.PasswordEnconderService;
import br.edu.ifpb.dac.falacampus.model.entity.SystemUser;
import br.edu.ifpb.dac.falacampus.model.enums.Role;
import br.edu.ifpb.dac.falacampus.model.interfaces.SystemUserService;
import br.edu.ifpb.dac.falacampus.model.repository.SystemUserRepository;

public class SystemUserServiceImpl implements SystemUserService {
	@Autowired
	private SystemUserRepository systemUserRepository;
	//@Autowired
	//private SystemRoleService systeRoleService;
	@Autowired
	private PasswordEnconderService passwordEnconderService;

	
	
	@Override
	public SystemUser save(SystemUser systemUser) {
		if(systemUser.getId() != null) {
			throw new IllegalStateException("User is no database!!");
		}
		passwordEnconderService.encryptPassword(systemUser);
		List<Role>roles = new ArrayList<>();
		//roles.add(systeRoleService.findDefault());
		//systemUser.setRoles(roles);
		return systemUserRepository.save(systemUser);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SystemUser user =findByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException(String.format("user with username", username));
			
		}
		return  (UserDetails) user;
	}

	

	@Override
	public SystemUser update(SystemUser systemUser) {
		if(systemUser.getId()==null) {
			throw new IllegalStateException("Id null");
		}
		passwordEnconderService.encryptPassword(systemUser);
	
		return null;
	}

	@Override
	public SystemUser delete(Long id) {
		if(id ==null) {
			throw new IllegalStateException("Id connot be null");
		}
		SystemUser systemUser = systemUserRepository.findById(id).get();
		if(id == null) {
			throw  new IllegalStateException(String.format("Coult not find a entity", id)); 
		}
		systemUserRepository.delete(systemUser);
		return systemUser;
	}

	@Override
	public SystemUser findById(Long id) {
		if(id==null){
			throw new IllegalStateException("Id count be null");
		}
		return findById(id);
	}

	@Override
	public SystemUser findByEmail(String email) {
		if(email==null){
			throw new IllegalStateException("Email count be null");
		}
		return findByEmail(email);
	}

	@Override
	public SystemUser findByUserName(String username) {
		if(username==null){
			throw new IllegalStateException("UserName count be null");
		}
		return findByUserName(username);
	}

	@Override
	public Iterable<SystemUser> findAll() {
		
		return systemUserRepository.findAll();
	}

	@Override
	public List<SystemUser> find(SystemUser filter) {
		Example example = Example.of(filter,
				ExampleMatcher
				.matching()
				.withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		
		return systemUserRepository.findAll();
	}
	

}
