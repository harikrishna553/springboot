package com.sample.app.model;

import java.util.List;

public class SearchQuery {
	private List<SearchFilter> searchFitler;

	public List<SearchFilter> getSearchFitler() {
		return searchFitler;
	}

	public void setSearchFitler(List<SearchFilter> searchFitler) {
		this.searchFitler = searchFitler;
	}

}
