package br.edu.ifpb.dac.falacampus.presentation.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifpb.dac.falacampus.model.entity.User;
import br.edu.ifpb.dac.falacampus.model.enums.Role;

public class UserDto {
	
	private Long id;
	private String name;
	private String email;
	private Long registration;
	private Role role;
	private Long departamentId;
	private String departamentName;
	
	public UserDto() {
		
	}
	
	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.registration = user.getRegistration();
		this.role = user.getRole();
		this.departamentId = user.getDepartament().getId();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getRegistration() {
		return registration;
	}

	public void setRegistration(Long registration) {
		this.registration = registration;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Long getDepartamentId() {
		return departamentId;
	}

	public void setDepartamentId(Long departamentId) {
		this.departamentId = departamentId.longValue();
	}
	
	public String getDepartamentName() {
		return departamentName;
	}

	public void setDepartamentName(String departamentName) {
		this.departamentName = departamentName;
	}

	public static List<UserDto> converter(List<User> users) {
		return users.stream().map(UserDto::new).collect(Collectors.toList());
	}

}
