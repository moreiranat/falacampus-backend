package br.edu.ifpb.dac.falacampus.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.dac.falacampus.business.security.impl.SystemRole;

public interface SystemRoleRepository extends JpaRepository<SystemRole, Long> {

}
