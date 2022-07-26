package br.edu.ifpb.dac.falacampus.model.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Login implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long registration;
	
	private String username;
	
	private String password;
	
	public Login() {
		
	}

	public Login(Long registration, String username, String password) {
		this.registration =registration;
		this.username = username;
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getRegistration() {
		return registration;
	}

	public void setId(Long registration) {
		this.registration = registration;
	}

	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(registration, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		return Objects.equals(registration, other.registration) && Objects.equals(password, other.password)
				;
	}

}
