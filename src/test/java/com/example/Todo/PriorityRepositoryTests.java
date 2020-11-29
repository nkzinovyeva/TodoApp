package com.example.Todo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.Todo.domain.Priority;
import com.example.Todo.domain.PriorityRepository;


@DataJpaTest
public class PriorityRepositoryTests {
	
	@Autowired
    private PriorityRepository prepository;

    @Test
    public void findByNameShouldReturnId() {
        List<Priority> priorities = prepository.findByName("Low");
        
        assertThat(priorities).hasSize(1);
        assertThat(priorities.get(0).getPriorityId()).isEqualTo(1);
    }
    
    @Test
    public void createNewPriority() {
    	Priority priority = new Priority("No priority");
    	prepository.save(priority);
    	assertThat(priority.getPriorityId()).isNotNull();
    }    

    @Test
    public void deletePriority() {
    	List <Priority> priorities = prepository.findByName("Low");
    	prepository.deleteById(priorities.get(0).getPriorityId());
    	assertThat(priorities.isEmpty());
    } 

}