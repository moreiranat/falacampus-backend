package br.edu.ifpb.dac.falacampus.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.edu.ifpb.dac.falacampus.business.service.AuthenticationService;
import br.edu.ifpb.dac.falacampus.business.service.ConverterService;
import br.edu.ifpb.dac.falacampus.business.service.SuapService;
import br.edu.ifpb.dac.falacampus.business.service.TokenService;
import br.edu.ifpb.dac.falacampus.business.service.UserService;

import br.edu.ifpb.dac.falacampus.model.entity.User;


public class AuthenticationServiceImpl implements AuthenticationService{
	
	@Autowired
	private UserService userService;
	@Autowired
	private SuapService suapService;
	@Autowired
	private ConverterService converterService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Value("${app.logintype}")
	private String logintype;
	
	private String suapToken;
	
	
	public String login(Long registration, String password) {
		
		return suapLogin(registration, password);
		
//		switch (logintype) {
//		case "suap": 
//			return suapLogin(registration, password);
//		case "local":
//			return localLogin(username, password);
//		default:
//			return localLogin(username, password);
//		}
	}
		
//	private String localLogin(String username, String password) {
//		//Exceção será lançada em caso de falha
//		Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		
//		SystemUser user = systemUserService.findByUserName(username);
//		
//		return tokenService.generate(user);
//	}
	
	private String suapLogin(Long registration, String password) {
		String jsonToken = suapService.login(registration, password);
		this.suapToken = converterService.jsonToToken(jsonToken);
		
		if(this.suapToken == null) {
			throw new IllegalArgumentException("Incorrect E-mail or Password");
		}
		
		User user = userService.findByRegistration(registration);
		
		if(user == null) {
			String json = suapService.findUser(suapToken, registration);
			user = converterService.jsonToUser(json);
			
			user = userService.save(user);
		}
		
		return tokenService.generate(user);
	}

	@Override
	public User getLoggedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (User) authentication.getPrincipal();
	}
	
}
