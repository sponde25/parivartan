package com.defaulting.parivartan.backend.data;

import java.io.Serializable;

public class User implements Serializable{
	
	
	private String username;
	private String password;
	private int id = -1;
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.id = -1;
	}
	
	public int getId() {
		return id;
	}
	
	public void setInt(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;		
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

}
