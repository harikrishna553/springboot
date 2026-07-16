package com.sample.app.util;

import java.util.List;

import com.sample.app.model.PagedResponse;

/**
 * Reusable pagination utility for in-memory collections.
 *
 * <p>
 * This utility centralizes all pagination logic in one place, following the
 * <b>DRY (Don't Repeat Yourself)</b> principle. Every paginated tool delegates
 * here.
 *
 * <p>
 * <b>How pagination works:</b>
 * <ol>
 * <li>Validate that pageNumber ≥ 0 and 0 &lt; pageSize ≤ 100</li>
 * <li>Calculate the start index: {@code pageNumber * pageSize}</li>
 * <li>Calculate the end index:
 * {@code min(start + pageSize, totalElements)}</li>
 * <li>Use {@link List#subList} to slice the full list</li>
 * <li>Wrap the slice in a {@link PagedResponse} with metadata</li>
 * </ol>
 */
public final class PaginationUtil {

	/** Maximum allowed page size to protect against accidentally huge responses. */
	private static final int MAX_PAGE_SIZE = 100;

	// Utility class — no instances allowed
	private PaginationUtil() {
		throw new UnsupportedOperationException("Utility class");
	}

	/**
	 * Validates pagination parameters and returns a page from the given list.
	 *
	 * @param <T>        type of list elements
	 * @param allItems   the full (already filtered) list to paginate
	 * @param pageNumber zero-based page index
	 * @param pageSize   number of items per page (1–100)
	 * @return a {@link PagedResponse} with the requested slice and metadata
	 * @throws InvalidPaginationException if pageNumber &lt; 0 or pageSize is out of
	 *                                    range
	 */
	public static <T> PagedResponse<T> paginate(List<T> allItems, int pageNumber, int pageSize) {

		// ── Validation ──────────────────────────────────────────────────────
		if (pageNumber < 0) {
			throw new RuntimeException("pageNumber must be >= 0 (got " + pageNumber + "). Pagination is zero-indexed.");
		}
		if (pageSize <= 0) {
			throw new RuntimeException("pageSize must be > 0 (got " + pageSize + ").");
		}
		if (pageSize > MAX_PAGE_SIZE) {
			throw new RuntimeException("pageSize must be <= " + MAX_PAGE_SIZE + " (got " + pageSize + ").");
		}

		// ── Slice calculation ────────────────────────────────────────────────
		long totalElements = allItems.size();
		int totalPages = (int) Math.ceil((double) totalElements / pageSize);

		// If the requested page is beyond the last page, return an empty page
		// rather than throwing — this allows iteration to stop gracefully.
		if (totalElements == 0 || pageNumber >= totalPages) {
			PagedResponse<T> response = new PagedResponse<>();

			response.setCurrentPage(pageNumber);
			response.setPageSize(pageSize);
			response.setTotalPages(totalPages);
			response.setTotalElements(totalElements);
			response.setData(List.of());

			return response;
		}

		int fromIndex = pageNumber * pageSize;
		int toIndex = (int) Math.min((long) fromIndex + pageSize, totalElements);

		List<T> pageData = allItems.subList(fromIndex, toIndex);

		PagedResponse<T> response = new PagedResponse<>();

		response.setCurrentPage(pageNumber);
		response.setPageSize(pageSize);
		response.setTotalPages(totalPages);
		response.setTotalElements(totalElements);
		response.setData(pageData);

		return response;
	}
}
