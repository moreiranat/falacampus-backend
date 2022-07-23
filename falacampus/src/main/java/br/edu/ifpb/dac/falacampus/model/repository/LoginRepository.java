package br.edu.ifpb.dac.falacampus.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.dac.falacampus.model.entity.Login;
import br.edu.ifpb.dac.falacampus.model.entity.User;

public interface LoginRepository  extends JpaRepository<Login, Long> {

}
