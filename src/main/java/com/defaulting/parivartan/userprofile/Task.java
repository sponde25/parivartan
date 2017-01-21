package com.defaulting.parivartan.userprofile;

import java.io.Serializable;

import java.util.LinkedList;
import java.util.List;


public class Task implements Serializable{
	
	private int difficulty;
	private int impact;
	private int monetary_value;
	private String name;
	private String description;

	public Task(String name, String description, int difficulty, int impact, int monetary_value) {
		// TODO Auto-generated constructor stub
		this.description = description;
		this.name = name;
		this.difficulty = difficulty;
		this.impact = impact;
		this.monetary_value = monetary_value;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public int getImpact() {
		return impact;
	}

	public void setImpact(int impact) {
		this.impact = impact;
	}

	public int getMonetary_value() {
		return monetary_value;
	}

	public void setMonetary_value(int monetary_value) {
		this.monetary_value = monetary_value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
