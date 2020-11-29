package com.example.Todo.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PriorityRepository extends JpaRepository<Priority, Long> {
	List<Priority> findByName(String name);
}