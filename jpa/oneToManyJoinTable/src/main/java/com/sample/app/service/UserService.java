package com.sample.app.service;

import com.sample.app.dto.UserDto;
import com.sample.app.entity.User;

public interface UserService {

	User save(UserDto emp);

	User getById(int id);

}
