package br.edu.ifpb.dac.falacampus.presentation.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.dac.falacampus.business.service.UserService;
import br.edu.ifpb.dac.falacampus.business.service.impl.UserConverterService;
import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.model.entity.User;
import br.edu.ifpb.dac.falacampus.presentation.dto.UserDto;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserConverterService userConverterService;
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity save(@RequestBody UserDto dto) {
		try {
			User entity = userConverterService.dtoToUser(dto);
			
			entity = userService.save(entity);

			dto = userConverterService.userToDTO(entity);
			
			return new ResponseEntity(dto, HttpStatus.CREATED);
			
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody UserDto dto) {
		try {
			dto.setId(id);
			User entity = userConverterService.dtoToUser(dto);
			entity = userService.update(entity);
			dto = userConverterService.userToDTO(entity);
			
			return ResponseEntity.ok(dto);
		} catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		try {
			userService.deleteById(id);
			
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity find (
				@RequestParam(value = "id", required = false) Long id,
				@RequestParam(value = "name", required = false) String name,
				@RequestParam(value = "email", required = false) String email,
				@RequestParam(value = "registration", required = false) Long registration,
				@RequestParam(value = "departamentId", required = false) Departament departamentId,
				@RequestParam(value = "departamentName", required = false) Departament departamentName
			) {
		
		try {
			
			User filter = new User();
			filter.setId(id);
			filter.setName(name);
			filter.setEmail(email);
			filter.setRegistration(registration);
			
			List<User> entities = userService.find(filter);
			List<UserDto> dtos = userConverterService.userToDTOList(entities);
			
			return ResponseEntity.ok(dtos);
			
		} catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
