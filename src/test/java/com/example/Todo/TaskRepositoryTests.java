package com.example.Todo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.Todo.domain.Priority;
import com.example.Todo.domain.Task;
import com.example.Todo.domain.TaskRepository;


@DataJpaTest
public class TaskRepositoryTests {
	
	@Autowired
    private TaskRepository trepository;

    @Test
    public void findByCreatorShouldReturnTask() {
    	List<Task> tasks = trepository.findByTitle("Test");
        
	    assertThat(tasks).hasSize(1);
	    assertThat(tasks.get(0).getCreator()).isEqualTo("test");
    }
    
    @Test
    public void createNewTask() {
    	Task task = new Task("Test2", null, new Priority("No priority"), "test");
    	trepository.save(task);
    	assertThat(task.getId()).isNotNull();
    }    
    
    @Test
    public void deleteTask() {
    	List <Task> tasks = trepository.findByTitle("Test");
    	trepository.deleteById(tasks.get(0).getId());
    	assertThat(tasks.isEmpty());
    }    
}