package com.defaulting.parivartan.userprofile;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

public class ProfileView extends CssLayout implements View {

	public static final String VIEW_NAME = "Profile";
	
	public ProfileView() {
		setSizeFull();
		setStyleName("crud-view");
		
		FormLayout form = new FormLayout();
		
		TextField username = new TextField("Username");
		Button addFriendPop = new Button("Add Friend", FontAwesome.USER_PLUS);
		Button cancel = new Button("Cancel",FontAwesome.CLOSE);
		addFriendPop.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		cancel.setStyleName(ValoTheme.BUTTON_DANGER);

		HorizontalLayout buttonBar = new HorizontalLayout();
		buttonBar.setSpacing(true);
		buttonBar.setMargin(true);
		buttonBar.addComponents(addFriendPop,cancel);
		
		username.setInputPrompt("Enter Friend's Username");
		username.setWidth("6cm");
		
		form.addComponents(username,buttonBar);
		
		HorizontalLayout topBar = new HorizontalLayout();
		
		PopupView addFriends = new PopupView("Add friend", form);
		
		cancel.addClickListener(e -> {
			addFriends.setPopupVisible(false);
		});
		addFriendPop.addClickListener(evt -> {
			
		});
		
		Button addFriend = new Button("right: 10px;top: 10px;");
		addFriend.setCaption("Add Friend");
		addFriend.setIcon(FontAwesome.PLUS_CIRCLE);
		addFriend.addStyleName(ValoTheme.BUTTON_PRIMARY);
		addFriend.addClickListener(evt -> {
			Notification.show("Ok So you want friends , Go watch F.R.I.E.N.D.S");
//			if(form.isEnabled()){
//				form.removeStyleName("visible");
//	            form.setEnabled(false);
//			}else{
//				form.addStyleName("visible");
//	            form.setEnabled(true);
//			} DONT TOUCH THIS
			addFriends.setPopupVisible(true);
		});
		
		Button compareFriends = new Button();
		compareFriends.setCaption("Compare Friends");
		compareFriends.setIcon(FontAwesome.USER_TIMES);
		compareFriends.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		compareFriends.addClickListener(e -> {
			
		});
		
		topBar.setSpacing(true);
		topBar.setWidth("100%");
		topBar.addComponent(addFriend);
		topBar.setComponentAlignment(addFriend, Alignment.TOP_RIGHT);
		topBar.addComponent(compareFriends);
		topBar.setComponentAlignment(compareFriends, Alignment.TOP_LEFT);
		//topBar.setStyleName("top-bar");
		
		VerticalLayout barAndGridLayout = new VerticalLayout();
        barAndGridLayout.addComponent(topBar);
        //barAndGridLayout.addComponent(grid);
        barAndGridLayout.setMargin(true);
        barAndGridLayout.setSpacing(true);
        barAndGridLayout.setSizeFull();
        //barAndGridLayout.setExpandRatio(grid, 1);
        barAndGridLayout.setStyleName("crud-main-layout");
		
		addComponent(barAndGridLayout);
		//addComponent(form);
		addComponent(addFriends);
//		form.removeStyleName("visible");
//        form.setEnabled(false);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Entered profile View");
		
	}

}