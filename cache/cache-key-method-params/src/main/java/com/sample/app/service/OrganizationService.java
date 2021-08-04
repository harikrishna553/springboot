package com.sample.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sample.app.model.Organization;

@Service
@CacheConfig(cacheNames = "myOrgCache")
public class OrganizationService {
	private static final List<Organization> orgs = new ArrayList<>();

	static {
		Organization org1 = new Organization(1, "ABC Corp", "Hello ABC Corp");
		Organization org2 = new Organization(2, "DEF Corp", "Hello DEF Corp");
		Organization org3 = new Organization(3, "GHI Corp", "Hello GHI Corp");
		Organization org4 = new Organization(4, "JKL Corp", "Hello JKL Corp");
		Organization org5 = new Organization(5, "MNO Corp", "Hello MNO Corp");

		orgs.add(org1);
		orgs.add(org2);
		orgs.add(org3);
		orgs.add(org4);
		orgs.add(org5);
	}

	@Cacheable(key = "#id")
	public Organization getById(int id) {
		// System.out.println("getById() called");
		for (Organization org : orgs) {
			if (id == org.getId()) {
				return org;
			}
		}

		return null;
	}

	@Cacheable(key = "#name")
	public Organization getByName(String name) {
		// System.out.println("getByName() called");
		for (Organization org : orgs) {
			if (org.getName().equals(name)) {
				return org;
			}
		}

		return null;
	}

	@Cacheable(key = "{#id, #name }")
	public List<Organization> getByNameOrId(int id, String name) {
		List<Organization> myOrgs = new ArrayList<>();

		for (Organization org : orgs) {
			if (org.getName().equals(name)) {
				myOrgs.add(org);
				continue;
			}

			if (org.getId() == id) {
				myOrgs.add(org);
			}
		}

		return myOrgs;
	}

}