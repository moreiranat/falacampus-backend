package br.edu.ifpb.dac.falacampus.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.falacampus.model.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
