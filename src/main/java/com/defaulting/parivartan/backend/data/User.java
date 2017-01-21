package com.defaulting.parivartan.backend.data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.defaulting.parivartan.userprofile.Task;
import com.defaulting.parivartan.userprofile.TaskManager;

public class User implements Serializable{
	
	
	private String username;
	private String password;
	private String dob;
	private String profression;
	private String name;
	private List<String> friendList;
	private List<Task> tasksAttempted;
	private List<Task> tasksCompleted;
	private List<Task> tasksForRecommend;
	
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		tasksAttempted = new LinkedList<>();
		tasksCompleted = new LinkedList<>();
		tasksForRecommend = new LinkedList<>();
		friendList = new LinkedList<>();
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getProfression() {
		return profression;
	}

	public void setProfression(String profression) {
		this.profression = profression;
	}


	public List<String> getFriendList() {
		return friendList;
	}


	public void setFriendList(List<String> friendList) {
		this.friendList = friendList;
	}

}
