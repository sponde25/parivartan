package com.defaulting.parivartan.userprofile;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

public class ProfileView extends CssLayout implements View {

	public static final String VIEW_NAME = "Profile";
	
	public ProfileView() {
		setSizeFull();
		setStyleName("crud-view");
		
		HorizontalLayout topBar = new HorizontalLayout();
		
		Button addFriend = new Button("right: 10px;top: 10px;");
		addFriend.setCaption("Add Friend");
		addFriend.setIcon(FontAwesome.PLUS_CIRCLE);
		addFriend.addStyleName(ValoTheme.BUTTON_PRIMARY);
		addFriend.addClickListener(evt -> {
			//Side bar for friends
		});
		
		topBar.setSpacing(true);
		topBar.setWidth("100%");
		topBar.addComponent(addFriend);
		topBar.setComponentAlignment(addFriend, Alignment.TOP_RIGHT);
		//topBar.setStyleName("top-bar");
		
		addComponent(topBar);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Entered profile View");
		
	}

}