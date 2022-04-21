package br.edu.ifpb.dac.falacampus.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.falacampus.model.entity.Comment;
import br.edu.ifpb.dac.falacampus.model.repository.AnswerRepository;

@Service
public class AnswerService {
	
	@Autowired
	private AnswerRepository answerRepository;
	
	public Comment save(Comment comment) {
		return null;
	}
	
	public void deleteById(Long id) {
		
	
	}

	public Comment update(Comment comment) {
		return null;
	}
	
	public Comment update(Long id) {
				
		return null;
	}
	
	public Comment findById(Long id) {
		return null;
		
	}

	public Iterable<Comment> findAll() {
		return null;
	}
	
	public List<Comment> find(Comment filter) {
		
		return null;
	}



}
