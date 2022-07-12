package br.edu.ifpb.dac.falacampus.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.falacampus.model.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	@Query(value = "SELECT obj FROM Comment obj JOIN FETCH obj.author JOIN FETCH obj.departament JOIN FETCH obj.answer")
	public List<Comment> findCommentsDatas(List<Comment> comments);

}













//@Query("SELECT * FROM comment INNER JOIN user ON comment.comment_id = user.user_id INNER JOIN departament ON departament.departament_id = user.departament_id INNER JOIN answer ON answer.answer_id = user.departament_id")

