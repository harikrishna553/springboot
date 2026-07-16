package com.sample.app.model;

import java.util.List;

/**
 * Generic pagination wrapper returned by all paginated MCP tools.
 *
 * <p>
 * This is a <b>learning pattern</b>: rather than returning raw lists, we wrap
 * them in a structured response that includes metadata. AI clients can use this
 * metadata to request additional pages or present summary information to users.
 *
 * <p>
 * Example JSON shape:
 * 
 * <pre>
 * {
 *   "currentPage": 0,
 *   "pageSize": 10,
 *   "totalPages": 5,
 *   "totalElements": 47,
 *   "data": [ ... ]
 * }
 * </pre>
 *
 * @param <T> the type of elements in the page (typically {@link Employee})
 */
public class PagedResponse<T> {

	/** Zero-based index of the current page. */
	private int currentPage;

	/** Number of elements requested per page. */
	private int pageSize;

	/** Total number of pages available (= ceil(totalElements / pageSize)). */
	private int totalPages;

	/** Total number of matching elements across all pages. */
	private long totalElements;

	/** The actual elements for the current page. */
	private List<T> data;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
