package br.edu.ifpb.dac.falacampus.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.falacampus.business.service.CommentConverterService;
import br.edu.ifpb.dac.falacampus.model.entity.Comment;
import br.edu.ifpb.dac.falacampus.presentation.dto.CommentDto;

@Service
public class CommentConverterServiceImpl implements CommentConverterService {
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<CommentDto> commentToDTOList(List<Comment> entities) {
		List<CommentDto> dtos = new ArrayList<>();
		
		for (Comment dto : entities) {
			CommentDto entity = commentToDTO(dto);
			dtos.add(entity);
		}
		return dtos;
	}

	@Override
	public Comment dtoToComment(CommentDto dto) {
		
		Comment entity = mapper.map(dto, Comment.class);
				
		return entity;
	}

	@Override
	public CommentDto commentToDTO(Comment entity) {
		
		CommentDto dto = mapper.map(entity, CommentDto.class);
				
		return dto;
	}

}
