package com.example.Todo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.Todo.domain.User;
import com.example.Todo.domain.UserRepository;

@DataJpaTest
public class UserRepositoryTests {
	
	@Autowired
    private UserRepository urepository;

    @Test
    public void findByUsernameShouldReturnEmail() {
        List<User> users = urepository.findUserByEmail("test@todo.fi");
        
        assertThat(users).hasSize(1);
        assertThat(users.get(0).getUsername()).isEqualTo("test");
    }
    
    @Test
    public void createNewUser() {
    	User user = new User("test2", "$2a$10$KVu/rKf1a4TA7igvm.eEbO.LHJEWu1lrix1C/k.OA.NfmT27Jptay", "test2@todo.fi", "ADMIN");
    	urepository.save(user);
    	assertThat(user.getId()).isNotNull();
    }
    
    @Test
    public void deleteUser() {
    	List <User> users = urepository.findUserByEmail("test@todo.fi");
    	urepository.deleteById(users.get(0).getId());
    	assertThat(users.isEmpty());
    } 

}