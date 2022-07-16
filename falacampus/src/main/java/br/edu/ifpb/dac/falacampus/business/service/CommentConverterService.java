package br.edu.ifpb.dac.falacampus.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.falacampus.model.entity.Comment;
import br.edu.ifpb.dac.falacampus.presentation.dto.CommentDto;

@Service
public interface CommentConverterService {
	
	public List<CommentDto> commentToDTOList(List<Comment> entities);
	public Comment dtoToComment(CommentDto dto);
	public CommentDto commentToDTO(Comment entity);

}
