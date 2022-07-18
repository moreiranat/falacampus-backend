package br.edu.ifpb.dac.falacampus.config;

import java.util.Objects;

public class Pagination {
	
	private int page;
    private int pageSize;
    private int pagesTotal;
    private long itensTotal;

    public Pagination(int page, int pageSize, int pagesTotal, long itensTotal) {
        this.page = page;
        this.pageSize = pageSize;
        this.pagesTotal = pagesTotal;
        this.itensTotal = itensTotal;
    }

    public Pagination() {}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPagesTotal() {
		return pagesTotal;
	}

	public void setPagesTotal(int pagesTotal) {
		this.pagesTotal = pagesTotal;
	}

	public long getItensTotal() {
		return itensTotal;
	}

	public void setItensTotal(long itensTotal) {
		this.itensTotal = itensTotal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(itensTotal, page, pageSize, pagesTotal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagination other = (Pagination) obj;
		return itensTotal == other.itensTotal && page == other.page && pageSize == other.pageSize
				&& pagesTotal == other.pagesTotal;
	}
    
    

}
