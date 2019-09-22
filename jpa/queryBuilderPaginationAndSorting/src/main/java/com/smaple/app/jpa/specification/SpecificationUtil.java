package com.smaple.app.jpa.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.sample.app.model.SearchFilter;
import com.sample.app.model.SearchQuery;

public class SpecificationUtil {
	public static <T> Specification<T> bySearchQuery(SearchQuery searchQuery, Class<T> clazz) {

		return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criterailBuilder) -> {

			List<Predicate> predicates = new ArrayList<>();
			List<SearchFilter> searchFilters = searchQuery.getSearchFitler();

			if (searchFilters == null || searchFilters.isEmpty()) {
				return null;
			}

			for (final SearchFilter searchFilter : searchFilters) {
				String property = searchFilter.getProperty();
				Path expression = root.get(property);

				switch (searchFilter.getOperator()) {
				case "=":
					predicates.add(criterailBuilder.equal(expression, searchFilter.getValue()));
					break;
				case "LIKE":
					predicates.add(criterailBuilder.like(expression, "%" + searchFilter.getValue() + "%"));
					break;
				case "IN":
					predicates.add(criterailBuilder.in(expression).value(searchFilter.getValue()));
					break;
				case ">":
					predicates.add(criterailBuilder.greaterThan(expression, (Comparable) searchFilter.getValue()));
					break;
				case "<":
					predicates.add(criterailBuilder.lessThan(expression, (Comparable) searchFilter.getValue()));
					break;
				case ">=":
					predicates.add(
							criterailBuilder.greaterThanOrEqualTo(expression, (Comparable) searchFilter.getValue()));
					break;
				case "<=":
					predicates
							.add(criterailBuilder.lessThanOrEqualTo(expression, (Comparable) searchFilter.getValue()));
					break;
				case "!":
					predicates.add(criterailBuilder.notEqual(expression, searchFilter.getValue()));
					break;
				case "IsNull":
					predicates.add(criterailBuilder.isNull(expression));
					break;
				case "NotNull":
					predicates.add(criterailBuilder.isNotNull(expression));
					break;
				default:
					System.out.println("Predicate is not matched");
					throw new IllegalArgumentException(searchFilter.getOperator() + " is not a valid predicate");
				}

			}

			if (predicates.isEmpty()) {
				return criterailBuilder.conjunction();
			}

			return criterailBuilder.and(predicates.toArray(new Predicate[0]));

		};
	}
}
