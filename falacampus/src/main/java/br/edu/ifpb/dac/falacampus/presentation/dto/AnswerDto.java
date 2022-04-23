package br.edu.ifpb.dac.falacampus.presentation.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import br.edu.ifpb.dac.falacampus.model.entity.Answer;
import lombok.*;

public class AnswerDto {
	
	private Long id;
	
	@NotNull
	@NotEmpty
	@Size(min = 10, max=255)
	private String message;
	
	@NotNull
	@NotEmpty
	private Long commentId;
	
	@NotNull
	@NotEmpty
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDateTime creationDate;
	
	@NotNull
	@NotEmpty
	@Size(min=2, max=50)
	private String authorName;
	
	public AnswerDto() {
		
	}
	
	public AnswerDto (Answer answer) {
		this.id = answer.getId();
		this.message = answer.getMessage();
		this.commentId = answer.getComment().getId();
		this.creationDate = answer.getCreationDate();
		this.authorName = answer.getAuthor().getName();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public static List<AnswerDto> converter(List<Answer> answers) {
		return answers.stream().map(AnswerDto::new).collect(Collectors.toList());
	}

}
