package br.edu.ifpb.dac.falacampus.business.security.impl;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifpb.dac.falacampus.business.service.AuthenticationService;
import br.edu.ifpb.dac.falacampus.business.service.SystemUserService;
import br.edu.ifpb.dac.falacampus.model.entity.SystemUser;

public class AuthenticationServiceImpl implements AuthenticationService{
	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	
	
	
	@Override
	public String login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SystemUser getLoggedUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
