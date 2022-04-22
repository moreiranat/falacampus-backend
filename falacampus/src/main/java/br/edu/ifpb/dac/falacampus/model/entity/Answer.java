package br.edu.ifpb.dac.falacampus.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Answer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "answer_id")
	private Long id;
	
	@NotNull
	@NotEmpty
	private String message;
	
	@NotNull
	@NotEmpty
	@OneToOne
	private Comment comment;
	
	@NotNull
	@NotEmpty
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDateTime creationDate = LocalDateTime.now();
	
	@NotNull
	@NotEmpty
	@ManyToOne
	private User author;
	
	@NotNull
	private Boolean solution = false;
	
	public Answer() {
		
	}

	public Answer(Long id, String message, Comment comment, LocalDateTime creationDate, User author, Boolean solution) {
		super();
		this.id = id;
		this.message = message;
		this.comment = comment;
		this.creationDate = creationDate;
		this.author = author;
		this.solution = solution;
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

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Boolean getSolution() {
		return solution;
	}

	public void setSolution(Boolean solution) {
		this.solution = solution;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, comment, creationDate, id, message, solution);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		return Objects.equals(author, other.author) && Objects.equals(comment, other.comment)
				&& Objects.equals(creationDate, other.creationDate) && Objects.equals(id, other.id)
				&& Objects.equals(message, other.message) && Objects.equals(solution, other.solution);
	}
	
}
