package br.edu.ifpb.dac.falacampus.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.edu.ifpb.dac.falacampus.business.service.DepartamentService;
import br.edu.ifpb.dac.falacampus.business.service.UserService;
import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.presentation.dto.DepartamentDto;
import br.edu.ifpb.dac.falacampus.presentation.dto.UserDto;

@Component
public class DepartamentConverterServiceImpl implements DepartamentConverterService{

	@Override
	@Bean
	public List<DepartamentDto> departamentToDTO(List<Departament> entities) {
		List<DepartamentDto> dtos = new ArrayList<>();
		
		for (Departament dto : entities) {
			DepartamentDto entity = departamentToDTO(dto);
			dtos.add(entity);
		}
		return dtos;
	}

	@Override
	public Departament dtoToDepartament(DepartamentDto dto) {
		Departament entity = new Departament();
		
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		
		return entity;
	}

	@Override
	public DepartamentDto departamentToDTO(Departament entity) {
		DepartamentDto dto = new DepartamentDto();
		
		return dto;
	}
	
}
