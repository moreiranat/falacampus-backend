package br.edu.ifpb.dac.falacampus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Mapeamento de exceções para códigos de resposta HTTP (Not Found)
//Criação de exceções personalizadas e uso da anotação ResponseStatus 

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException(String message) {
        super(message);
    }

}
