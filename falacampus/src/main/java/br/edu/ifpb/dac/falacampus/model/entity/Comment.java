package br.edu.ifpb.dac.falacampus.model.entity;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import br.edu.ifpb.dac.falacampus.model.enums.CommentType;
import br.edu.ifpb.dac.falacampus.model.enums.StatusComment;

@Entity
public class Comment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Long id;
	
	@NotNull
	@NotEmpty
	@Column(name = "comment_title")
	private String title;
	
	@NotNull
	@NotEmpty
	@Column(name = "comment_title")
	private String message;
	
	@NotNull
	@NotEmpty
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDateTime creationDate = LocalDateTime.now();
	
	@NotNull
	@NotEmpty
	@Enumerated(EnumType.STRING)
	private CommentType commentType;

	@NotNull
	@NotEmpty
	@Enumerated(EnumType.STRING)
	private StatusComment statusComment = StatusComment.NOT_ANSWERED;

	@NotNull
	@NotEmpty
	@ManyToOne
	@Size(min=2)
	private User author;
	
	@NotNull
	@NotEmpty
	@ManyToOne
	private Departament departament;
	
	@NotNull
	@NotEmpty
	@OneToOne
	private Answer answer;
	
	private File attachment;
	
	public Comment() {
		
	}

	public Comment(Long id, String title, String message, LocalDateTime creationDate, CommentType commentType,
			StatusComment statusComment, User author, Departament departament, Answer answer, File attachment) {
		this.id = id;
		this.title = title;
		this.message = message;
		this.creationDate = creationDate;
		this.commentType = commentType;
		this.statusComment = statusComment;
		this.author = author;
		this.departament = departament;
		this.answer = answer;
		this.attachment = attachment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public CommentType getCommentType() {
		return commentType;
	}

	public void setCommentType(CommentType commentType) {
		this.commentType = commentType;
	}

	public StatusComment getStatusComment() {
		return statusComment;
	}

	public void setStatusComment(StatusComment statusComment) {
		this.statusComment = statusComment;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Departament getDepartament() {
		return departament;
	}

	public void setDepartament(Departament departament) {
		this.departament = departament;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public File getAttachment() {
		return attachment;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(answer, attachment, author, commentType, creationDate, departament, id, message,
				statusComment, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return Objects.equals(answer, other.answer) && Objects.equals(attachment, other.attachment)
				&& Objects.equals(author, other.author) && commentType == other.commentType
				&& Objects.equals(creationDate, other.creationDate) && Objects.equals(departament, other.departament)
				&& Objects.equals(id, other.id) && Objects.equals(message, other.message)
				&& statusComment == other.statusComment && Objects.equals(title, other.title);
	}
	
}
