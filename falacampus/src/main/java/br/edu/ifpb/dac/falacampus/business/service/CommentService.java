package br.edu.ifpb.dac.falacampus.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.falacampus.model.entity.Answer;
import br.edu.ifpb.dac.falacampus.model.entity.Comment;
import br.edu.ifpb.dac.falacampus.model.entity.Departament;
import br.edu.ifpb.dac.falacampus.model.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	public Answer save(Answer answer) {
		return null;
	}
	
	public void deleteById(Long id) {
		
	
	}

	public Answer update(Answer answer) {
		return null;
	}
	
	public Answer update(Long id) {
				
		return null;
	}
	
	public Answer findById(Long id) {
		return null;
		
	}

	public Iterable<Answer> findAll() {
		return null;
	}
	
	public List<Answer> find(Answer filter) {
		
		return null;
	}


}
