package br.edu.ifpb.dac.falacampus.business.service;

import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.falacampus.model.entity.User;
@Service

public interface LoginService {

	User suapLogin(String username, String password) throws NumberFormatException, Exception;

	//User login(String username, String password) throws NumberFormatException;

}
