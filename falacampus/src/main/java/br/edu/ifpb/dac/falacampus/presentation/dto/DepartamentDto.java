 package br.edu.ifpb.dac.falacampus.presentation.dto;

import java.util.List;
import java.util.stream.Collectors;
import br.edu.ifpb.dac.falacampus.model.entity.Departament;

public class DepartamentDto {
	
	private Long id;
	private String name;
	
	public DepartamentDto() {
		
	}

	public DepartamentDto(Departament departament) {
		this.id = departament.getId();
		this.name = departament.getName();
	}
	
	public static List<DepartamentDto> convert(List<Departament> departament){
		return departament.stream().map(DepartamentDto::new).collect(Collectors.toList());
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
	
}
