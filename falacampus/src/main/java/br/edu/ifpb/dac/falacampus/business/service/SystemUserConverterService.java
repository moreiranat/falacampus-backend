package br.edu.ifpb.dac.falacampus.business.service;

import java.util.List;

import br.edu.ifpb.dac.falacampus.model.entity.SystemUser;
import br.edu.ifpb.dac.falacampus.model.entity.User;
import br.edu.ifpb.dac.falacampus.presentation.dto.SystemUserDto;
import br.edu.ifpb.dac.falacampus.presentation.dto.UserDto;

public interface SystemUserConverterService {
	
	public List<SystemUserDto> systemUserToDTOList(List<SystemUserDto> entities);
	public SystemUser dtoToSystemUser(SystemUserDto entity);
	public SystemUserDto userToDTO(SystemUser entity);
}
