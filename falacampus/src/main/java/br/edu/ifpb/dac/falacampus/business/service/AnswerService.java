package br.edu.ifpb.dac.falacampus.business.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.falacampus.model.entity.Answer;
import br.edu.ifpb.dac.falacampus.model.repository.AnswerRepository;
import br.edu.ifpb.dac.falacampus.presentation.dto.AnswerDto;

@Service
public class AnswerService {

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private ModelMapper mapper;

	public Answer save(Answer answer) {
		return answerRepository.save(answer);
	}

	public Answer save(Answer answer, Long id) {
		return answerRepository.save(answer);
	}

	public Answer saveAnswerDto(AnswerDto answerDto) {

		Answer answer = mapper.map(answerDto, Answer.class);
		return answerRepository.save(answer);

	}
	
	public void deleteById(Long id) {
		Answer answer = findById(id);

		if (answer == null) {
			throw new IllegalStateException(String.format("Could not find a entity with id=%1", id));
		}
		answerRepository.deleteById(id);

	}

	public Answer update(Answer answer) {
		return answerRepository.save(answer);
	}

	public Answer updateAnswerDto(Long id, AnswerDto answerDto) {

		if (id == null) {
			throw new IllegalStateException("Id cannot be null");
		}

		Answer answerUpdate = mapper.map(answerDto, Answer.class);
		answerUpdate.setId(id);

		return answerRepository.save(answerUpdate);
	}

	public Answer findById(Long id) {
		if (id == null) {
			throw new IllegalStateException("Id cannot be null");
		}
		return answerRepository.findById(id).get();

	}

	public List<Answer> findAll() {

		return answerRepository.findAll();
	}

	public List<Answer> find(Answer filter) {

		Example example = Example.of(filter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));

		return answerRepository.findAll(example);
	}

}
