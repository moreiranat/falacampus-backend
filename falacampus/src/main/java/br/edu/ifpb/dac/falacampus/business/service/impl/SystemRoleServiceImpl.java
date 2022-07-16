package br.edu.ifpb.dac.falacampus.business.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifpb.dac.falacampus.business.service.SystemRoleService;
import br.edu.ifpb.dac.falacampus.model.entity.SystemRole;
import br.edu.ifpb.dac.falacampus.model.repository.SystemRoleRepository;

public class SystemRoleServiceImpl implements SystemRoleService{
	
	@Autowired
	private SystemRoleRepository systemRoleRepository;

	@Override
	public void createDefaultValues() {
		for (AVAILABLE_ROLES availableRole : AVAILABLE_ROLES.values()) {
			SystemRole role = findByName(availableRole.name());
			
			if(role == null) {
				role = new SystemRole();
				role.setName(availableRole.name());
				systemRoleRepository.save(role);
			}
		}
		
	}

	@Override
	public SystemRole findByName(String name) {
		if(name == null) {
			throw new IllegalStateException("Name cannot be null");
			
		}
		
		Optional<SystemRole> optional = systemRoleRepository.findByName(name);
		
		return optional.isPresent() ? optional.get() : null;
	}
	
	@Override
	public SystemRole findDefault() {
					
		return findByName(AVAILABLE_ROLES.USER.name());
	}
	
	
	


}
