package com.defaulting.parivartan;

import javax.servlet.annotation.WebServlet;

import com.defaulting.parivartan.authenticator.AccessControl;
import com.defaulting.parivartan.authenticator.BasicAccessControl;
import com.defaulting.parivartan.authenticator.LoginScreen;
import com.defaulting.parivartan.authenticator.LoginScreen.LoginListener;
import com.defaulting.parivartan.backend.data.UserManager;
import com.defaulting.parivartan.dashboard.MainScreen;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;

import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	
	private AccessControl accessControl = new BasicAccessControl();
	private UserManager userManager = new UserManager();
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	Responsive.makeResponsive(this);
        setLocale(vaadinRequest.getLocale());
        getPage().setTitle("Parivartan");
        
        if(!accessControl.isUserSignedIn()) {
        	setContent(new LoginScreen(this, accessControl, new LoginListener() {
				
				@Override
				public void loginSuccessfull() {
					showMainView();
					
				}
			}));
        }
        else
        	showMainView();
    }

    private void showMainView() {
//    	Button b = new Button("Logout");
//    	b.addClickListener(e -> {
//    		VaadinSession.getCurrent().getSession().invalidate();
//    		Page.getCurrent().reload();
//    	});
//		setContent(b);
    	addStyleName(ValoTheme.UI_WITH_MENU);
        setContent(new MainScreen(MyUI.this));
        getNavigator().navigateTo(getNavigator().getState());
		
	}
    
    public UserManager getUserManager() {
    	return userManager;
    }

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
