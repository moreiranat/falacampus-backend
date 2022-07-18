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

import br.edu.ifpb.dac.falacampus.business.service.SystemUserConverterService;
import br.edu.ifpb.dac.falacampus.model.entity.SystemUser;
import br.edu.ifpb.dac.falacampus.model.interfaces.SystemUserService;
import br.edu.ifpb.dac.falacampus.presentation.dto.SystemUserDto;

@RestController
@RequestMapping("/api/systemUser")

public class SystemUserController {
	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private SystemUserConverterService systemUserConverterService;

	@PostMapping
	public ResponseEntity save(@RequestBody SystemUserDto dto) {
		try {
			SystemUser entity = systemUserConverterService.dtoToSystemUser(dto);
			entity = systemUserService.save(entity);
			dto = systemUserConverterService.systemUserToDTO(entity);
			return new ResponseEntity<>(dto, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody SystemUserDto dto) {
		try {
			SystemUser entity = systemUserConverterService.dtoToSystemUser(dto);
			entity = systemUserService.update(entity);
			dto = systemUserConverterService.systemUserToDTO(entity);
			return ResponseEntity.ok(dto);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		try {

			systemUserService.delete(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity find(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "id", required = false) Long id) {

		try {
			SystemUser filter = new SystemUser();
			filter.setId(id);
			filter.setName(name);
			filter.setEmail(email);
			List<SystemUser> entities = systemUserService.find(filter);
			List<SystemUserDto> dtos = systemUserConverterService.systemUserToDTOList(entities);
			return ResponseEntity.ok(dtos);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		}
	}

}
