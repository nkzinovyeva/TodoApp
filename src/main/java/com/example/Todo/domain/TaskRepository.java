package com.example.Todo.domain;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface TaskRepository extends  JpaRepository<Task, Long> {
	List<Task> findByTitle(@Param("title") String title);
	List<Task> findByCreator(@Param("creator") String creator);
	List<Task> findByCreatorOrderByTargetDateAsc(@Param("creator") String creator);
	//List<Task> findByPriorityOrderByTargetDateAsc(@Param("priority") Priority priority);
	//List<Task> findByFamilyIdOrderByTargetDateAsc(@Param("creator") String creator);
	
	//@Query("select b from Task b where b.start >= ?1 and b.finish <= ?2")
	//public List<Task> findByDateBetween(LocalDateTime start, LocalDateTime end);
	//@Query(value = "SELECT * FROM TASKS WHERE EMAIL_ADDRESS = ?0", nativeQuery = true)
	//User findByEmailAddress(String emailAddress);
	  
	  
}
