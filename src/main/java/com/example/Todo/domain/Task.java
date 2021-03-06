package com.example.Todo.domain;

import java.util.Date;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/*
 * Task entity for the project
 */

@Entity
//@Table(name="task")
public class Task {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title; 
	private Date   targetDate; 
	
	@ManyToOne
	@JoinColumn(name="priorityId")
	@JsonManagedReference
	private Priority priority;
	
	private String creator;
	
	//@ManyToOne
	//@JoinColumn(name="UserFamilyID")
	//@JsonManagedReference
	//private User familyId;
	
	public Task() {
		super();
	}
	
	public Task(String title, Date targetDate, Priority priority, String creator) {
		super();
		this.title = title;
		this.targetDate = targetDate;
		this.priority = priority;
		this.creator = creator;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
}