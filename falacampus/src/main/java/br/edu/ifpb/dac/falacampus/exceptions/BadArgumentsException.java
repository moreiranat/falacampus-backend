package br.edu.ifpb.dac.falacampus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Mapeamento de exceções para códigos de resposta HTTP (Bad Request)
//Criação de exceções personalizadas e uso da anotação ResponseStatus 

//@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadArgumentsException extends RuntimeException {
	
	public BadArgumentsException(String message) {
        super(message);
    }

}
