package com.defaulting.parivartan.userprofile;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

import com.defaulting.parivartan.backend.data.User;

public class TaskManager {
	private HashMap<String, User> tasks;
	private final String PATH = System.getProperty("user.dir") + "\\files\\users.ser";
	private FileInputStream fileInputStream;
	private File tasksFile;
	
	public TaskManager() {
		
	}
}
