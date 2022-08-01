package br.edu.ifpb.dac.falacampus.business.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.falacampus.model.entity.User;

@Service
public interface UserService extends UserDetailsService {

	public User save(User user);
	public User update(User user);
	public void delete(Long id);
	public User findById(Long id);
	//public User findByRegistration(Long registration);
	public Optional<User> findByEmail(String email);
	public Iterable<User>findAll();
	public Iterable<User>find(User filter);

	public User findByName(String name);
	public User findByUserName(String username);
	public User findByToken(String token);
	
}
