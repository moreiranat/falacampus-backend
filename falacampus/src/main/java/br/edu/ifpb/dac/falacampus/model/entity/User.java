package br.edu.ifpb.dac.falacampus.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.edu.ifpb.dac.falacampus.model.enums.Role;

@Entity
public class User implements UserDetails {
	

	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	
	//@NotNull
	@NotBlank
	@Size(min=2, max=50)
	@Column(name = "user_name")
	private String name;
	
	//@NotNull
	@NotBlank
	@Column(name = "user_email")
	@Pattern (regexp="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "Digite um e-mail padrão válido: _@_._")
	private String email;
	
	@NotNull
	//@NotBlank
	@Column(name = "user_registration")
	private Long registration;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "user_role")
	private Role role = Role.STUDENTS;
	
	//@NotNull
	@NotBlank
	@Size(min=8, max=30)
	@Column(name = "user_password")
	private String password;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "departament_id")
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

	@Override
	public Collection<? extends GrantedAuthority>  getAuthorities() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}
	
	

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
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

	public void setRole(List<SystemRole> role2) {
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

	public void setDepartament(Departament departament) {
		this.departament = departament;
	}

	public void setRole(Role students) {
		
		
	}



	
	
}
