package br.edu.ifpb.dac.falacampus.business.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.falacampus.model.entity.Answer;
import br.edu.ifpb.dac.falacampus.model.entity.Comment;
import br.edu.ifpb.dac.falacampus.model.repository.CommentRepository;
import br.edu.ifpb.dac.falacampus.presentation.dto.CommentDto;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}
	
	public Comment saveCommentDto(CommentDto commentDto){
		
		Comment comment = mapper.map(commentDto, Comment.class);		
		return commentRepository.save(comment);
		
	}
	
	public void deleteById(Long id) {
		Comment comment = findById(id);
		
		if(comment == null) {
			throw new IllegalStateException(String.format("Could not find a entity with id=%1", id));
		}
		commentRepository.deleteById(id);
	
	}

	public Comment update(Comment comment) {
		return commentRepository.save(comment);
	}
	
	public Comment updateCommentDto(Long id, CommentDto commentDto) {
		
		if(id == null) {
			throw new IllegalStateException("Id cannot be null");
		}
		
		Comment commentUpdate = mapper.map(commentDto, Comment.class);
		commentUpdate.setId(id);
		
		return commentRepository.save(commentUpdate);
	}	
	
	public Comment findById(Long id) {
		if(id == null) {
			throw new IllegalStateException("Id cannot be null");
		}
		return commentRepository.findById(id).get();
		
	}

	public List<Comment> findAll() {
		
		return commentRepository.findAll();
	}
	
	public List<Comment> find(Comment filter) {
		
		Example example = Example.of(filter, ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		
		return commentRepository.findAll(example);
	}

	public Comment findByName(String authorName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<Comment> getOpcionalComment(Long id) {
		return commentRepository.findById(id);
	}

}
