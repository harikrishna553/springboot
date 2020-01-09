package com.sample.app.model;

import java.util.List;

public class SearchQuery {
	private List<SearchFilter> searchFitler;

	private int pageNumber;
	private int pageSize;

	private SortOrder sortOrder;

	public List<SearchFilter> getSearchFitler() {
		return searchFitler;
	}

	public void setSearchFitler(List<SearchFilter> searchFitler) {
		this.searchFitler = searchFitler;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

}