package br.edu.ifpb.dac.falacampus.business.service;

import java.util.Map;

public interface SuapService {
	public static final String OBTAIN_TOKEN_URL ="https://suap.ifpb.edu.br/api/jwt/obtain_token/";
	public static final String EMPLOYEES_URL = "https://suap.ifpb.edu.br/api/recursos-humanos/servidores/v1/";
	public static final String STUDENS_URL ="https://suap.ifpb.edu.br/apiensino/alunos/v1/";
	
	public static final String USERNAME_JSON_FIELD ="username";
	public static final String PASSWORD_JSON_FIELD = "password";
	

	public static final String TOKEN_HEADER_NAME ="Authorization";
	public static final String TOKEN_HEADER_VALUE = "JWT %s";
	
	public static final Map<String ,String> DEFAULT_HEADERS =Map.of("Content-Type","application/json");
	
	public String login (String username ,String password);
	
	public String findEmployee (String token,String username);
	
	public String findEmployee (String token);
	
	public String findStudent (String token,String username);
	
	public String findStudent (String token);
	
	public String findUser (String token,String username);
	
	
	
	
	
	

}
