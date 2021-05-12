package com.sample.app.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.sample.app.entity.WorkingIn;

public interface WorkingInRepository extends ArangoRepository<WorkingIn, String>{

}
