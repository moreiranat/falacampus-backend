package br.edu.ifpb.dac.falacampus.presentation.control;

import static org.assertj.core.api.Assertions.assertThat;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.edu.ifpb.dac.falacampus.model.repository.UserRepository;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerIntegrationTest2 {
	
	@MockBean
    private UserRepository userRepository;
   
    @Autowired
    UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenUserControllerInjected_thenNotNull() throws Exception {
       assertThat(userController).isNotNull();
    }

    @Test
    public void whenGetRequestToUsers_thenCorrectResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/user?departamentId=1&id=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    
    }
    
    @Test
    public void whenPostRequestToUsersAndValidUser_thenCorrectResponse() throws Exception {
        
        String user ="{ \"name\": \"Maria da Silva\", \"email\": \"maria_silva@gmail.com\", \"registration\": 202114050030, \"role\": \"STUDENT\", \"password\": 12345678, \"departamentId\": 1 }";
        
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/user")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
               
    }
    
    @Test
    public void whenPostRequestToUsersAndInValidUser_thenCorrectReponse() throws Exception {
        String user ="{ \"name\": \"\", \"email\": \"ana@teste.com\", \"registration\": 2504480, \"role\": \"TECHNICIAN\", \"password\": 12345678, \"departamentId\": 1 }";
        
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/user")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())                
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}
