package br.edu.ifpb.dac.falacampus.business.service;

import br.edu.ifpb.dac.falacampus.model.entity.User;

public interface AuthenticationService {
	
	//public String login(String username,String password);
	
	public String login(String email,String password);
	public User getLoggedUser();

}
