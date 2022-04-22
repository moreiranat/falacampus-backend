package br.edu.ifpb.dac.falacampus.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.falacampus.business.service.AnswerService;
import br.edu.ifpb.dac.falacampus.business.service.CommentConverterService;
import br.edu.ifpb.dac.falacampus.model.entity.Comment;
import br.edu.ifpb.dac.falacampus.presentation.dto.CommentDto;

@Service
public class CommentConverterServiceImpl implements CommentConverterService {
	
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
		Comment entity = new Comment();
		
		entity.setId(dto.getId());
		entity.setTitle(dto.getTitle());	
		entity.setMessage(dto.getMessage());
		entity.setCreationDate(dto.getCreationDate());
				
		return entity;
	}

	@Override
	public CommentDto commentToDTO(Comment entity) {
		CommentDto dto = new CommentDto();
		
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());	
		dto.setMessage(entity.getMessage());
		dto.setCreationDate(entity.getCreationDate());
		
		return dto;
	}

}
