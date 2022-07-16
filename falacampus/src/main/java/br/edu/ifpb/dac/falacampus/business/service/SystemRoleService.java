package br.edu.ifpb.dac.falacampus.business.service;

import br.edu.ifpb.dac.falacampus.model.entity.SystemRole;

public interface SystemRoleService {
	
	public enum AVAILABLE_ROLES { ADMIN, USER }
	
	public void createDefaultValues();
	
	public SystemRole findByName(String name);

	public SystemRole findDefault();
	
	
	

}
