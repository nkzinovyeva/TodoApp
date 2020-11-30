package com.example.Todo.domain;

import javax.persistence.*;

/*
 * User entity for the project
 */

@Table(name = "usertable")
@Entity
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    // Username with unique constraint
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String passwordHash;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "role", nullable = false)
    private String role;
    
    //@Column(name = "familyID", nullable = false)
    //private String familyID;
    
    public User() {
    }

	public User(String username, String passwordHash, String email, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.email = email;
		this.role = role;
		//this.familyID = familyID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/*public String getFamilyID() {
		return familyID;
	}

	public void setFamilyID(String familyID) {
		this.familyID = familyID;
	}*/
}