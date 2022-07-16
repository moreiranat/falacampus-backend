package br.edu.ifpb.dac.falacampus.business.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.edu.ifpb.dac.falacampus.model.entity.SystemUser;
import br.edu.ifpb.dac.falacampus.model.entity.User;

public interface SystemUserService extends UserDetailsService{
	public SystemUser save(SystemUser systemUser);
	public SystemUser update(SystemUser systemUser);
	
	public SystemUser delete(SystemUser systemUser);
	public SystemUser findById(Long id);
	public SystemUser findByEmail(String email);
	
	public SystemUser findByUserName(String username);
	public  Iterable<SystemUser>findAll();
	public List<SystemUser> find(SystemUser filter);
	
	
	
	
	
	

}
