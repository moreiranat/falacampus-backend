package br.edu.ifpb.dac.falacampus.presentation.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.dac.falacampus.exceptions.BadArgumentsException;
import br.edu.ifpb.dac.falacampus.exceptions.InternalException;
import br.edu.ifpb.dac.falacampus.exceptions.ResourceNotFoundException;

//criação de um serviço no Controller para lançar as exceções personalizadas
//@RestController
public class ExceptionController {

	// Esse metodo espera 3 string possiveis, not_found, bad_arguments e qualquer
	// coisa
	// para cada argumento recebido ele vai lançar uma exception diferente
	@GetMapping("/exception/{exception_id}")
	public void getSpecificException(@PathVariable("exception_id") String pException) {
		if ("not_found".equals(pException)) {
			throw new ResourceNotFoundException("resource not found");
		} else if ("bad_arguments".equals(pException)) {
			throw new BadArgumentsException("bad arguments");
		} else {
			throw new InternalException("internal error");
		}
	}

	@GetMapping("/exception/throw")
	public void getException() throws Exception {
		throw new Exception("error");
	}

}
