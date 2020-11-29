package com.example.Todo.web;

import java.util.Random;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Todo.domain.Secret;
import com.example.Todo.domain.SecretRepository;
import com.example.Todo.domain.SignupForm;
import com.example.Todo.domain.User;
import com.example.Todo.domain.UserRepository;

@Controller
public class UserController {
	@Autowired
	private UserRepository urepository;
	
	@Autowired
	private SecretRepository srepository;
	
	//Sign up handler
	@RequestMapping(value = "/signup")
    public String addUser(Model model){
    	model.addAttribute("signupform", new SignupForm());
        return "signup";
    }
		
	//Save user handler
    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
    	if (!bindingResult.hasErrors()) { // validation errors
    		
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
        					
    			String pwd = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
	
		    	User newUser = new User();
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setEmail(signupForm.getEmail());
		    	newUser.setRole("ADMIN");
		    	
		    	
		    	if ((urepository.findByUsername(signupForm.getUsername()) == null) & (urepository.findByEmail(signupForm.getEmail()) == null)) { // Check if user exists
		    		
		    		urepository.save(newUser);
			
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "User with these Username or Email is already exists");  	
	    			return "signup";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			return "signup";
    		}
    	}
    	else {
    		return "signup";
    	}
    	return "redirect:/login";    	
    } 
    
  //forgot handler
    @RequestMapping(value = "forgotpassword")
    public String forgotPassword(Model model){
    	model.addAttribute("signupform", new SignupForm());
		return "forgotpassword";
    }	

    //send forgotten password handler
    @RequestMapping(value = "sendforgot", method = RequestMethod.POST)
    public String sendforgot(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
    	
    	User user = urepository.findByEmail(signupForm.getEmail());
    		
			if (signupForm.getEmail().matches(user.getEmail())) {
				Random random = new Random();
		    	int randomNumber = random.nextInt(20000000);
		    	String randomStr = randomNumber + "";
		    	Secret newSecret = createSecretForUser(user, randomStr);
		    	
		    	Email letter = new Email();
		    	letter.sendEmail(user.getEmail(), "Reset key", "Please copy this address and insert it to the main url of the programm : /updatepassword?secret=" + newSecret.getSecret() );
		    	
		    	return "forgotpassword";
		    	
			} else {
	    		System.out.println("user not found");
	    		return "login";
	    	} 
		
    }	
	
    //Update user's password handler
    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public String changePassword(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
    	
    	if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) {
    		String pwd = signupForm.getPassword();
			BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			String hashPwd = bc.encode(pwd);
			String email = signupForm.getEmail();
			
			User user = urepository.findByEmail(email);
			user.setPasswordHash(hashPwd);
			
			urepository.save(user);
			
			return "redirect:/login";
		} 
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match"); 
				return "updatepassword";
    		}
} 
    //Update user's password page
    @RequestMapping(value="/updatepassword")
  	public String updatepassword(@RequestParam("secret") String secret, Model model) {
      	model.addAttribute("signupform", new SignupForm());
      	String result = validateSecret(secret);
      	  if(result == null) {
      		     model.addAttribute("secret", secret);
      		     System.out.println(result);
      		  return "updatepassword";
      	    }  else {
      	    	System.out.println(result);
      	    	return "login";
      	    }
  	}
    
    //Create unique user secret code
    public Secret createSecretForUser(User user, String secret) {
	    Secret mySecret = new Secret(user, secret);
	    srepository.save(mySecret);
	    return mySecret;
	}
    
    //Secret code validation
    public String validateSecret(String secret) {
	    final Secret sendedSecret = srepository.findBySecret(secret);
	 
	    return !isSecretFound(sendedSecret) ? "invalid secret code"
	            : null;
	}
	 
	private boolean isSecretFound(Secret sendedSecret) {
	    return sendedSecret != null;
	}
}