package br.edu.ifpb.dac.falacampus.business.service;

import br.edu.ifpb.dac.falacampus.model.entity.User;

public interface LoginService {

	User login(Long matricula, String password);

}
