package br.edu.ifpb.dac.falacampus.presentation.dto;

import java.util.Objects;

public class TokenDto {
	
	private String token;

	
	public TokenDto(String token) {
		this.token =token;

	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public int hashCode() {
		return Objects.hash(token);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TokenDto other = (TokenDto) obj;
		return Objects.equals(token, other.token);
	}

	
	
	
	
	

}
