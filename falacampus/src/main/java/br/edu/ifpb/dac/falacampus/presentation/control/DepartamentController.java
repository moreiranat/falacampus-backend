package br.edu.ifpb.dac.falacampus.presentation.control;

import java.util.List;

import org.springframework.beans.BeanUtils;
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

import br.edu.ifpb.dac.falacampus.business.service.DepartamentService;
import br.edu.ifpb.dac.falacampus.business.service.impl.DepartamentConverterService;
import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.model.entity.User;
import br.edu.ifpb.dac.falacampus.presentation.dto.DepartamentDto;
import br.edu.ifpb.dac.falacampus.presentation.dto.UserDto;

@RestController
@RequestMapping("/api/departament")
public class DepartamentController {
	
	@Autowired
	private DepartamentConverterService departamentConvertService;
	
	@Autowired
	private DepartamentService departamentService;

	@PostMapping
	public ResponseEntity save(@RequestBody DepartamentDto dto) {
		try {
			Departament entity = departamentConvertService.dtoToDepartament(dto);

			entity = departamentService.save(entity);

			dto = departamentConvertService.departamentToDTO(entity);

			return new ResponseEntity(dto, HttpStatus.CREATED);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody DepartamentDto dto) {
		try {
			dto.setId(id);
			Departament departament = departamentConvertService.dtoToDepartament(dto);
			departament = departamentService.update(departament);
			dto = departamentConvertService.departamentToDTO(departament);

			return ResponseEntity.ok(dto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		try {
			departamentService.deleteById(id);

			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("{id}")
    public ResponseEntity<DepartamentDto> getDepartamentById(@PathVariable Long id) {
		Departament departament = departamentService.findById(id);
        if (departament != null) {
        	DepartamentDto departamentDto = new DepartamentDto();
            BeanUtils.copyProperties(departament, departamentDto);
            return ResponseEntity.ok(departamentDto);
        }

        return ResponseEntity.notFound().build();
    }
	
	@GetMapping
	public ResponseEntity find (
		    @RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "name", required = false) String name
			
		) {

		try {

			Departament departamento = new Departament();
			departamento.setId(id);
			departamento.setName(name);
			//departamento.setUsers(users);

			List<Departament> entities = departamentService.find(departamento);
			List<DepartamentDto> dtos = departamentConvertService.departamentToDTO(entities);
			return ResponseEntity.ok(dtos);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
