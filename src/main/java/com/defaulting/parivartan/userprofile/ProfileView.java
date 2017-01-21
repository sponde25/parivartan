package com.defaulting.parivartan.userprofile;


import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.defaulting.parivartan.backend.data.User;
import com.defaulting.parivartan.backend.data.UserManager;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.SelectionEvent;
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
		//HorizontalLayout availableAndRating = getAvailableGrid(current);
		
		
		
		
		Grid grid = new Grid("Available Tasks");
		grid.setSelectionMode(SelectionMode.SINGLE);
		//dummy data
		List<Task> availableTasks = current.getAttemptedTasks();
		grid.setContainerDataSource( new BeanItemContainer<>(Task.class,availableTasks));
		grid.setColumns("name","description");
		FormLayout ratingForm = new FormLayout();
		Slider impactBar = new Slider("Impact", 0, 5);
		impactBar.setValue(0.0);
		Slider difficultyBar = new Slider("Difficulty", 0, 5);
		difficultyBar.setValue(0.0);
		Slider monetaryBar = new Slider("Monetary", 0, 5);
		monetaryBar.setValue(0.0);
		
		ratingForm.addComponents(impactBar,difficultyBar,monetaryBar);
		ratingForm.setVisible(false);
		impactBar.addListener(new Listener() {			
			@Override
			public void componentEvent(Event event) {
				if(difficultyBar.getValue()!=0.0 && monetaryBar.getValue()!=0.0){
					//TODO : Anirudh we got the rating do the deletion after this comment but we have to save the values somewhere so call setters
					current.setCompleted((Task) (selected.iterator().next()));
					ratingForm.setVisible(false);
					grid.setContainerDataSource( new BeanItemContainer<>(Task.class,availableTasks));
					
				}
			}
		});
		difficultyBar.addListener(new Listener() {
			
			@Override
			public void componentEvent(Event event) {
				if(impactBar.getValue()!=0.0 && monetaryBar.getValue()!=0.0){
					//TODO : Anirudh we got the rating do the deletion after this comment we have to save the values somewhere so call setters
					current.setCompleted((Task) (selected.iterator().next()));
					ratingForm.setVisible(false);
					grid.setContainerDataSource( new BeanItemContainer<>(Task.class,availableTasks));
				}
			}
		});
		monetaryBar.addListener(new Listener() {
			
			@Override
			public void componentEvent(Event event) {
				if(impactBar.getValue()!=0.0 && difficultyBar.getValue()!=0.0){
					//TODO : Anirudh we got the rating do the deletion after this comment we have to save the values somewhere so call setters
					current.setCompleted((Task) (selected.iterator().next()));
					ratingForm.setVisible(false);
					grid.setContainerDataSource( new BeanItemContainer<>(Task.class,availableTasks));
				}
			}
		});
		grid.addSelectionListener(evt -> {
			if(evt.getSelected().isEmpty()){
				ratingForm.setVisible(false);
			}else{
				setSelected(evt.getSelected());
				ratingForm.setVisible(true);
				
			}
		});
		
		HorizontalLayout availableRatingPanel = new HorizontalLayout();
		availableRatingPanel.setSpacing(true);
		availableRatingPanel.setMargin(true);
		availableRatingPanel.setSizeFull();
		availableRatingPanel.addComponents(grid,ratingForm);
		
		
		
		
		
		
		HorizontalLayout recommendedGrid = getRecommendedGrid(current, grid);
		VerticalLayout grids = new VerticalLayout();
		grids.setSpacing(true);
		grids.setMargin(true);
		grids.setSizeFull();
		grids.addComponents(availableRatingPanel,recommendedGrid);
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
	
	Collection<Object> selected;
	private void setSelected(Set<Object> selected) {
		// TODO Auto-generated method stub
		this.selected = selected;
		
	}
	private HorizontalLayout getRecommendedGrid(User current, Grid grid2){
		Grid grid = new Grid("Recommended Tasks");
		grid.setSelectionMode(SelectionMode.SINGLE);
		//dummy data
		List<Task> taskRecommended = current.getRecommenations();
		Notification.show(""+taskRecommended.size());
		grid.setContainerDataSource( new BeanItemContainer<>(Task.class,taskRecommended));
		grid.setColumns("name","description");
		FormLayout ratingForm = new FormLayout();
		/*Slider impactBar = new Slider("Impact", 1, 5);
		impactBar.setEnabled(false);
		Slider difficultyBar = new Slider("Difficulty", 1, 5);
		difficultyBar.setEnabled(false);
		Slider monetaryBar = new Slider("Monetary", 1, 5);
		monetaryBar.setEnabled(false);*/
		Button tryTask = new Button("TRY");
		tryTask.setEnabled(false);
		ratingForm.addComponents(tryTask);
		ratingForm.setVisible(false);
		/*impactBar.addListener(new Listener() {
			
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
		});*/
		//SelectionEvent eventGrid = null;
		tryTask.addClickListener(e -> {
			current.setAttempted((Task) (selected.iterator().next()));
			Notification.show("Signed up for this task");
			ratingForm.setVisible(true);
			grid.setContainerDataSource(new BeanItemContainer<>(Task.class, taskRecommended));
			grid2.setContainerDataSource(new BeanItemContainer<>(Task.class, current.getAttemptedTasks()));
		});
		grid.addSelectionListener(evt -> {
			if(evt.getSelected().isEmpty()){
				ratingForm.setVisible(false);
				tryTask.setEnabled(false);
			}else{
				setSelected(evt.getSelected());
				ratingForm.setVisible(true);
				tryTask.setEnabled(true);
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