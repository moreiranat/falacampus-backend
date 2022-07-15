package br.edu.ifpb.dac.falacampus.business.security;

import javax.servlet.http.HttpServletRequest;

import br.edu.ifpb.dac.falacampus.business.security.impl.SystemUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

public interface TokenService {
	
	public String generate(SystemUser user); 
	
	public Claims getClaims(String token) throws ExpiredJwtException;
	
	public boolean isValid(String token);
	
	public String getUsername(String token);
	
	public Long getUserId(String token);
	
	public String get(HttpServletRequest request);

}
