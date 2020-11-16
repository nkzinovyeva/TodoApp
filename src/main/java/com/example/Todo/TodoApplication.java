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
			
			prepository.save(new Priority("Low"));
			prepository.save(new Priority("High"));
			prepository.save(new Priority("Normal"));
			
			//User user1 = new User("user", "$2a$10$8G69obUTwd90WeMgcwMbTuii8z68BDUzFmpli55Pe5xj8J1yIJO8K", "user@bookstore.fi", "USER", "123");
			User user2 = new User("admin", "$2a$10$BAvZvAW6Xw8thG7ZZYwYPOTy.l8cbfyfI.gBUAB0pXzpanaQxVgSu", "admin@bookstore.fi", "ADMIN");
			User user3 = new User("test", "$2a$10$8G69obUTwd90WeMgcwMbTuii8z68BDUzFmpli55Pe5xj8J1yIJO8K", "user@bookstore.fi", "ADMIN");
			//urepository.save(user1);
			urepository.save(user2);
			urepository.save(user3);
			
			Task t1 = new Task("one", null,  prepository.findByName("Low").get(0), "user");
			Task t2 = new Task("three", null,  prepository.findByName("High").get(0), "user" );
			Task t3 = new Task("two", null ,  prepository.findByName("Low").get(0), "test");
			Task t4 = new Task("two", null,  prepository.findByName("Low").get(0), "admin");
			
			trepository.save(t1);
			trepository.save(t2);
			trepository.save(t3);
			trepository.save(t4);
			
			};
		}

}
