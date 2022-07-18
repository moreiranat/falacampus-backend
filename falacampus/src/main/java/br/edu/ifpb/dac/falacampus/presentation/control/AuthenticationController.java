package br.edu.ifpb.dac.falacampus.presentation.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import br.edu.ifpb.dac.falacampus.business.service.AuthenticationService;
import br.edu.ifpb.dac.falacampus.business.service.ConverterService;
import br.edu.ifpb.dac.falacampus.business.service.SystemUserConverterService;
import br.edu.ifpb.dac.falacampus.business.service.TokenService;
import br.edu.ifpb.dac.falacampus.model.entity.SystemUser;
import br.edu.ifpb.dac.falacampus.model.interfaces.SystemUserService;
import br.edu.ifpb.dac.falacampus.presentation.dto.LoginDto;
import br.edu.ifpb.dac.falacampus.presentation.dto.SystemUserDto;
import br.edu.ifpb.dac.falacampus.presentation.dto.TokenDto;

@RequestMapping("/api")
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private SystemUserConverterService systemUserConverterService; //private ConverterSystemUser converterSystemUser;
	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginDto dto) { 
		try {
			String token = authenticationService.login(dto.getUsername(), dto.getPassword()); 
			SystemUser entity = systemUserService.findByUserName(dto.getUsername());
			SystemUserDto systemUserDto = systemUserConverterService.systemUserToDTO(entity); 
			
			TokenDto tokenDto = new TokenDto(token, systemUserDto);
			
			return new ResponseEntity(tokenDto, HttpStatus.OK);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/isTokenValid")
	public ResponseEntity isTokenValid(@RequestBody TokenDto dto) {
		try {
			boolean isTokenValid = tokenService.isValid(dto.getToken());
			
			return new ResponseEntity(isTokenValid, HttpStatus.OK);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
