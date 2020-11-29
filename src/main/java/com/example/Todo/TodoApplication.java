package com.example.Todo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Todo.domain.Priority;
import com.example.Todo.domain.PriorityRepository;
//import com.example.Todo.domain.Task;
import com.example.Todo.domain.TaskRepository;
//import com.example.Todo.domain.User;
import com.example.Todo.domain.UserRepository;


@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(TaskRepository trepository, PriorityRepository prepository, UserRepository urepository) {
		return(args) -> {
			
			prepository.save(new Priority("Low"));
			prepository.save(new Priority("High"));
			prepository.save(new Priority("Normal"));
			
			};
		}

}
