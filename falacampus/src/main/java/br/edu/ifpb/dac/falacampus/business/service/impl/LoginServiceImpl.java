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

import com.google.gson.JsonObject;

import br.edu.ifpb.dac.falacampus.business.service.ConverterService;
import br.edu.ifpb.dac.falacampus.business.service.DepartamentService;
import br.edu.ifpb.dac.falacampus.business.service.LoginService;
import br.edu.ifpb.dac.falacampus.business.service.SuapService;
import br.edu.ifpb.dac.falacampus.business.service.TokenService;
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

	@Autowired
	private TokenService tokenService;

	@Value("${app.logintype}")
	private String logintype;
	private String suapToken;

	public User getLoggedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return (User) authentication.getPrincipal();

	}

	public User suapLogin(String username, String password) throws NumberFormatException {

		String jsonToken = suapService.login(username, password);

		System.out.println("Retorno SUAP: " + jsonToken);
		try {
			this.suapToken = converterService.jsonToToken(jsonToken);

		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		if (this.suapToken == null) {
			throw new IllegalArgumentException("Incorrect Registration or Password");
		}

		String formatToken = jsonToken.substring(10, jsonToken.length() - 2);

		String userFind = this.suapService.findUser(formatToken, username);

		User user = null;
		try {

			user = converterService.jsonToUser(userFind);
			// user = userService.findByRegistration(username);
			user.setToken(tokenService.generate(user));
			userService.update(user);

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
					user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);

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
