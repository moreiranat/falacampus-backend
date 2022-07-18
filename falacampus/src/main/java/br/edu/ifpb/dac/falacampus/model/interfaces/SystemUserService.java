package br.edu.ifpb.dac.falacampus.model.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.core.userdetails.UserDetailsService;

import br.edu.ifpb.dac.falacampus.model.entity.SystemUser;
import br.edu.ifpb.dac.falacampus.model.repository.SystemUserRepository;

public interface SystemUserService extends UserDetailsService {
	
	  public SystemUser save(SystemUser systemUser); 
	  public SystemUser update(SystemUser systemUser);
	  
	  public SystemUser delete(SystemUser systemUser); 
	  public SystemUser findById(Long id); 
	  public SystemUser findByEmail(String email);
	  
	  public SystemUser findByUserName(String username); 
	  public Iterable<SystemUser>findAll(); 
	  public List<SystemUser> find(SystemUser filter);
	 
}
