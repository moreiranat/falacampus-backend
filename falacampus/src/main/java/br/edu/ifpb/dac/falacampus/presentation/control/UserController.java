package br.edu.ifpb.dac.falacampus.presentation.control;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.internal.bytebuddy.TypeCache.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
import br.edu.ifpb.dac.falacampus.business.service.UserConverterService;
import br.edu.ifpb.dac.falacampus.business.service.UserService;

import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.model.entity.User;
import br.edu.ifpb.dac.falacampus.model.enums.Role;
import br.edu.ifpb.dac.falacampus.presentation.dto.UserDto;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserConverterService userConverterService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private DepartamentService departamentService;
	
	//SAVE
	@PostMapping
	public ResponseEntity save(@RequestBody @Valid UserDto dto) {
		
		try {
			if (dto.getDepartamentId() == null) {
				throw new IllegalStateException("departamentId cannot be null");
			}
			
			Long departamentId = dto.getDepartamentId();
			Departament departament = departamentService.findById(departamentId);
			
			if(departament == null) {
				throw new IllegalStateException(String.format("Could not find any departament with id=%1", departamentId));
			}
			
			User entity = userConverterService.dtoToUser(dto);
			entity.setDepartament(departament);
			entity = userService.save(entity);
					
			dto = userConverterService.userToDTO(entity);
			
			return new ResponseEntity(dto, HttpStatus.CREATED);
			
		} catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	//PUT
	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody @Valid UserDto dto) {
		try {
			dto.setId(id);
			
			Long departamentId = dto.getDepartamentId();
			Departament departament = departamentService.findById(departamentId);
			
			if(departament == null) {
				throw new IllegalStateException(String.format("Cound not find any departament with id=%1", id));
			}
			
			User entity = userConverterService.dtoToUser(dto);
			entity.setDepartament(departament);
			
			entity = userService.update(entity);
			dto = userConverterService.userToDTO(entity);
			
			return ResponseEntity.ok(dto);
		} catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	//DELETE
	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		try {
			userService.delete(id);
			//.deleteById(id);
			
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	//FIND FILTER
	@GetMapping
	public ResponseEntity findByFilter (
				@RequestParam(value = "id", required = false) Long id,
				@RequestParam(value = "name", required = false) String name,
				@RequestParam(value = "email", required = false) String email,
				@RequestParam(value = "registration", required = false) Long registration,
				@RequestParam(value = "role", required = false) Role role,
				@RequestParam(value = "departamentId", required = false) Long departamentId) {
		
		try {
			
			User filter = new User();
			filter.setId(id);
			filter.setName(name);
			filter.setEmail(email);
			filter.setRegistration(registration);
			filter.setRole(role);
			//.setRole(role);
			
			Departament departament = departamentService.findById(departamentId);
			
			if(departament == null) {
				throw new IllegalStateException(String.format("Could not find any departament whit id=%1", departamentId));
			}
			
			filter.setDepartament(departament);
			
			List<User> entities = (List<User>) userService.find(filter);
					//.find(filter);
			List<UserDto> dtos = userConverterService.userToDTOList(entities);
			
			return ResponseEntity.ok(dtos);
			
		} catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	//FIND ALL
	@GetMapping("/all")
	public List<User> findAll() throws Exception {

		List<User> result = (List<User>) userService.findAll();

		if (result.isEmpty()){
			throw new Exception("List is empty!");

		} else {
			return (List<User>) userService.findAll();	
		}
	}
	
//	@GetMapping("/users")
//	public ResponseEntity execute(
//	         		 @PageableDefault(sort = "name",
//	                 //direction = Sort.Direction.ASC,
//	                 page = 0,
//	                 size = 10) Pageable page){
//
//	      return ResponseEntity.ok(userService.execute(page));
//	  }
	
	
	@GetMapping("/{id}")
	public User findById(@PathVariable("id") Long id) throws Exception {

		User result = userService.findById(id);

		if (result == null){
			throw new Exception("User not exist!");

		} else {
			return result;	
		}
	}
	
//	@GetMapping("/users")
//	public ResponseEntity sortUsers(String name) {
//		
//		Pageable nameAsc = PageRequest.of(0, 10,
//				Sort.by(Sort.Direction.ASC, "name"));
//		Page<User> users = userService.findByName(name,
//				nameAsc);
//		System.out.println(users);
//		System.out.println("Página atual: " + users.getNumber());
//		System.out.println("Total de elementos: " + users.getTotalElements());
//		users.forEach(user -> System.out.println(user.toString()));
//		
//	}
	
}
