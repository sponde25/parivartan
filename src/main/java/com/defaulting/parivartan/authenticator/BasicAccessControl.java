package com.defaulting.parivartan.authenticator;

import java.util.HashMap;

import com.defaulting.parivartan.backend.data.User;
import com.defaulting.parivartan.backend.data.UserManager;

public class BasicAccessControl implements AccessControl{
	
	@Override
	public boolean signIn(String username, String password, UserManager userManager){
		// TODO Auto-generated method stub
		HashMap<String, User> users = userManager.getUsers();
		if(users == null) return false;
		if(users.containsKey(username) && users.get(username).getPassword().equals(password)) {
			CurrentUser.set(username);
			return true;
		}
		return false;
	}

	@Override
	public boolean isUserSignedIn() {
		// TODO Auto-generated method stub
		return !CurrentUser.get().isEmpty();
	}

	@Override
	public String getPrincipalName() {
		// TODO Auto-generated method stub
		return CurrentUser.get();
	}

	@Override
	public boolean register(User new_user, UserManager userManager) {
		// TODO Auto-generated method stub
		
		HashMap<String, User> users = userManager.getUsers();
		String username = new_user.getUsername();
		if(!users.containsKey(username )) {
			users.put(username, new_user);
			userManager.setUsers(users);
			return true;
		}		
		return false;
	}

}
