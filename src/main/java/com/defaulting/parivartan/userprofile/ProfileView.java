package com.defaulting.parivartan.userprofile;


import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.defaulting.parivartan.backend.data.User;
import com.defaulting.parivartan.backend.data.UserManager;
import com.vaadin.data.Container;
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
import com.vaadin.ui.Table;
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
		
		Table sample = new Table("");
        //sample.setSizeFull();
        //sample.setSelectable(true);
        //sample.setMultiSelect(true);
        //sample.setImmediate(true);
        sample.addContainerProperty("Name", String.class, null);
        sample.addContainerProperty("Score", String.class, null);
        sample.setColumnWidth("Name", 336);
        sample.setColumnWidth("Score",336);
        sample.addItem(new Object[]{"Ajeet", "10.14 miT"}, 1);
        sample.addItem(new Object[]{"Nikky", "7.54 miT"}, 2);
        sample.addItem(new Object[]{"Anirudh", "17.70miT"}, 3);
        sample.addItem(new Object[]{"Thuku", "9.28miT"}, 4);
        sample.addItem(new Object[]{"Shrill", "10.37miT"}, 5); 
        
        
		User current = userManager.getCurrentUser();
		
		List<String> friendNames = current.getFriendList();
		System.out.println(" QQ " + friendNames.size());
		int itr = 1;
        for(String name:friendNames){
        	Object[] item = new Object[2];
        	System.out.println(name);
        	item[0] = name;
        	item[1] = "" + userManager.getUsers().get(name).getScore();
        	sample.addItem(item,itr);
        	itr++;
        }
		sample.setPageLength(sample.size());
        PopupView compFriends = new PopupView("Compare Friends",sample);
		
		Button compareFriends = new Button();
		compareFriends.setCaption("Compare Friends");
		compareFriends.setIcon(FontAwesome.USERS);
		compareFriends.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		compareFriends.addClickListener(e -> {
			compFriends.setPopupVisible(true); 
//			if(compFriends.isPopupVisible()){
//				compFriends.setPopupVisible(false);
//			}else{
//				compFriends.setPopupVisible(true);
//			}
		});
		
		topBar.setSpacing(true);
		topBar.setWidth("100%");
		topBar.addComponent(addFriend);
		topBar.setComponentAlignment(addFriend, Alignment.TOP_RIGHT);
		topBar.addComponent(compareFriends);
		topBar.setComponentAlignment(compareFriends, Alignment.TOP_LEFT);
		//topBar.setStyleName("top-bar");
		
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
		addComponent(compFriends);
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
