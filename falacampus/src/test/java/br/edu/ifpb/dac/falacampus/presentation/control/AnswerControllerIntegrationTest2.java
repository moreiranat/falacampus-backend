package br.edu.ifpb.dac.falacampus.presentation.control;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.edu.ifpb.dac.falacampus.exceptions.BadArgumentsException;
import br.edu.ifpb.dac.falacampus.exceptions.InternalException;
import br.edu.ifpb.dac.falacampus.exceptions.ResourceNotFoundException;

//Teste de exceções com Spring MockMvc
//testar se o Controller está lançando as exceções corretas

//vamos ver as diferentes respostas do ExceptionController 
//para as exceções personalizadas mapeadas
// --> Para not_found, recebemos um código de resposta 404
// --> Dado o bad_arguments, recebemos um código de resposta 400
// --> Para qualquer outro valor, recebemos um código de resposta 500
// --> Também recebemos um corpo de resposta no formato Json 

@WebMvcTest(ExceptionController.class)
@AutoConfigureMockMvc
class AnswerControllerIntegrationTest2 {
	
	//criar uma instância de MockMvc
	
	@Autowired
	private MockMvc mvc;
	
	//casos de teste para cada um dos valores que a Exception Controller pode receber
	//Com esses testes, estamos verificando se 
	//o código de resposta, o tipo de exceção gerado e as mensagens dessas exceções 
	//são as esperadas para cada um dos valores.
	
	//dado não encontrado quando obter exceção específica, então Not Found Code
	@Test
	public void givenNotFound_whenGetSpecificException_thenNotFoundCode() throws Exception {
	    String exceptionParam = "not_found";

	    //perform() chamará um método de solicitação GET 
	    //podemos ter expectativas de asserção sobre a resposta, 
	    //como seu conteúdo, status HTTP ou cabeçalho.
	    //andExpect() esperará o argumento fornecido.    
	    
	    this.mvc.perform(get("/exception/{exception_id}", exceptionParam)
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isNotFound())
	      .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
	      .andExpect(result -> assertEquals("resource not found", result.getResolvedException().getMessage()));
	}

	@Test
	public void givenBadArguments_whenGetSpecificException_thenBadRequest() throws Exception {
	    String exceptionParam = "bad_arguments";

	    this.mvc.perform(get("/exception/{exception_id}", exceptionParam)
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isBadRequest())
	      .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadArgumentsException))
	      .andExpect(result -> assertEquals("bad arguments", result.getResolvedException().getMessage()));
	}
	
	@Test
	public void givenOther_whenGetSpecificException_thenInternalServerError() throws Exception {
	    String exceptionParam = "dummy";

	    this.mvc.perform(get("/exception/{exception_id}", exceptionParam)
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isInternalServerError())
	      .andExpect(result -> assertTrue(result.getResolvedException() instanceof InternalException))
	      .andExpect(result -> assertEquals("internal error", result.getResolvedException().getMessage()));
	}

}
