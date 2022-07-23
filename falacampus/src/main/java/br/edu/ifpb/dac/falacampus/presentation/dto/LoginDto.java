package br.edu.ifpb.dac.falacampus.presentation.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifpb.dac.falacampus.model.entity.Login;

public class LoginDto {

	private Long matricula;

	private String password;

	public LoginDto() {

	}

	public LoginDto(Login login) {
		this.matricula = login.getMatricula();
		this.password = login.getPassword();
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula= matricula;
	}


	public String getPassword() {
		return password;
	}

	public static List<LoginDto> converter(List<Login> logins) {
		return logins.stream().map(LoginDto::new).collect(Collectors.toList());
	}

}
