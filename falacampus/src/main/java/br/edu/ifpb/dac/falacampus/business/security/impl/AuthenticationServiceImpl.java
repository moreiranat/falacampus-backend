package br.edu.ifpb.dac.falacampus.business.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.edu.ifpb.dac.falacampus.business.service.AuthenticationService;
import br.edu.ifpb.dac.falacampus.business.service.ConverterService;
import br.edu.ifpb.dac.falacampus.business.service.SuapService;
import br.edu.ifpb.dac.falacampus.business.service.SystemUserService;
import br.edu.ifpb.dac.falacampus.model.entity.SystemUser;
import br.edu.ifpb.dac.falacampus.model.interfaces.TokenService;


public class AuthenticationServiceImpl implements AuthenticationService{
	
	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private SuapService suapService;
	
	@Autowired
	private ConverterService converterService;
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private org.springframework.security.authentication.AuthenticationManager authenticationManager;
	
	
	
	@Override
	public String loginLocal(String email, String password) {
		
		return null;
	}

	@Override
	public SystemUser getLoggedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (SystemUser)authentication.getPrincipal();
	}
	
	public String login(String username, String password) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, password));
		SystemUser user = systemUserService.findByUserName(username);
		
		return tokenService.generate(user);
		
	
	}
	
	

}
