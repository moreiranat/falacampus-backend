package br.edu.ifpb.dac.falacampus.presentation.control;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextChangedEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.edu.ifpb.dac.falacampus.business.service.TokenService;
import br.edu.ifpb.dac.falacampus.business.service.UserService;
import br.edu.ifpb.dac.falacampus.model.entity.User;

public class TokenFilter extends OncePerRequestFilter {
	private TokenService tokenService;
	private UserService userService;
	
	

	public TokenFilter(TokenService tokenService, UserService userService) {
		super();
		this.tokenService = tokenService;
		this.userService = userService;
	}



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarToken(request);
		filterChain.doFilter(request, response);

		boolean valid = tokenService.isValid(token);
		if (valid) {
			recuperarToken(request);
		}

	
	}
	
	  private String recuperarToken(HttpServletRequest httpServletRequest) {
		  
//		   Pelo request eu consigo pegar os dados da requisição inclusive os dados do header
//	          eu chamo o método getHeader(Passando o nome do que eu quero no header) eu consigo
//	          buscar os valores
		  String token = httpServletRequest.getHeader("Authorization");
		  
		  if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
	            return null;
	        }
		  return token.substring(7, token.length());
	  }

	
	

}
