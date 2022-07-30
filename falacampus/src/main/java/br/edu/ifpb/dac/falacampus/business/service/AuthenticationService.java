package br.edu.ifpb.dac.falacampus.business.service;

import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.falacampus.model.entity.User;
@Service

public interface AuthenticationService {
	
	//public String login(Long registration,String password);
	
	public String login(String username,String password);	
	public User getLoggedUser();

}
