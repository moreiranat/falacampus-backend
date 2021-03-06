package br.edu.ifpb.dac.falacampus.presentation.dto;

import java.util.Objects;

public class TokenDto {
	
	private String token;
	private UserDto user;
	
	public TokenDto(String token, UserDto user) {
		this.token =token;
		this.user =user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(token, user);
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
		return Objects.equals(token, other.token) && Objects.equals(user, other.user);
	}
	
	

}
