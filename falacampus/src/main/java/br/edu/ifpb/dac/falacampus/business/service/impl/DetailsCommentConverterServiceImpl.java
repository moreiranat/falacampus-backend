package br.edu.ifpb.dac.falacampus.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.falacampus.business.service.DetailsCommentConverterService;
import br.edu.ifpb.dac.falacampus.model.entity.Comment;
import br.edu.ifpb.dac.falacampus.presentation.dto.DetailsCommentDto;

@Service
public class DetailsCommentConverterServiceImpl implements DetailsCommentConverterService {

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<DetailsCommentDto> detailsCommentToDTO(List<Comment> entities) {
		List<DetailsCommentDto> dtos = new ArrayList<>();

		for (Comment dto : entities) {
			DetailsCommentDto entity = detailsCommentToDTO(dto);
			dtos.add(entity);
		}
		return dtos;
	}

	@Override
	public Comment dtoToDetailsComment(DetailsCommentDto dto) {
		Comment entity = mapper.map(dto, Comment.class);
		return entity;
	}

	@Override
	public DetailsCommentDto detailsCommentToDTO(Comment entity) {
		DetailsCommentDto dto = mapper.map(entity, DetailsCommentDto.class);
		return dto;
	}

}
