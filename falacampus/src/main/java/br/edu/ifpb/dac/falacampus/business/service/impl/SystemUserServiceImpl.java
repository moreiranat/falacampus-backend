package br.edu.ifpb.dac.falacampus.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
		return null;
	}

	@Override
	public SystemUser delete(SystemUser systemUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SystemUser findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SystemUser findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SystemUser findByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<SystemUser> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SystemUser> find(SystemUser filter) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
