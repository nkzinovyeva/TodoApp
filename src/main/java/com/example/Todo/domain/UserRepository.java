package com.example.Todo.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends CrudRepository <User, Long> {
	User findByUsername(String username);
	List<User> findByFamilyID(String familyID);
	//List<User> findByUsername(String username);
	//List<User> findByEmail(String email);
}