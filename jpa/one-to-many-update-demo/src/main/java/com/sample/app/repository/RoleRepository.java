package com.sample.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.app.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
