package com.example.Todo.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.Todo.domain.PriorityRepository;
import com.example.Todo.domain.Task;
import com.example.Todo.domain.TaskRepository;


@Controller
public class TaskController {
	
	@Autowired
	private TaskRepository trepository;
	
	@Autowired
	private PriorityRepository prepository;
	
	@RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
	
	//Date handler
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
	//Logged user handler
	private String getLoggedInUserName(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }
	
	
	// RESTful service to get task by id (for Rest API)
    @RequestMapping(value="/task/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Task> findTaskRest(@PathVariable("id") Long taskId) {	
    	return trepository.findById(taskId);
    }  
    
    // RESTful service to get all tasks (for Rest API)
    @RequestMapping(value="/tasks")
    public @ResponseBody List<Task> taskListRest() {	
    	return (List<Task>) trepository.findAll();
    }
    
	//List todos
	@RequestMapping(value={"/","/tasklist"})
	public String tasklist (Model model) {
		String name = getLoggedInUserName(model);
		model.addAttribute("tasks", trepository.findByCreatorOrderByTargetDateAsc(name));
		return "tasklist";
	}
		   
	//Add todo
	@RequestMapping(value = "/add")
    public String addTask(Model model){
    	model.addAttribute("task", new Task());
    	model.addAttribute("priorities", prepository.findAll());
        return "addtask";
    }
	
	//Save todo
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Task task, Model model) {
		task.setCreator(getLoggedInUserName(model));
		trepository.save(task);
        return "redirect:tasklist";
    } 
  
	//Delete todo
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(value = "/delete/{id}")
	 public String deleteTask(@PathVariable("id") Long taskId, Model model) {
	    	trepository.deleteById(taskId);
	        return "redirect:../tasklist";
	} 
    
	//Edit todo
    @RequestMapping(value = "/edit/{id}")
    public String editTask(@PathVariable("id") Long taskId, Model model) {
    	model.addAttribute("task", trepository.findById(taskId));
    	model.addAttribute("priorities", prepository.findAll());
    	return "edittask";
    } 
    
    
    //Errors todo
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class BadDateFormatException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BadDateFormatException(String dateString) {
        super(dateString);
    }
	
    }}

/*@RequestMapping(value={"/tasklist/{Priority}"})
	public String tasklistLow (@PathVariable("Priority") Long priorityId, Model model) {
		String name = getLoggedInUserName(model);
		model.addAttribute("task", trepository.findByCreatorOrderByTargetDateAsc(name));
		model.addAttribute("task", trepository.findByPriorityOrderByTargetDateAsc(priorityId));
		return "redirect:../tasklist";
	}*/
