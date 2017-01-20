package com.defaulting.parivartan.authenticator;

public enum RegistrationState {
	SUCCESSFUL("User Registration Successful"), PASSWORD_NOT_MATCH("The Passwords don't match."), USER_EXISTS("User already exists!");
	
	private String value;
	
	private RegistrationState(String value) {
		this.value = value;
	}
	
	public String toString() {
		return value;
	}
}
