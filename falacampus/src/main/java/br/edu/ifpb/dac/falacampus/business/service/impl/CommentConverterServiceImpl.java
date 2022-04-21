package br.edu.ifpb.dac.falacampus.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.falacampus.business.service.AnswerService;
import br.edu.ifpb.dac.falacampus.business.service.CommentConverterService;
import br.edu.ifpb.dac.falacampus.model.entity.Comment;
import br.edu.ifpb.dac.falacampus.presentation.dto.CommentDto;

@Service
public class CommentConverterServiceImpl implements CommentConverterService {
	
	@Autowired
	private AnswerService answerService;

	@Override
	public List<CommentDto> commentToDTOList(List<Comment> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment dtoToComment(CommentDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentDto commentToDTO(Comment entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
