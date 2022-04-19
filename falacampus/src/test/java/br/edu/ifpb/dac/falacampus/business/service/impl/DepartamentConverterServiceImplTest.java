package br.edu.ifpb.dac.falacampus.business.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ifpb.dac.falacampus.business.service.DepartamentConverterService;
import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.presentation.dto.DepartamentDto;

class DepartamentConverterServiceImplTest {
	/*
	 * @Test void test() { fail("Not yet implemented"); }
	 */
	private static DepartamentConverterService departamentConvertServiceImpl;
    private static DepartamentDto dto;
    private static Departament departament;
    

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        
        departamentConvertServiceImpl = new DepartamentConverterServiceImpl();
        departament = new Departament();
        dto = new DepartamentDto();
        
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
    
    //@Test
    //void departamentToDTOTest2()
    
    
    //@Test
    //void dtoToDepartamentTest()
    

}
