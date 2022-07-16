package br.edu.ifpb.dac.falacampus.business.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import br.edu.ifpb.dac.falacampus.model.entity.SystemUser;
import br.edu.ifpb.dac.falacampus.model.entity.User;



public interface PasswordEnconderService extends PasswordEncoder{
	public void encryptPassword(SystemUser systemUser);

}
