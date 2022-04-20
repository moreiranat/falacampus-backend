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
import br.edu.ifpb.dac.falacampus.presentation.dto.DepartamentDto;

class DepartamentConverterServiceImplTest {
	
	private static DepartamentConverterService departamentConvertServiceImpl;
	private static DepartamentConverterService departamentConverterService;
    private static DepartamentDto dto;
    private static Departament departament;
    private static List<User> userList;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        
        departamentConvertServiceImpl = new DepartamentConverterServiceImpl();
        departament = new Departament();
        dto = new DepartamentDto();

        departament.setId(1L);
        departament.setName("Biblioteca");
        departament.setUsers(userList);
        
        dto.setId(1L);
        dto.setName("Biblioteca");
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
