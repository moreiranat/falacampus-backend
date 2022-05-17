package br.edu.edu.dac.testes.it;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.function.IntPredicate;

import javax.annotation.ManagedBean;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;

import org.apache.tomcat.util.http.parser.MediaType;
import org.assertj.core.api.Assertions;
import org.hibernate.mapping.List;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.runner.RunWith;
import org.mapstruct.MapperConfig;
import org.mapstruct.control.MappingControl.Use;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.rsocket.context.LocalRSocketServerPort;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.webservices.server.AutoConfigureMockWebServiceClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.ifpb.dac.falacampus.business.service.UserService;
import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.model.entity.User;

import br.edu.ifpb.dac.falacampus.model.repository.UserRepository;
import br.edu.ifpb.dac.falacampus.presentation.control.UserController;
import br.edu.ifpb.dac.falacampus.presentation.dto.UserDto;
import junit.framework.Assert;
@MapperConfig
@SpringBootTest
@FixMethodOrder
@AutoConfigureMockWebServiceClient
@PersistenceContext

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { UserRepository.class })

@WebAppConfiguration(value = "/api")

@RunWith(SpringRunner.class)
class TesteControllerUser {
	/* @ Ptricia */
	 private static final String BASE_URL = "http://localhost:8080";
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	private  MvcResult mvcResult ;
	@Mock
	private Departament departament;
	@Mock
	private UserController userController;
	@Mock
	private UserService userService;
	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}
	

	/*
	 *  verificando se existe um bean UseController. java no contexto da
	 * web. Isso garante que os beans Spring sejam carregados
	 */
	//@Test
	public void user_whenServletContext_controller() {
		ServletContext servletContext = webApplicationContext.getServletContext();

		Assert.assertNotNull(servletContext);
		Assert.assertTrue(servletContext instanceof MockServletContext);
		Assert.assertNotNull(webApplicationContext.getBean("userController"));
	}
	
	/*
	 * verificará se o status HTTP da resposta é Ok (200). Isso garante que a
	 * solicitação foi executada com sucesso.
	 */
	
	@Test
	
	public void user_whenMockMVC_thenVerifyResponse() {
	   
		try {
			mvcResult = this.mockMvc.perform(get("/user"))
			  .andDo(print()).andExpect(status().isOk())
			  .andExpect(jsonPath("$.message").value("fala campus!!!"))
			  .andReturn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    Assert.assertEquals("application/json;charset=UTF-8", 
	    mvcResult.getResponse().getContentType());
	}
	
	
	  
	 @DisplayName("list retuns list of user")
	 //@Test
	  void listTestUserController() {
	  
	  JpaRepository<User, Long> userRepository = null;
	  userRepository.findAll(); // List<User>userList =
	  IntPredicate userController = null;
	  UserService  userService= new UserService();
	
	  Assertions.assertThat(userController).isNotNull();
	  }
	  
		/*
		 * isso enviará uma solicitação como “/usertWithPathVariable/falacampus.”
		 * 
		 * onde  se torna mais fácil em relação à legibilidade e saber quais parâmetros
		 * são definidos dinamicamente da URL.
		 */
	  @Test
	  public void userTest_whenMockMVC_thenResponseOK() {
	      try {
			this.mockMvc
			    .perform(get("/api/user", "patricia"))
			    .andDo(print()).andExpect(status().isOk())
			    
			    .andExpect(content().contentType("application/json;charset=UTF-8"))
			    .andExpect(jsonPath("$.message").value("fala campus!!!"));
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	  }
	  
	  @Test
	  public void user_WithPost_whenMockMVC_thenVerifyResponse() {
	      try {
			this.mockMvc.perform(post("/user")).andDo(print())
			    .andExpect(status().isOk()).andExpect(content()
			    .contentType("application/json;charset=UTF-8"))
			    .andExpect(jsonPath("$.message").value("fala campus!!!"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	////////////////
	  
	  
	  @Test
	  public void list_whenMockMVC_thenVerifyResponse() throws Exception {
		  MvcResult mvcResult = null;
		  try {
		       mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/user")
		    		   .accept(org.springframework.http.MediaType.APPLICATION_JSON))  
		    		   .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		    

		} catch (Exception e) {
			System.out.println(e);
		}
	    		 
	     assertEquals("application/fala campus;charset=UTF-8", mvcResult.getResponse().getContentType());
	  }
	  
	  @Test
	    public void testarListaUser() throws Exception {
	        this.mockMvc.perform(MockMvcRequestBuilders.get("/user")) //teste para acessar o endpoint
	                .andDo(MockMvcResultHandlers.print()) // print do resultado do teste
	                .andExpect(MockMvcResultMatchers.jsonPath("/api").isArray()); // verificando se o endpoint retorna um array
	    }

	  
	  @Test
		public void testUser() throws Exception {
			mockMvc.perform(get("/user")).andExpect(status().isOk())
					.andDo(print())
					.andExpect(content().contentType(""))
					.andExpect(jsonPath("").value("1"))
					.andExpect(jsonPath("").value("user"))
					.andExpect(jsonPath("").value(30));

		}
	  
	


	 
}
