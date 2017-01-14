package view;

import java.awt.Toolkit;

import javafx.scene.control.Button;

/**
 * This class creates a default implementation for a button in the GUI.
 */
public class BasicButton extends Button {

    private static final double BUTTON_WIDTH_PERC = 0.25;
    private static final double BUTTON_HEIGHT_PERC = 0.044;
    private static final double STAGE_DIM = 0.9;

    BasicButton(final String s) {
        super(s);
        this.setDimension();
    }

    private void setDimension() {
        this.setPrefWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * STAGE_DIM * BUTTON_WIDTH_PERC);
        this.setPrefHeight(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * STAGE_DIM * BUTTON_HEIGHT_PERC);
    } 

}
