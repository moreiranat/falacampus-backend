package br.edu.ifpb.dac.falacampus.business.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifpb.dac.falacampus.model.entity.Comment;
import br.edu.ifpb.dac.falacampus.model.repository.CommentRepository;
import br.edu.ifpb.dac.falacampus.presentation.dto.CommentDto;
import br.edu.ifpb.dac.falacampus.presentation.dto.DetailsCommentDto;

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
	
	@Transactional(readOnly = true)
	public Page<DetailsCommentDto> findByPage(PageRequest pageRequest){

		Page<Comment> page = commentRepository.findAll(pageRequest);
		commentRepository.findCommentsDatas(page.stream().collect(Collectors.toList()));

		return page.map(x -> new DetailsCommentDto(x));

	}
	
//	public List<Comment> findAll(Sort by) {
//		
//		return commentRepository.findAll();
//	}
	
	public List<Comment> findAll(PageRequest pageRequest) {
		
		return commentRepository.findAll();
	}

//	public List<Comment> findOrderByName(String name) {
//		
//		List<Comment> result = commentRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
//		
//		return result;
//
//		
//	}

}
