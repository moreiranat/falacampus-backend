package br.edu.ifpb.dac.falacampus.business.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ifpb.dac.falacampus.business.service.UserConverterService;
import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.model.entity.User;
import br.edu.ifpb.dac.falacampus.model.enums.Role;
import br.edu.ifpb.dac.falacampus.presentation.dto.UserDto;

class UserConverterServiceImplTest {

	  
	private static UserConverterService userConverterService;
    private static UserDto dto;
    private static User user;
    private static Departament dep;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        
        dep = new Departament(1L, "Biblioteca", null);
        userConverterService = new UserConverterServiceImpl();
        user = new User();
        
        user.setId(1L);
        user.setName("Maria");
        user.setEmail("maria@email.com");
        user.setRegistration(1234L);
        user.setRole(Role.STUDENT);
        user.setPassword(null);
        user.setDepartament(dep);
        
        dto = new UserDto();
        dto.setId(1L);
        dto.setName("Maria");
        dto.setEmail("maria@email.com");
        dto.setRegistration(1234L);
        dto.setRole(Role.STUDENT);
        dto.setPassword("123456");
        dto.setDepartamentId(dep.getId().longValue());
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    public void userToDto() {
        UserDto dtoConverted = userConverterService.userToDTO(user);
        assertAll("Testing user to dto",
            () -> assertEquals(dtoConverted.getId(), user.getId()),
            () -> assertEquals(dtoConverted.getName(), user.getName()),
            () -> assertEquals(dtoConverted.getEmail(), user.getEmail()),
            () -> assertEquals(dtoConverted.getRegistration(), user.getRegistration()),
            () -> assertEquals(dtoConverted.getRole(), user.getRole()),
            () -> assertEquals(dtoConverted.getPassword(), user.getPassword()),
            () -> assertEquals(dtoConverted.getDepartamentId().longValue(), user.getDepartament())
        );
    }
    
    @Test
    public void dtoToUser() {
        User userConverted = userConverterService.dtoToUser(dto);
        assertAll("Testing dto to user",
                () -> assertEquals(userConverted.getId(), dto.getId()),
                () -> assertEquals(userConverted.getName(), dto.getName()),
                () -> assertEquals(userConverted.getEmail(), dto.getEmail()),
                () -> assertEquals(userConverted.getRegistration(), dto.getRegistration()),
                () -> assertEquals(userConverted.getRole(), dto.getRole()),
                () -> assertEquals(userConverted.getPassword(), dto.getPassword()),
                () -> assertEquals(userConverted.getDepartament(), dto.getDepartamentId())
        );
    }
}
