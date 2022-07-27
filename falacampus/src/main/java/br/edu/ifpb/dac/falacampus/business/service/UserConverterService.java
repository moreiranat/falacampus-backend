package br.edu.ifpb.dac.falacampus.business.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import br.edu.ifpb.dac.falacampus.model.entity.User;
import br.edu.ifpb.dac.falacampus.presentation.dto.UserDto;

@Service
public interface UserConverterService {
	
	public List<UserDto> userToDTOList(List<User> entities);
	public User dtoToUser(UserDto dto);
	public UserDto userToDTO(User entity);

	
	
	

}
