package br.edu.ifpb.dac.falacampus.presentation.control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifpb.dac.falacampus.business.service.DepartamentConverterService;
import br.edu.ifpb.dac.falacampus.business.service.DepartamentService;
import br.edu.ifpb.dac.falacampus.business.service.impl.DepartamentConverterServiceImpl;
import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.presentation.dto.DepartamentDto;

class DepartamentControllerTest {

	DepartamentConverterService departamentConvertServiceImpl ;
	DepartamentService departamentService ;

	DepartamentDto dto;
	DepartamentController departamentController ;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		 departamentController 	= new DepartamentController();
		 departamentConvertServiceImpl= new DepartamentConverterServiceImpl();
		 departamentService= new DepartamentService();
		 dto = new DepartamentDto();
		 
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/*
	 * @Test void saveDepartamentTest() {
	 * 
	 * dto = new DepartamentDto(); // Within a code block, if an assertion fails the
	 * // subsequent code in the same block will be skipped. assertAll("Biblioteca",
	 * () -> { String departamentName = dto.getName();
	 * assertNotNull(departamentName);
	 * 
	 * // Executed only if the previous assertion is valid. assertAll("Biblioteca",
	 * () -> assertTrue(departamentName.startsWith("B")), () ->
	 * assertTrue(departamentName.endsWith("a"))); });
	 * 
	 * }
	 */
	
	@Test
	void standardAssertions() {
		 
		 dto.setId( 1L);
		 dto.setName("Biblioteca");
		assertEquals(dto, departamentController.save(dto));  
		
		
		//assertEquals(4, departamentController.save(dto), "The optional failure message is now the last parameter");
		//assertTrue('a' < 'b', () -> "Assertion messages can be lazily evaluated -- "
				//+ "to avoid constructing complex messages unnecessarily.");
	}

}
