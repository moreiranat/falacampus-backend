package br.edu.ifpb.dac.falacampus.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.falacampus.business.service.AnswerConverterService;
import br.edu.ifpb.dac.falacampus.business.service.CommentService;
import br.edu.ifpb.dac.falacampus.model.entity.Answer;
import br.edu.ifpb.dac.falacampus.presentation.dto.AnswerDto;

@Service
public class AnswerConverterServiceImpl implements AnswerConverterService {
	
	@Autowired
	private CommentService commentService;

	@Override
	public List<AnswerDto> answerToDTOList(List<Answer> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Answer dtoToAnswer(AnswerDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnswerDto answerToDTO(Answer entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
