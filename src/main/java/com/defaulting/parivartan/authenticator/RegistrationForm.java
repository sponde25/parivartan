package com.defaulting.parivartan.authenticator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import com.defaulting.parivartan.MyUI;
import com.defaulting.parivartan.authenticator.LoginScreen.LoginListener;
import com.defaulting.parivartan.backend.data.User;
import com.defaulting.parivartan.backend.data.UserManager;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

class RegisterationForm extends FormLayout{
	private TextField name = new TextField("Name");
	private TextField email = new TextField("Username");
	private NativeSelect occupation;
	private PopupDateField DOB = new PopupDateField("Date Of Birth");
	private PasswordField pwd = new PasswordField("Password");
	private PasswordField cpwd = new PasswordField("Confirm");
	private Button registerBtn = new Button("Register");
	private Button cancelBtn = new Button("Cancel");
	private RegistrationState registrationState =  null;
	private AccessControl accessControl;
	private UserManager userManager;
	private MyUI myUI;
	
	public RegisterationForm(MyUI myUI,LoginScreen frmScreen, AccessControl accessControl, UserManager userManager) {
		this.myUI = myUI;
		this.accessControl = accessControl;
		this.userManager = userManager;
		pwd.setRequired(true);
		cpwd.setRequired(true);
		setSizeUndefined();
		String professions[] = {
				"Self-Employed",
				"Engineer",
				"Doctor",
				"Manager",
				"Student"
		};
		Collection<String> jobs = Arrays.asList(professions);
		occupation = new NativeSelect("Occupation", jobs);
		registerBtn.setStyleName(ValoTheme.BUTTON_PRIMARY);
		cancelBtn.setStyleName(ValoTheme.BUTTON_DANGER);
		setSpacing(true);
		setMargin(true);
		HorizontalLayout buttonBar = new HorizontalLayout();
		buttonBar.setSpacing(true);
		cancelBtn.addClickListener(evt -> {
	        //setVisible(false);
//	        frmScreen.splitter.setSplitPosition(100);
		});
		registerBtn.addClickListener(evt -> {
	        /*setVisible(false);
//	        frmScreen.splitter.setSplitPosition(100);
    		Notification.show("Registration Not Supported Yet", Notification.Type.WARNING_MESSAGE);*/
			registrationState = attemptRegistration();
			if(registrationState == RegistrationState.SUCCESSFUL) {
				//setVisible(false);
				Notification.show("Registration Successfull", Notification.Type.HUMANIZED_MESSAGE);				
			}
			else 
				Notification.show(registrationState.toString(), Notification.Type.ERROR_MESSAGE);
			
		});
		buttonBar.addComponents(registerBtn);
		addComponents(name,email,DOB,occupation,pwd,cpwd,buttonBar);
	}
	
	public RegistrationState attemptRegistration() {
		if(pwd.getValue().equals(cpwd.getValue())) {
			if(accessControl.register(new User(email.getValue(), pwd.getValue()), userManager))
				return RegistrationState.SUCCESSFUL;
			return RegistrationState.USER_EXISTS;
		}
		return RegistrationState.PASSWORD_NOT_MATCH;		
	}
}
