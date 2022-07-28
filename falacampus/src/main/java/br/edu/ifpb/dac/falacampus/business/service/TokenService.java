package br.edu.ifpb.dac.falacampus.business.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.falacampus.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

@Service
public interface TokenService {
	
	public String generate(User user); 
	
	public Claims getClaims(String token) throws ExpiredJwtException;
	
	public boolean isValid(String token);
	
	public String getUsername(String token);
	
	public Long getUserId(String token);
	
	public String get(HttpServletRequest request);

}
