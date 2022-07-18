package br.edu.ifpb.dac.falacampus.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.dac.falacampus.business.service.SystemUserConverterService;
import br.edu.ifpb.dac.falacampus.model.entity.SystemUser;
import br.edu.ifpb.dac.falacampus.presentation.dto.SystemUserDto;

public class SystemUserConverterServiceImpl implements SystemUserConverterService{

	@Override
	public List<SystemUserDto> systemUserToDTOList(List<SystemUser> entities) {
		List<SystemUserDto> dtos = new ArrayList<>();
		
		for (SystemUser dto : entities) {
			SystemUserDto entity = systemUserToDTO(dto);
			dtos.add(entity);
		}
		return dtos;
	}

	@Override
	public SystemUser dtoToSystemUser(SystemUserDto dto) {

		SystemUser entity = new SystemUser();
		
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setUserName(dto.getUsername());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		
		return entity;
	}

	@Override
	public SystemUserDto systemUserToDTO(SystemUser entity) {
		
		SystemUserDto dto = new SystemUserDto();
		
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setUserName(entity.getUsername());
		dto.setEmail(entity.getEmail());
				
		return dto;
	}

}
