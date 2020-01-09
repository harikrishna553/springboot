package com.sample.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sample.app.entity.Project;
import com.sample.app.model.SearchQuery;
import com.sample.app.model.SortOrder;
import com.sample.app.repository.ProjectRepository;
import com.sample.app.service.ProjectService;
import com.sample.app.specification.SpecificationUtil;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public List<Project> findAll(SearchQuery searchQuery) {
		Specification<Project> spec = SpecificationUtil.bySearchQuery(searchQuery, Project.class);
		PageRequest pageRequest = getPageRequest(searchQuery);

		Page<Project> page = projectRepository.findAll(spec, pageRequest);

		return page.getContent();
	}

	private PageRequest getPageRequest(SearchQuery searchQuery) {

		int pageNumber = searchQuery.getPageNumber();
		int pageSize = searchQuery.getPageSize();

		List<Sort.Order> orders = new ArrayList<>();

		SortOrder sortOrder = searchQuery.getSortOrder();

		if (sortOrder == null) {
			return PageRequest.of(pageNumber, pageSize, null);
		}

		List<String> ascProps = searchQuery.getSortOrder().getAscendingOrder();

		if (ascProps != null && !ascProps.isEmpty()) {
			for (String property : ascProps) {

				if ("projectName".equals(property)) {
					orders.add(Sort.Order.asc("projectId.projectName"));
				} else if ("organization".equals(property)) {
					orders.add(Sort.Order.asc("projectId.organization"));
				} else {
					orders.add(Sort.Order.asc(property));
				}
			}

		}

		List<String> descProps = searchQuery.getSortOrder().getDescendingOrder();

		if (descProps != null && !descProps.isEmpty()) {
			for (String property : descProps) {
				if ("projectName".equals(property)) {
					orders.add(Sort.Order.desc("projectId.projectName"));
				} else if ("organization".equals(property)) {
					orders.add(Sort.Order.desc("projectId.organization"));
				} else {
					orders.add(Sort.Order.desc(property));
				}
			}

		}

		Sort sort = Sort.by(orders);

		return PageRequest.of(pageNumber, pageSize, sort);

	}

}
