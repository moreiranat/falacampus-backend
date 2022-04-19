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
        
        dto = new DepartamentDto();
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
    public void departamentToDto() {
        DepartamentDto dtoConverted = departamentConverterService.departamentToDTO(departament);
        assertAll("Testing departament to dto",
            () -> assertEquals(dtoConverted.getId(), departament.getId()),
            () -> assertEquals(dtoConverted.getName(), departament.getName())
        );
    }
    
    @Test
    public void dtoToDepartament() {
        Departament departamentConverted = departamentConverterService.dtoToDepartament(dto);
        assertAll("Testing dto to departament",
                () -> assertEquals(departamentConverted.getId(), dto.getId()),
                () -> assertEquals(departamentConverted.getName(), dto.getName())
        );
    }

    @Test
    void departamentToDTOTest() {    
    
        assertEquals(DepartamentDto.class, departamentConvertServiceImpl.departamentToDTO(departament).getClass());
    
    }
    
    @Test
    void dtoToDepartamentTest() {    
    
        assertEquals(departament.getClass(), departamentConvertServiceImpl.dtoToDepartament(dto).getClass());
    
    }
    
    //@Test
    //void departamentToDTOTest2()
    
    
    //@Test
    //void dtoToDepartamentTest()

}
