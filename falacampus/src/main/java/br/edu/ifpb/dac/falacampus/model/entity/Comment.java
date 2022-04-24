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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	@Size(min=5, max=50)
	@Column(name = "comment_title")
	private String title;
	
	@NotNull
	@NotEmpty
	@Size(min = 10, max=255)
	@Column(name = "comment_message")
	private String message;
	
	@Column(name = "comment_creationDate")
	private LocalDateTime creationDate = LocalDateTime.now();
	
	@Enumerated(EnumType.STRING)
	@Column(name = "comment_type")
	private CommentType commentType;

	@Enumerated(EnumType.STRING)
	@Column(name = "comment_status")
	private StatusComment statusComment = StatusComment.NOT_ANSWERED;

	@ManyToOne
	@JoinColumn(name = "comment_author")
	private User author;
	
	@ManyToOne
	@JoinColumn(name = "departament_id")
	private Departament departament;
	
	@OneToOne
	@JoinColumn(name = "answer_id")
	private Answer answer;
	
	@Column(name = "comment_attachment")
	private File attachment;
	
	public Comment() {
		
	}

	public Comment(Long id, String title, String message, CommentType commentType,
			StatusComment statusComment, User author, Departament departament, Answer answer, File attachment) {
		this.id = id;
		this.title = title;
		this.message = message;
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
