package view;

import javafx.scene.control.Button;

/**
 * This class creates a default implementation for a button in the GUI.
 */
public class BasicButton extends Button {

    private static final double BUTTON_WIDTH_PERC = 0.28;
    private static final double BUTTON_HEIGHT_PERC = 0.09;

    BasicButton(final String s) {
        super(s);
        this.setDimension();
    }

    private void setDimension() {
        this.setPrefWidth(Dimension.SCREEN_W * BUTTON_WIDTH_PERC);
        this.setPrefHeight(Dimension.SCREEN_H * BUTTON_HEIGHT_PERC);
    } 

}
