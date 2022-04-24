package br.edu.ifpb.dac.falacampus.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.falacampus.model.entity.Answer;
import br.edu.ifpb.dac.falacampus.presentation.dto.AnswerDto;

@Service
public interface AnswerConverterService {
	
	public List<AnswerDto> answerToDTOList(List<Answer> entities);
	public Answer dtoToAnswer(AnswerDto dto);
	public AnswerDto answerToDTO(Answer entity);

}
