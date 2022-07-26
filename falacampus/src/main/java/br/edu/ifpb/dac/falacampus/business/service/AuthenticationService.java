package br.edu.ifpb.dac.falacampus.business.service;

import br.edu.ifpb.dac.falacampus.model.entity.User;

public interface AuthenticationService {
	
	//public String login(String username,String password);
	
	public String login(Long registration,String password);
	public User getLoggedUser();

}
