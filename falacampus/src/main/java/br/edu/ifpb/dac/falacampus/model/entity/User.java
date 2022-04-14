package br.edu.ifpb.dac.falacampus.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.edu.ifpb.dac.falacampus.model.enums.Role;

public class User {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private Long registration;
	@Enumerated(EnumType.STRING)
	private Role role = Role.ALUNO;
	private String password;
	@ManyToOne
	private List<Departament> departaments = new ArrayList<>();
	
	
	
	public User() {
		
	}
	
	public User(Long id, String name, String email, Long registration, Role role, String password,
			List<Departament> departaments) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.registration = registration;
		this.role = role;
		this.password = password;
		this.departaments = departaments;
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Departament> getDepartaments() {
		return departaments;
	}
	
	public void setDepartaments(List<Departament> departaments) {
		this.departaments = departaments;
	}

	@Override
	public int hashCode() {
		return Objects.hash(departaments, email, id, name, password, registration, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(departaments, other.departaments) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(registration, other.registration)
				&& role == other.role;
	}
	
}
