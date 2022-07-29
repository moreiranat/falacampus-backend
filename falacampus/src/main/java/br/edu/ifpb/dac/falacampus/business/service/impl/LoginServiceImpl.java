package br.edu.ifpb.dac.falacampus.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import br.edu.ifpb.dac.falacampus.business.service.ConverterService;
import br.edu.ifpb.dac.falacampus.business.service.LoginService;
import br.edu.ifpb.dac.falacampus.business.service.SuapService;
import br.edu.ifpb.dac.falacampus.business.service.UserService;

import br.edu.ifpb.dac.falacampus.model.entity.User;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION)

public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserService userService;
	@Autowired
	private SuapService suapService;
	@Autowired
	private ConverterService converterService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Value("${app.logintype}")
	private String login;
	private String suapToken;

	

	public User getLoggedUser() {
	Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
				//SecurityContextHolder.getContext().getAuthentication();
		return (User) authentication.getPrincipal();
		
	}
	
	
		public User suapLogin(String username, String password) throws NumberFormatException,Exception {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			String jsonToken = suapService.login(username, password);
			this.suapToken= converterService.jsonToToken(jsonToken);
			if(this.suapToken == null) {
				throw new IllegalArgumentException("User or passowd invalido");
			}
			User user = new User();
			try {
				user= userService.findByRegistration(Long.parseLong(username)).get();
				
			} catch (Exception e) {
				String json = suapService.findUser(this.suapToken, username);
				user=converterService.jsonToUser(json);
				user.setToken(this.suapToken);
				user =userService.save(user);
				
			}
			return user;
			
		}
		
//		@Override
//		public  User login(String username, String password)throws NumberFormatException {
//			String jsonToken = suapService.login(username, password);
//			this.suapToken = converterService.jsonToToken(jsonToken);
//			if (this.suapToken == null) {
//				throw new IllegalArgumentException("Incorret Matricula or Password");
//			}
//			User user = userService.findByName(username);
//			
//			if(user == null) {
//				String json = suapService.findUser(suapToken,login);
//				user =converterService.jsonToUser(json);
//				user = userService.save(user);
//			}
//			return user;
//		}
	

}
