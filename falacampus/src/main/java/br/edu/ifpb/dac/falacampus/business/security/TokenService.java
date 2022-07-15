package br.edu.ifpb.dac.falacampus.business.security;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

public interface TokenService {
	
	String generate(SystemUser user); 
	Claims getClaims(String token) throws ExpiredJwtException;
	boolean isValid(String token);
	String getUsername(String token);
	Long getUserId(String token);
	String get(HttpServletRequest request);

}
