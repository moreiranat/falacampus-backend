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
import br.edu.ifpb.dac.falacampus.business.service.DepartamentService;
import br.edu.ifpb.dac.falacampus.business.service.LoginService;
import br.edu.ifpb.dac.falacampus.business.service.SuapService;
import br.edu.ifpb.dac.falacampus.business.service.UserService;
import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.model.entity.User;

@Service
//@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserService userService;
	@Autowired
	private SuapService suapService;
	@Autowired
	private DepartamentService departamentService;
	@Autowired
	private ConverterService converterService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Value("${app.logintype}")
	private String logintype;
	private String suapToken;

	public User getLoggedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return (User) authentication.getPrincipal();

	}

	public User suapLogin(String username, String password) throws NumberFormatException {

		// Authentication authentication = authenticationManager.authenticate(new
		// UsernamePasswordAuthenticationToken(username, password));
		String jsonToken = suapService.login(username, password);
		System.out.println("Retorno do SUAP: "+jsonToken);
		User user = null;

		
		try {
			this.suapToken = converterService.jsonToToken(jsonToken);
			
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		if (this.suapToken == null) {
			throw new IllegalArgumentException("Incorrect Registration or Password");
		}
		
		
		//{"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoyMzg4OSwidXNlcm5hbWUiOiIyNDAzOTQ0IiwiZXhwIjoxNjU5OTI1MDI5LCJlbWFpbCI6InRpYWdvLnJvY2hhQGlmcGIuZWR1LmJyIiwib3JpZ19pYXQiOjE2NTk4Mzg2Mjl9.AteKiYrEeAySiMJQo5HJ84hiFXOp4g4UaRdpM8I80qQ"}
		
		
		String tokenTeste = jsonToken.substring(10,jsonToken.length()-2);
		
		String findStudent = this.suapService.findStudent(tokenTeste, username);
		
		user = converterService.jsonToUser(findStudent);
		user.setToken(tokenTeste);
		
		System.out.println("Testando: "+user.getName()+"\n"+user.getDepartament().getName());
		
		
		System.out.println("findStudent: "+findStudent);

		

		try {
			user.setPassword("Temp");
			user.setEmail("emailtemp@ifpb.edu.br");
			userService.save(user);

			// user = userService.findByRegistration(Long.parseLong(username)).get();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return user;

	}

//		@Override
//		public  User login(String username, String password) throws NumberFormatException {
//			String jsonToken = suapService.login(username, password);
//			this.suapToken = converterService.jsonToToken(jsonToken);
//			if (this.suapToken == null) {
//				throw new IllegalArgumentException("Incorret Matricula or Password");
//			}
//			User user = userService.findByName(username);
//			
//			if(user == null) {
//				String json = suapService.findUser(suapToken, username);
//				user =converterService.jsonToUser(json);
//				user = userService.save(user);
//			}
//			return user;
//		}

}
