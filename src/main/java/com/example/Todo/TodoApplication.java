package com.example.Todo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Todo.domain.Priority;
import com.example.Todo.domain.PriorityRepository;
import com.example.Todo.domain.Task;
import com.example.Todo.domain.TaskRepository;
import com.example.Todo.domain.User;
import com.example.Todo.domain.UserRepository;


@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(TaskRepository trepository, PriorityRepository prepository, UserRepository urepository) {
		return(args) -> {
			//Create user and Task for tests
			User user = new User("test", "$2a$10$BAvZvAW6Xw8thG7ZZYwYPOTy.l8cbfyfI.gBUAB0pXzpanaQxVgSu", "test@todo.fi", "ADMIN");
			urepository.save(user);
			
			prepository.save(new Priority("Low"));
			prepository.save(new Priority("High"));
			prepository.save(new Priority("Normal"));
			
			
			Task t1 = new Task("Test", null, prepository.findByName("Low").get(0), "test");
			trepository.save(t1);
			};
		}

}
