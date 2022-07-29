package br.edu.ifpb.dac.falacampus.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.falacampus.model.entity.SystemRole;

public interface SystemRoleRepository extends JpaRepository<SystemRole, Long> {
	
	public Optional<SystemRole> findByName(String name);

}
