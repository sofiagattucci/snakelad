package view;

import java.awt.Toolkit;

import javafx.scene.control.Button;

/**
 * This class creates a default implementation for a button in the GUI.
 */
public class BasicButton extends Button {

    private static final double BUTTON_WIDTH_PERC = 0.25;
    private static final double BUTTON_HEIGHT_PERC = 0.044;

    BasicButton(final String s) {
        super(s);
        this.setDimension();
    }

    private void setDimension() {
        this.setPrefWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * Dimension.SCREEN_W_PERC * BUTTON_WIDTH_PERC);
        this.setPrefHeight(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * Dimension.SCREEN_H_PERC * BUTTON_HEIGHT_PERC);
    } 

}
