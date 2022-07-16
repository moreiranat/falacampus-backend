package br.edu.ifpb.dac.falacampus.business.security.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.edu.ifpb.dac.falacampus.business.service.PasswordEnconderService;
import br.edu.ifpb.dac.falacampus.model.entity.SystemUser;


public class PasswordEnconderServiceImpl extends BCryptPasswordEncoder implements PasswordEnconderService {

	@Override
	public void encryptPassword(SystemUser systemUser) {
		if (systemUser.getPassword() != null) {
			String encryptePassword = encode(systemUser.getPassword());
			systemUser.setPassword(encryptePassword);
		}
	}

}
