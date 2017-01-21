package com.defaulting.parivartan.backend.data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.defaulting.parivartan.dataService.DataCreation;
import com.defaulting.parivartan.userprofile.Task;

public class User implements Serializable{
	
	
	private String username;
	private String password;
	private String dob;
	private String profression;
	private String name;
	 List<String> friendList;
	private List<Task> tasksAttempted;
	private List<Task> tasksCompleted;
	private List<Task> tasksRecommended;
	private List<Task> tasksForRecommend;
	
	
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		//tasks = new File(PATH);
		//populate(tasks);
		tasksAttempted = new LinkedList<>();
		tasksCompleted = new LinkedList<>();
		tasksRecommended = new LinkedList<>();
		tasksForRecommend = DataCreation.readRecommedations();
		//init_testing();
		
		friendList = new LinkedList<String>();
		
	}
	
	private void init_testing() {
		int a, r;
		for(int i=0;i<3;i++) {
			a = (int) (Math.random() * (tasksForRecommend.size()));
			r = (int) (Math.random() * (tasksForRecommend.size()));
			tasksAttempted.add(tasksForRecommend.get(a));
			tasksRecommended.add(tasksForRecommend.get(r));
			
		}
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
		System.out.println("  $$  "+ friendList.size());
		return friendList;
	}


	public void setFriendList(List<String> friendList) {
		this.friendList = friendList;
	}
	
	public List<Task> getRecommenations(){
		//TODO Add Recommendation
		tasksRecommended = DataCreation.readRecommedations();
		return tasksRecommended;
	}
	
	public void setAttempted(Task task) {
		tasksAttempted.add(task);
		for(Task toTask : tasksRecommended) if(task.getName().equals(toTask.getName())) {
			tasksRecommended.remove(toTask); break;
		}
	}
	
	public List<Task> getAttemptedTasks(){
		return tasksAttempted;
	}
	
	public void setCompleted(Task task) {
		tasksCompleted.add(task);
		for(Task toTask :tasksAttempted) if(task.getName().equals(toTask.getName())) {
			tasksAttempted.remove(toTask); break;
		}
	}
	
	public float getScore() {
		float score = 0;
		for(Task task: tasksCompleted)
			score += (task.getDifficulty() + task.getImpact() + task.getMonetary_value())/3;
		return score;
	}
	
	
	

}
