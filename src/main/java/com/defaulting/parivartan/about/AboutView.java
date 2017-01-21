package com.defaulting.parivartan.about;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class AboutView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "About";

    public AboutView() {
        VerticalLayout aboutContent = new VerticalLayout();
        aboutContent.setStyleName("about-content");
        Label label = new Label("Team:");
        label.setStyleName(ValoTheme.LABEL_H3);
        aboutContent.addComponent(label);
        // you can add Vaadin components in predefined slots in the custom
        // layout
        Label ajeet = new Label("Ajeet Singh @luciferajeet");
        Label ani = new Label("Anirudh Jain @sponde25");
        Label nikky = new Label("Nikita Kapoor @nikita204");
        Label thuku = new Label("Rishabh Thukral @supercool276");
        Label shrill = new Label("Shril Kumar @shril");
        aboutContent.addComponent(ajeet);
        aboutContent.addComponent(ani);
        aboutContent.addComponent(thuku);
        aboutContent.addComponent(nikky);
        aboutContent.addComponent(shrill);
        aboutContent.addComponent(new Label("Vist here for more: https://github.com/sponde25/parivartan/blob/master/README.md"));
        //aboutContent.addComponent(
        //        new Label(
        //        		"Ajeet Singh @luciferajeet\r\n" + 
        //        		"Anirudh Jain @sponde25\r\n" + 
        //        		"Nikita Kapoor @nikita204\r\n" + 
        //        		"Rishabh Thukral @supercool276\r\n" + 
        //        		"Shril Kumar @shril"
        //        		));

        setSizeFull();
        setStyleName("about-view");
        addComponent(aboutContent);
        setComponentAlignment(aboutContent, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeEvent event) {
		Notification.show("Entered About View");
    }

}
