package com.defaulting.parivartan.backend.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import com.defaulting.parivartan.authenticator.CurrentUser;
import com.vaadin.ui.Notification;

public class UserManager {
	
	private HashMap<String, User> users;
	private final String PATH = System.getProperty("user.dir") + "\\files\\users.ser";
	FileInputStream usersFileInputStream;
	ObjectInputStream usersObjectInputStream;
	FileOutputStream usersFileOutputStream;
	ObjectOutputStream usersObjectOutputStream;
	File usersFile;
	
	public UserManager() {
		users = new HashMap<>();
		try {
			usersFile = new File(PATH);
			if(!usersFile.exists()) {
				usersFile.createNewFile();
				Notification.show("File Created");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public HashMap<String, User> getUsers() {
		try {
			usersFileInputStream = new FileInputStream(usersFile);
			if(usersFile.length() > 0) {
				usersObjectInputStream = new ObjectInputStream(usersFileInputStream);
				users = (HashMap<String, User>) usersObjectInputStream.readObject();
				usersObjectInputStream.close();
			}
			else 
				users = new HashMap<String, User>();
			usersFileInputStream.close();			
			return users;
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println(usersFile.length());
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void setUsers(HashMap<String, User> users) {
		this.users = users;
		try {
			usersFileOutputStream = new FileOutputStream(usersFile);
			usersObjectOutputStream = new ObjectOutputStream(usersFileOutputStream);
			usersObjectOutputStream.writeObject(users);
			usersObjectOutputStream.close();
			usersFileOutputStream.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean addFriend(String username) {
		if(users.containsKey(username) && getCurrentUser().getFriendList().contains(username)) {
			getCurrentUser().getFriendList().add(username);
			users.get(username).getFriendList().add(CurrentUser.get());
		}
		return false;
	}
	
	public User getCurrentUser() {
		return users.get(CurrentUser.get());
	}
}
