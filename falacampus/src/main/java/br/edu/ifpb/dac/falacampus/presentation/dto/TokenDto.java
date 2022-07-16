package br.edu.ifpb.dac.falacampus.presentation.dto;

import java.util.Objects;

public class TokenDto {
	
	private String token;
	private SystemUserDto user;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public SystemUserDto getUser() {
		return user;
	}
	public void setUser(SystemUserDto user) {
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
