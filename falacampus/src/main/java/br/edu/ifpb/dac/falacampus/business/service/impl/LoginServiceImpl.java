package br.edu.ifpb.dac.falacampus.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
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
	@Value("${app.login}")
	private String login;
	private String suapToken;

	@Override
	public  User login(String username, String password) {
		String jsonToken = suapService.login(username, password);
		this.suapToken = converterService.jsonToToken(jsonToken);
		if (this.suapToken == null) {
			throw new IllegalArgumentException("Incorret Matricula or Password");
		}
		User user = userService.findByName(username);
		
		if(user == null) {
			String json = suapService.findUser(suapToken,login);
			user =converterService.jsonToUser(json);
			user = userService.save(user);
		}
		return user;
	}
	

}
