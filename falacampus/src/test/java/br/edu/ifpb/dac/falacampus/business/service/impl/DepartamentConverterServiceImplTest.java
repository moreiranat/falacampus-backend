package br.edu.ifpb.dac.falacampus.business.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ifpb.dac.falacampus.business.service.DepartamentConverterService;
import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.model.entity.User;
import br.edu.ifpb.dac.falacampus.model.enums.Role;
import br.edu.ifpb.dac.falacampus.presentation.dto.DepartamentDto;

class DepartamentConverterServiceImplTest {
	
	private static DepartamentConverterService departamentConvertServiceImpl;
	private static DepartamentConverterService departamentConverterService;
    private static DepartamentDto dto;
    private static Departament departament;
    private static List<User> userList;
    private static User user;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        

    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        departamentConvertServiceImpl = new DepartamentConverterServiceImpl();
        departament = new Departament();
        dto = new DepartamentDto();

        departament.setId(1L);
        departament.setName("Biblioteca");
        
        user.setId(1L);
        user.setName("Maria");
        user.setPassword("1234");
        user.setEmail("maria@email.com");
        user.setRegistration(123L);
        user.setRole(Role.STUDENT);
        user.setDepartament(departament);

        userList.add(user);
        departament.setUsers(userList);

        dto.setId(1L);
        dto.setName("Biblioteca");
    }

    @AfterEach
    void tearDown() throws Exception {
    }
    
    @Test
    void departamentToDTOTest() {    
    
        assertEquals(DepartamentDto.class, departamentConvertServiceImpl.departamentToDTO(departament).getClass());
    
    }
    
    @Test
    void dtoToDepartamentTest() {    
    
        assertEquals(departament.getClass(), departamentConvertServiceImpl.dtoToDepartament(dto).getClass());
    
    }
    
    @Test
    public void departamentToDto() {
        DepartamentDto departamentDtoConverted = departamentConverterService.departamentToDTO(departament);
        assertAll("departamentToDto",
            () -> assertEquals(departamentDtoConverted.getId(), departament.getId()),
            () -> assertEquals(departamentDtoConverted.getName(), departament.getName())
        );
    }
    
    @Test
    public void dtoToDepartament() {
        Departament convertedDepartament = departamentConverterService.dtoToDepartament(dto);
        assertAll("dtoToDepartament",
                () -> assertEquals(convertedDepartament.getId(), dto.getId()),
                () -> assertEquals(convertedDepartament.getName(), dto.getName())
        );
    }


}
