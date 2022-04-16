package br.edu.ifpb.dac.falacampus.presentation.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.dac.falacampus.business.service.DepartamentService;
import br.edu.ifpb.dac.falacampus.business.service.impl.DepartamentConverterService;
import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.presentation.dto.DepartamentDto;


@RestController
@RequestMapping("/api/departament")
public class DepartamentController {
	@Autowired(required = true)
	private DepartamentService departamentService;

	@Autowired(required = true)
	private DepartamentConverterService departamentConvertService;

	@PostMapping(value = "departament")

	public ResponseEntity<Departament> salve(@RequestBody Departament departamento) {
		try {
			Departament depar = departamentService.save(departamento);
			return new ResponseEntity<>(depar, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<HttpStatus> deleteDepartament(@PathVariable("id") long id) {
		try {
			departamentService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/departament/{id}")
	public ResponseEntity<Departament> getDetinoById(@PathVariable("id") long id) {
		Optional<Departament> informacoesDepartamentos = Optional.ofNullable(departamentService.findById(id));
		if (informacoesDepartamentos.isPresent()) {
			return new ResponseEntity<>(informacoesDepartamentos.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/departament")
	public ResponseEntity<List<Departament>> getAllDeResponseEntity() {
		try {
			List<Departament> departamentos = new ArrayList<Departament>();

			if (departamentos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(departamentos, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody DepartamentDto dto) {
		try {
			dto.setId(id);
			Departament departament = departamentConvertService.dtoToDepartament(dto);
			departament = departamentService.update(id);
			dto = departamentConvertService.departamentToDTO(departament);

			return ResponseEntity.ok(dto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
