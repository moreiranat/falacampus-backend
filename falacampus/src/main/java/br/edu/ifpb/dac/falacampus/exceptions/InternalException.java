package br.edu.ifpb.dac.falacampus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Mapeamento de exceções para códigos de resposta HTTP (Internal Server Error)
//Criação de exceções personalizadas e uso da anotação ResponseStatus 

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalException extends RuntimeException {
	
	public InternalException(String message) {
		super(message);
	}
}
