package br.edu.ifpb.dac.falacampus.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.falacampus.model.entity.Comment;
import br.edu.ifpb.dac.falacampus.presentation.dto.DetailsCommentDto;

@Service
public interface DetailsCommentConverterService {
	
	public List<DetailsCommentDto> detailsCommentToDTO(List<Comment> entities);
	public Comment dtoToDetailsComment(DetailsCommentDto dto);
	public DetailsCommentDto detailsCommentToDTO(Comment entity);

}
