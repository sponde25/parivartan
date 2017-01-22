package com.defaulting.parivartan.authenticator;

import java.io.Serializable;

import com.defaulting.parivartan.MyUI;
import com.defaulting.parivartan.backend.data.UserManager;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class LoginScreen extends CssLayout {
	private VerticalLayout box = new VerticalLayout();
//	HorizontalSplitPanel splitter = new HorizontalSplitPanel();
	private TabSheet tabs = new TabSheet();
	private Label title = new Label("Parivartan           :                A step towards saving the planet.");
//	private Button signUp = new Button("Register Me");
	private TextField userID = new TextField("User ID");
	private PasswordField pwd = new PasswordField("Password");
	private Button login = new Button("Login");
	private RegisterationForm registrationForm;
	private AccessControl accessControl;
	private LoginListener loginListener;
	private UserManager userManager;
	private MyUI myUI;
	
	
	public LoginScreen(MyUI myUI, AccessControl accesssControl, LoginListener loginListener) {
		this.myUI = myUI;
		this.accessControl = accesssControl;
		this.loginListener = loginListener;
		userManager = myUI.getUserManager();
		title.setStyleName(ValoTheme.LABEL_H1);
		box.setSizeFull();
		box.addComponent(title);
		userID.setRequired(true);
		pwd.setRequired(true);
		registrationForm = new RegisterationForm(myUI,this,accessControl,userManager);
//		splitter.setSizeFull();
//    	splitter.setSplitPosition(100);
    	FormLayout login_register = new FormLayout();
    	login_register.setMargin(true);
    	login_register.setSpacing(true);
    	login.addClickListener(evt -> {
    		try {
    			login();
    		} finally {
    			login.setEnabled(true);
    		}
    	});
    	login.setStyleName(ValoTheme.BUTTON_PRIMARY);
    	//signUp.setStyleName(ValoTheme.BUTTON_FRIENDLY);
//    	HorizontalLayout btnBar = new HorizontalLayout();
//    	btnBar.setSpacing(true);
//    	btnBar.setMargin(true);
//    	btnBar.addComponents(login,signUp);
    	login_register.addComponents(userID,pwd,login);
    	//login_register.setComponentAlignment(login_register,Alignment.MIDDLE_CENTER);
//    	signUp.addClickListener(evt -> {
//            registrationForm.setVisible(true);
//            splitter.setSplitPosition(0);
//    	});
  
        Tab left = tabs.addTab(login_register, "Login",FontAwesome.USER);
        Tab right = tabs.addTab(registrationForm, "Sign Up",FontAwesome.USER_PLUS);
        box.setComponentAlignment(title, Alignment.TOP_CENTER);
        tabs.addStyleName("centered-tabs");
        tabs.addStyleName("equal-width-tabs");
        tabs.addStyleName("framed");
        tabs.setSizeUndefined();
        tabs.setWidth(50, UNITS_PERCENTAGE);
        box.addComponent(tabs);
        box.setComponentAlignment(tabs, Alignment.MIDDLE_CENTER);
        addComponent(box);
	}
	private void login() {
		// TODO Auto-generated method stub
		 if (accessControl.signIn(userID.getValue(), pwd.getValue(), userManager)) {
	            loginListener.loginSuccessfull();
	        } else {
	           Notification.show("Login failed. Check username/password and Try Again!", Notification.TYPE_ERROR_MESSAGE);
	            userID.focus();
	        }		
	}
	
		
	
	public interface LoginListener extends Serializable{
		void loginSuccessfull();
	}
}
