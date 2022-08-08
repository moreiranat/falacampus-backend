package br.edu.ifpb.dac.falacampus.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.falacampus.model.entity.Departament;

@Repository
public interface DepartamentRepository extends JpaRepository<Departament, Long> {

	public Departament findByName(String name);
}
