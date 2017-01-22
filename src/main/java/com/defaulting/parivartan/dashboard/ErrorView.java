package com.defaulting.parivartan.dashboard;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * View shown when trying to navigate to a view that does not exist using
 * {@link com.vaadin.navigator.Navigator}.
 * 
 * 
 */
public class ErrorView extends VerticalLayout implements View {

    private Label explanation;

    public ErrorView() {
        setMargin(true);
        setSpacing(true);

        Label header = new Label("Welcome to Parivartan");
        header.addStyleName(Reindeer.LABEL_H1);
        addComponent(header);
        addComponent(explanation = new Label());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        explanation.setValue(String.format(
                "Click on Profile to start your Parivartan. \n"
                + "Our team is building a tool to fight the Global Warming and Greenhouse Effect by training a recommender engine to recommend tasks to the users.\n"
                + "The tasks would be calibrated to maximize the impact of each task while keeping in mind the user's preference and convenience. \n"
                + "The primary purpose of this tool will be to minimize greenhouse emission on a user-by-user level to cut down the biggest non-industrial production of global warming without the need of major expenditure and fewer lifestyle changes.\n +"
                + " The highly optimized recommender engine chooses tasks from a wide database and also keeps track of impact till date."));
    }
}
