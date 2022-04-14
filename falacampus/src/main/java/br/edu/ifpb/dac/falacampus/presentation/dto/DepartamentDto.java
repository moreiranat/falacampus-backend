package br.edu.ifpb.dac.falacampus.presentation.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifpb.dac.falacampus.model.entity.Departament;

public class DepartamentDto {
	
	private Long id;
	private String name;
	private List<UserDto> users;
	
	public DepartamentDto() {
		
	}

	public DepartamentDto(Departament departament) {
		this.id = departament.getId();
		this.name = departament.getName();
		this.users = new ArrayList<>();
		this.users.addAll(departament.getUsers().stream().map(UserDto::new).collect(Collectors.toList()));
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

	public List<UserDto> getUsers() {
		return users;
	}

	public void setUsers(List<UserDto> users) {
		this.users = users;
	}
	
}
