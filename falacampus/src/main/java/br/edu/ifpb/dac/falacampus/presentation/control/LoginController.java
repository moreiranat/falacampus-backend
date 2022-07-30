package br.edu.ifpb.dac.falacampus.presentation.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import br.edu.ifpb.dac.falacampus.business.service.ConverterService;
import br.edu.ifpb.dac.falacampus.business.service.LoginService;
import br.edu.ifpb.dac.falacampus.business.service.UserConverterService;
import br.edu.ifpb.dac.falacampus.business.service.UserService;
import br.edu.ifpb.dac.falacampus.business.service.impl.LoginServiceImpl;
import br.edu.ifpb.dac.falacampus.business.service.impl.UserServiceImpl;
import br.edu.ifpb.dac.falacampus.model.entity.User;
import br.edu.ifpb.dac.falacampus.presentation.dto.LoginDto;
import br.edu.ifpb.dac.falacampus.presentation.dto.TokenDto;
import br.edu.ifpb.dac.falacampus.presentation.dto.UserDto;

@RestController
@RequestMapping("/api")
@Scope(value = WebApplicationContext.SCOPE_SESSION)

public class LoginController {
	@Autowired
	private LoginService loginService;
	@Autowired
	private UserConverterService userConverterService;
	
	@Autowired
	private UserService userService;

	
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginDto dto) {
		try {
			String token = loginService.suapLogin(dto.getUsername(), dto.getPassword());
					
					//suapLogin(dto.getUsername(), dto.getPassword());
			
			User entity  =  userService.findByRegistration(Long.parseLong(dto.getUsername())).get();
			UserDto userDto = userConverterService.userToDTO(entity);
			
			TokenDto tokenDto = new TokenDto(token, userDto);
			
			return new ResponseEntity(tokenDto, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
}
	}
	
	
	
	
	
