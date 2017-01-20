package com.defaulting.parivartan.authenticator;

import java.io.IOException;
import java.io.Serializable;

import com.defaulting.parivartan.backend.data.UserManager;

public interface AccessControl extends Serializable{
	public boolean signIn(String username, String password, UserManager userManager);
	
	public boolean isUserSignedIn();
	
	public String getPrincipalName();
}
