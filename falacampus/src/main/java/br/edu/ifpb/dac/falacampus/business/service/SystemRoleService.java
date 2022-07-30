package br.edu.ifpb.dac.falacampus.business.service;

import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.falacampus.model.entity.SystemRole;
@Service

public interface SystemRoleService {
	
	public enum AVAILABLE_ROLES { ADMIN, USER, EMPLOYEES, STUDENTS }
	
	public void createDefaultValues();
	
	public SystemRole findByName(String name);

	public SystemRole findDefault();
	
	
	

}
