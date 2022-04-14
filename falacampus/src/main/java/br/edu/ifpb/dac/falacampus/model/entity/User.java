package br.edu.ifpb.dac.falacampus.model.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.edu.ifpb.dac.falacampus.model.enums.Role;

@Entity
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
	private Departament departament;
	
	
	
	public User() {
		
	}
	
	public User(Long id, String name, String email, Long registration, Role role, String password,
			Departament departament) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.registration = registration;
		this.role = role;
		this.password = password;
		this.departament = departament;
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
	
	public Departament getDepartament() {
		return departament;
	}
	
	public void setDepartaments(Departament departament) {
		this.departament = departament;
	}

	@Override
	public int hashCode() {
		return Objects.hash(departament, email, id, name, password, registration, role);
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
		return Objects.equals(departament, other.departament) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(registration, other.registration)
				&& role == other.role;
	}
	
}
