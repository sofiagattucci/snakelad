package view;

import javafx.application.Application;

/**
 * This is the main class of the view and implements the View Interface.
 */
public class ViewImpl implements View {

    @Override
    public void setDiceValue(final int value) {

    }

    @Override
    public void start() {
        Application.launch(MainFrame.class);
    }

}

