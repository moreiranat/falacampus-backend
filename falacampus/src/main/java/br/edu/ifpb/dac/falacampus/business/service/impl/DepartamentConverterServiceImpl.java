package br.edu.ifpb.dac.falacampus.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.falacampus.business.service.DepartamentConverterService;
import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.model.entity.User;
import br.edu.ifpb.dac.falacampus.presentation.dto.DepartamentDto;
import br.edu.ifpb.dac.falacampus.presentation.dto.UserDto;

@Service
public class DepartamentConverterServiceImpl implements DepartamentConverterService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
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
		
		Departament entity = modelMapper.map(dto, Departament.class);
		//Departament entity = new Departament();
		
		//entity.setId(dto.getId());
		//entity.setName(dto.getName());
		
		return entity;
	}

	@Override
	public DepartamentDto departamentToDTO(Departament entity) {
		
		DepartamentDto dto = modelMapper.map(entity, DepartamentDto.class);
		//DepartamentDto dto = new DepartamentDto();
		
		//dto.setId(entity.getId());
		//dto.setName(entity.getName());
		
		return dto;
	}
	
}
