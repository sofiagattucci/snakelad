package view;

import javafx.scene.control.Button;

/**
 * This class creates a default implementation for a button in the GUI.
 */
public class BasicButton extends Button {

    private static final double BUTTON_WIDTH = Dimension.SCREEN_W * 0.28;
    private static final double BUTTON_HEIGHT = Dimension.SCREEN_H * 0.09;

    BasicButton(final String s) {
        super(s);
        this.setDimension();
    }

    private void setDimension() {
        this.setPrefWidth(BUTTON_WIDTH);
        this.setPrefHeight(BUTTON_HEIGHT);
    }

    /**
     * Getter of the button' s default width.
     * @return
     *     The button' s width
     */
    public static double getButtonWidth() {
        return BUTTON_WIDTH;
    }

    /**
     * Getter of the button' s default height.
     * @return
     *     The button' s height
     */
    public static double getButtonHeight() {
        return BUTTON_HEIGHT;
    }

}
