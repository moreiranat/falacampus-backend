package br.edu.ifpb.dac.falacampus.business.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.falacampus.model.entity.User;


@Service
public interface PasswordEnconderService extends PasswordEncoder{
	public void encryptPassword(User user);

}
