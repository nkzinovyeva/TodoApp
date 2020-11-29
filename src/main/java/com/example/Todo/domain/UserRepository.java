package com.example.Todo.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends CrudRepository <User, Long> {
	User findByUsername(String username);
	User findByEmail(String email);
}