package com.defaulting.parivartan.userprofile;


import java.util.LinkedList;
import java.util.List;

import com.defaulting.parivartan.backend.data.User;
import com.defaulting.parivartan.backend.data.UserManager;
import com.vaadin.data.util.BeanItemContainer;
import com.defaulting.parivartan.backend.data.UserManager;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.Slider;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component.Event;
import com.vaadin.ui.themes.ValoTheme;

public class ProfileView extends CssLayout implements View {

	public static final String VIEW_NAME = "Profile";
	
	public ProfileView(UserManager userManager) {
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
			if(addFirend(userManager, username.getValue())) {
				addFriends.setPopupVisible(false);
				Notification.show("Friend Added", Notification.Type.HUMANIZED_MESSAGE);
			}
			else 
				Notification.show("Failed Attempt", Notification.Type.WARNING_MESSAGE);
		});
		
		Button addFriend = new Button("right: 10px;top: 10px;");
		addFriend.setCaption("Add Friend");
		addFriend.setIcon(FontAwesome.PLUS_CIRCLE);
		addFriend.addStyleName(ValoTheme.BUTTON_PRIMARY);
		addFriend.addClickListener(evt -> {
			//Notification.show("Ok So you want friends , Go watch F.R.I.E.N.D.S");
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
		
		User current = userManager.getCurrentUser();
		HorizontalLayout availableAndRating = getAvailableGrid(current);
		HorizontalLayout recommendedGrid = getRecommendedGrid(current);
		VerticalLayout grids = new VerticalLayout();
		grids.setSpacing(true);
		grids.setMargin(true);
		grids.setSizeFull();
		grids.addComponents(availableAndRating,recommendedGrid);
		VerticalLayout barAndGridLayout = new VerticalLayout();
        barAndGridLayout.addComponent(topBar);
        barAndGridLayout.addComponent(grids);
        barAndGridLayout.setMargin(true);
        barAndGridLayout.setSpacing(true);
        barAndGridLayout.setSizeFull();
        barAndGridLayout.setExpandRatio(grids, 1);
        barAndGridLayout.setStyleName("crud-main-layout");
		
		addComponent(barAndGridLayout);
		//addComponent(form);
		addComponent(addFriends);
//		form.removeStyleName("visible");
//        form.setEnabled(false);
	}
	private HorizontalLayout getAvailableGrid(User current){
		Grid grid = new Grid("Available Tasks");
		grid.setSelectionMode(SelectionMode.SINGLE);
		//dummy data
		List<String> dummy = new LinkedList<String>();
		for(int i=0;i<10;i++){
			dummy.add("Lorem Ipsum Dolores");
		}
		grid.setContainerDataSource( new BeanItemContainer<>(String.class,dummy));
		//grid.setColumns("name","description");
		FormLayout ratingForm = new FormLayout();
		Slider impactBar = new Slider("Impact", 1, 5);
		impactBar.setValue(3.0);
		Slider difficultyBar = new Slider("Difficulty", 1, 5);
		difficultyBar.setValue(3.0);
		Slider monetaryBar = new Slider("Monetary", 1, 5);
		monetaryBar.setValue(3.0);
		
		ratingForm.addComponents(impactBar,difficultyBar,monetaryBar);
		ratingForm.setVisible(false);
		impactBar.addListener(new Listener() {
			
			@Override
			public void componentEvent(Event event) {
				if(difficultyBar.getValue()!=0.0 && monetaryBar.getValue()!=0.0){
					//TODO : Anirudh we got the rating do the deletion after this comment but we have to save the values somewhere so call setters
					 
					ratingForm.setVisible(false);
					grid.setContainerDataSource( new BeanItemContainer<>(String.class,dummy));
					
				}
			}
		});
		difficultyBar.addListener(new Listener() {
			
			@Override
			public void componentEvent(Event event) {
				if(impactBar.getValue()!=0.0 && monetaryBar.getValue()!=0.0){
					//TODO : Anirudh we got the rating do the deletion after this comment we have to save the values somewhere so call setters
					
					ratingForm.setVisible(false);
					grid.setContainerDataSource( new BeanItemContainer<>(String.class,dummy));
				}
			}
		});
		monetaryBar.addListener(new Listener() {
			
			@Override
			public void componentEvent(Event event) {
				if(impactBar.getValue()!=0.0 && difficultyBar.getValue()!=0.0){
					//TODO : Anirudh we got the rating do the deletion after this comment we have to save the values somewhere so call setters
					
					ratingForm.setVisible(false);
					grid.setContainerDataSource( new BeanItemContainer<>(String.class,dummy));
				}
			}
		});
		grid.addSelectionListener(evt -> {
			if(evt.getSelected().isEmpty()){
				ratingForm.setVisible(false);
			}else{
				ratingForm.setVisible(true);
			}
		});
		
		HorizontalLayout availableRatingPanel = new HorizontalLayout();
		availableRatingPanel.setSpacing(true);
		availableRatingPanel.setMargin(true);
		availableRatingPanel.setSizeFull();
		availableRatingPanel.addComponents(grid,ratingForm);
		return availableRatingPanel;
	}
	private HorizontalLayout getRecommendedGrid(User current){
		Grid grid = new Grid("Recommended Tasks");
		grid.setSelectionMode(SelectionMode.SINGLE);
		//dummy data
		List<String> dummy = new LinkedList<String>();
		for(int i=0;i<10;i++){
			dummy.add("Lorem Ipsum Dolores");
		}
		grid.setContainerDataSource( new BeanItemContainer<>(String.class,dummy));
		//grid.setColumns("name","description");
		FormLayout ratingForm = new FormLayout();
		Slider impactBar = new Slider("Impact", 1, 5);
		impactBar.setEnabled(false);
		Slider difficultyBar = new Slider("Difficulty", 1, 5);
		difficultyBar.setEnabled(false);
		Slider monetaryBar = new Slider("Monetary", 1, 5);
		monetaryBar.setEnabled(false);
		ratingForm.addComponents(impactBar,difficultyBar,monetaryBar);
		ratingForm.setVisible(false);
		impactBar.addListener(new Listener() {
			
			@Override
			public void componentEvent(Event event) {
				if(difficultyBar.getValue()!=0.0 && monetaryBar.getValue()!=0.0){
					//TODO : Anirudh we got the rating do the deletion after this comment but we have to save the values somewhere so call setters
					 
					ratingForm.setVisible(false);
					grid.setContainerDataSource( new BeanItemContainer<>(String.class,dummy));
					
				}
			}
		});
		difficultyBar.addListener(new Listener() {
			
			@Override
			public void componentEvent(Event event) {
				if(impactBar.getValue()!=0.0 && monetaryBar.getValue()!=0.0){
					//TODO : Anirudh we got the rating do the deletion after this comment we have to save the values somewhere so call setters
					
					ratingForm.setVisible(false);
					grid.setContainerDataSource( new BeanItemContainer<>(String.class,dummy));
				}
			}
		});
		monetaryBar.addListener(new Listener() {
			
			@Override
			public void componentEvent(Event event) {
				if(impactBar.getValue()!=0.0 && difficultyBar.getValue()!=0.0){
					//TODO : Anirudh we got the rating do the deletion after this comment we have to save the values somewhere so call setters
					
					ratingForm.setVisible(false);
					grid.setContainerDataSource( new BeanItemContainer<>(String.class,dummy));
				}
			}
		});
		grid.addSelectionListener(evt -> {
			if(evt.getSelected().isEmpty()){
				ratingForm.setVisible(false);
			}else{
				ratingForm.setVisible(true);
			}
		});
		
		HorizontalLayout availableRatingPanel = new HorizontalLayout();
		availableRatingPanel.setSpacing(true);
		availableRatingPanel.setMargin(true);
		availableRatingPanel.setSizeFull();
		availableRatingPanel.addComponents(grid,ratingForm);
		return availableRatingPanel;
	}
	
	private boolean addFirend(UserManager userManager, String username) {
		// TODO Auto-generated method stub
		
		return userManager.addFriend(username);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Entered profile View");
		
	}

}