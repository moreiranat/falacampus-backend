package br.edu.ifpb.dac.falacampus.business.service;

import br.edu.ifpb.dac.falacampus.model.entity.SystemUser;

public interface AuthenticationService {
	
	public String loginLocal (String email,String password);
	public SystemUser getLoggedUser();

}
