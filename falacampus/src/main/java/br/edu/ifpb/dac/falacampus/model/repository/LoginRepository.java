package br.edu.ifpb.dac.falacampus.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.falacampus.model.entity.Login;
import br.edu.ifpb.dac.falacampus.model.entity.User;
@Repository
public interface LoginRepository  extends JpaRepository<Login, Long> {

}
