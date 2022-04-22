package br.edu.ifpb.dac.falacampus.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.falacampus.business.service.AnswerConverterService;
import br.edu.ifpb.dac.falacampus.model.entity.Answer;
import br.edu.ifpb.dac.falacampus.presentation.dto.AnswerDto;

@Service
public class AnswerConverterServiceImpl implements AnswerConverterService {
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public List<AnswerDto> answerToDTOList(List<Answer> entities) {
		List<AnswerDto> dtos = new ArrayList<>();
		
		for (Answer dto : entities) {
			AnswerDto entity = answerToDTO(dto);
			dtos.add(entity);
		}
		return dtos;
	}

	@Override
	public Answer dtoToAnswer(AnswerDto dto) {
		
		Answer entity = mapper.map(dto, Answer.class);
		
		//Answer entity = new Answer();
		//entity.setId(dto.getId());
		//entity.setMessage(dto.getMessage());
		//entity.setComment(dto.getCommentId().longValue());//Problema na conversão
		//entity.setCreationDate(dto.getCreationDate());
		//entity.setAuthorName(dto.getAuthor().getName()); //Problema na conversão
		
		return entity;
	}

	@Override
	public AnswerDto answerToDTO(Answer entity) {
		
		AnswerDto dto = mapper.map(entity, AnswerDto.class);
		
		//AnswerDto dto = new AnswerDto();
		
		//dto.setId(entity.getId());
		//dto.setMessage(entity.getMessage());	
		//dto.setCommentId(entity.getComment().getId());
		//dto.setCreationDate(entity.getCreationDate());
		//dto.setAuthorName(entity.getAuthor().getName());
		
		return dto;		
		
	}

}
