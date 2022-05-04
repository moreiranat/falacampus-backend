package br.edu.ifpb.dac.falacampus.business.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.model.repository.DepartamentRepository;

class DepartamentServiceTest2 {
	
	@Mock
	private DepartamentRepository departamentRepository;
	
	@Mock
	private DepartamentService departamentService = new DepartamentService();
	
	@Mock
	private List<Departament> departaments;
	
	@BeforeEach
	public void before() {
		this.departamentRepository =  mock(DepartamentRepository.class);
		this.departaments =  mock(List.class);		
	}
 
	@Test
	void testFindAllService() {
		
		when(departamentRepository.findAll()).thenReturn(departaments);		
		when(departaments.size()).thenReturn(10);		
		assertEquals(10, departaments.size());
		verify(departamentRepository.findAll()).size(); //saber se o método findAll foi chamado durante
														//a execução do teste
	}
	
	@Test
	void testDeleteById() {		
		assertThrows(IllegalStateException.class, 
				() -> departamentService.deleteById(null));
	}
	
	@Test
	void testFindById () { //departamentoComIdNuloNaoPodeSerEncontrado
		
		// 1 Forma de fazer
		assertThrows(IllegalStateException.class,
				() -> departamentService.findById(null));
	}
	
	@Test
	void testFindById2 () {
		
		// Outra forma de fazer
		try {
			departamentService.findById(null);
		} catch (Exception e) {
			assertEquals("Id cannot be null", e.getMessage());
		}
	}

	@Test
	void testUpate() {
		when(departaments.get(0)).thenReturn(new Departament (1l, "Coordenação de ADS")); 
		//Mockando a chamada para o método save
		//Quando tentar salvar qualquer departamento (any), vai ser retornado esse departamento
		//instanciado como parâmetro
		when(departamentRepository.save(any(Departament.class))).thenReturn(new Departament(1l, "Biblioteca"));
		
		String updatedDepartamentName ="Biblioteca";
		
		Departament departament = departaments.get(0);
		departament.setName(updatedDepartamentName);		
		
		Departament departamentUpdate = departamentRepository.save(departament);
		String name = departamentUpdate.getName();
		
		assertEquals(updatedDepartamentName, departamentUpdate.getName());
	}

}
