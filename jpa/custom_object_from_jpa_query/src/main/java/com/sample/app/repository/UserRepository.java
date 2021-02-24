package com.sample.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sample.app.model.User;
import com.sample.app.model.UserBasicDetails;
import com.sample.app.repository.interfaces.UserBasicInfo;
import com.sample.app.repository.interfaces.UserBasicInfoOpenProjection;

public interface UserRepository extends CrudRepository<User, Integer> {

	List<UserBasicInfo> findByIdIn(List<Integer> ids);

	List<UserBasicInfoOpenProjection> findByFirstName(String firstName);

	<T> List<T> findByFirstName(String firstName, Class<T> tClass);

	@Query("select new com.sample.app.model.UserBasicDetails(u.id, u.firstName) from User u where u.firstName=:firstName")
	List<UserBasicDetails> getUsersByFirstName(@Param("firstName") String firstName);

}