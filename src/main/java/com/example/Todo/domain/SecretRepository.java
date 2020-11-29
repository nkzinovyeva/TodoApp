package com.example.Todo.domain;

import org.springframework.data.repository.CrudRepository;

public interface SecretRepository extends CrudRepository<Secret, Long> {
	Secret findBySecret(String secret);
}