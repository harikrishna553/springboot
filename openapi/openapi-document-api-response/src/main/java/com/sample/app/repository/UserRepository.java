package com.sample.app.repository;

import java.util.HashMap;
import java.util.Map;

import com.sample.app.model.User;

public class UserRepository {

	private static Map<Integer, User> USERS_CACHE = new HashMap<>();

	static {
		for (int i = 1; i < 10; i++) {
			User user = new User(i, "firstName" + i, "lastName" + i);
			USERS_CACHE.put(i, user);
		}
	}

	public static Map<Integer, User> getUSERS_CACHE() {
		return USERS_CACHE;
	}

}
