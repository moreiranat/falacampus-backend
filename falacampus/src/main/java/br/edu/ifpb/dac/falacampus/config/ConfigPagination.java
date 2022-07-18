package br.edu.ifpb.dac.falacampus.config;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;

import br.edu.ifpb.dac.falacampus.model.entity.Comment;

public class ConfigPagination {
	
	private List<Comment> itens;
	private Pagination pagination;

	public ConfigPagination(Page<Comment> commentPage) {
	    this.itens = commentPage.getContent();
	    this.pagination = new Pagination(
	            commentPage.getPageable().getPageNumber(),
	            commentPage.getPageable().getPageSize(),
	            commentPage.getTotalPages(),
	            commentPage.getTotalElements()
	    );
	}
	
	public ConfigPagination() {}

	public List<Comment> getItens() {
		return itens;
	}

	public void setItens(List<Comment> itens) {
		this.itens = itens;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	@Override
	public int hashCode() {
		return Objects.hash(itens, pagination);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConfigPagination other = (ConfigPagination) obj;
		return Objects.equals(itens, other.itens) && Objects.equals(pagination, other.pagination);
	}
	
	

}
