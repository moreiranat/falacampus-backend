package br.edu.ifpb.dac.falacampus.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.filter.OncePerRequestFilter;

import br.edu.ifpb.dac.falacampus.business.service.TokenService;
import br.edu.ifpb.dac.falacampus.business.service.UserService;

public class TokenFilter extends OncePerRequestFilter {
	private TokenService tokenService;
	private UserService userService;
	
	
	public TokenFilter(TokenService tokenService,UserService userService) {
		super();
		this.tokenService = tokenService;
		this.userService = userService;
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = tokenService.get(request);
		boolean valid = tokenService.isValid(token);
		
		if(valid) {
			authenticate(token);
		}
		filterChain.doFilter(request, response);
		
	}
	private void authenticate(String token) {
		Long userid = tokenService.getUserId(token);
		//Service userService.findById(userid);
		//ernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
		//curityContextHolder.getContext().setAuthentication(authentication);
		
	}

}
