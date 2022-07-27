package br.edu.ifpb.dac.falacampus.presentation.control;

import java.util.List;

import javax.validation.Valid;

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

import br.edu.ifpb.dac.falacampus.business.service.CommentService;
import br.edu.ifpb.dac.falacampus.business.service.DepartamentConverterService;
import br.edu.ifpb.dac.falacampus.business.service.DepartamentService;
import br.edu.ifpb.dac.falacampus.business.service.UserConverterService;

import br.edu.ifpb.dac.falacampus.exceptions.CommentCannotUpdateException;
import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.model.entity.User;
import br.edu.ifpb.dac.falacampus.model.enums.Role;
import br.edu.ifpb.dac.falacampus.presentation.dto.DepartamentDto;
import br.edu.ifpb.dac.falacampus.presentation.dto.UserDto;

@RestController
@RequestMapping("/api/departament")
public class DepartamentController {

	@Autowired
	private DepartamentConverterService departamentConvertService;

	@Autowired
	private DepartamentService departamentService;

//	@Autowired
//	private UserConverterService userConverterService;
//
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private User user;
//
//	@Autowired
//	private UserDto userDto;

	@PostMapping
	public ResponseEntity save(@RequestBody @Valid DepartamentDto dto) {
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
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody @Valid DepartamentDto dto) {
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
			// User userEntity = userService.
			// UserDto userDto = new UserDto();
			// userDto.getDepartamentId();

			/*
			 * Lógica
			 * 
			 * SE id de Departament de relaciona com um User ENTÃO Departament não pode ser
			 * deletado >> Ou seja << SE Departament é chave estrangeira de um User ENTÃO
			 * User não pode ser deletado
			 *
			 */
			/*
			 * ResponseEntity userWithDepartament = findUserByDepartament(id); User author =
			 * userService.find(userWithDepartament.);
			 * 
			 */ 
//			 if (!findUserByDepartament(id).equals(HttpStatus.OK)) {
//			  
//			 } else {
//					throw new Exception();
//			}

			departamentService.deleteById(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

//	@GetMapping
//	private ResponseEntity findUserByDepartament(
//			@RequestParam(value = "departamentId", required = false) Long departamentId) {
//
//		try {
//
//			User filter = new User();
//			Departament departament = departamentService.findById(departamentId);
//
//			if (departament == null) {
//				throw new IllegalStateException(
//						String.format("Could not find any departament whit id=%1", departamentId));
//			}
//
//			filter.setDepartament(departament);
//
//			List<User> entities = userService.find(filter);
//			List<UserDto> dtos = userConverterService.userToDTOList(entities);
//
//			return ResponseEntity.ok(dtos);
//
//		} catch (Exception e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//	}

	@GetMapping
	public ResponseEntity findByFilter(@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "name", required = false) String name) {

		try {

			Departament departamento = new Departament();
			departamento.setId(id);
			departamento.setName(name);

			List<Departament> entities = departamentService.find(departamento);
			List<DepartamentDto> dtos = departamentConvertService.departamentToDTO(entities);
			return ResponseEntity.ok(dtos);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public Departament findById(@PathVariable("id") Long id) throws Exception {

		Departament result = departamentService.findById(id);

		if (result == null) {
			throw new Exception("Departament not exist!");

		} else {
			return result;
		}
	}
	
	//FIND ALL
	@GetMapping("/all")
	public List<Departament> findAll() throws Exception {

		List<Departament> result = departamentService.findAll();

		if (result.isEmpty()){
			throw new Exception("List is empty!");

		} else {
			return departamentService.findAll();	
		}
	}

}
