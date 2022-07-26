package br.edu.ifpb.dac.falacampus.presentation.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifpb.dac.falacampus.model.entity.Login;

public class LoginDto {

	private Long registration;

	private String password;

	public LoginDto() {

	}

	public LoginDto(Login login) {
		this.registration= login.getRegistration();
		this.password = login.getPassword();
	}

	public Long getRregistration() {
		return registration;
	}

	public void setRegistration(Long registration) {
		this.registration= registration;
	}


	public String getPassword() {
		return password;
	}

	public static List<LoginDto> converter(List<Login> logins) {
		return logins.stream().map(LoginDto::new).collect(Collectors.toList());
	}

}
