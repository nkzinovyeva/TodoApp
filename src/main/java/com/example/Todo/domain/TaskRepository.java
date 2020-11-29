package com.example.Todo.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TaskRepository extends  JpaRepository<Task, Long> {
	List<Task> findByTitle(@Param("title") String title);
	List<Task> findByCreator(@Param("creator") String creator);
	List<Task> findByCreatorOrderByTargetDateAsc(@Param("creator") String creator);  
}