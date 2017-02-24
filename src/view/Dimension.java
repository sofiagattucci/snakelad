package view;

import java.awt.Toolkit;

import view.gameBoard.GameBoardImpl;

/**
 * This class contains fixed values for the dimension of elements of the GUI.
 */
public final class Dimension {

    /**
     * Width of the window in proportion to the screen. 
     */
    public static final double SCREEN_W_PERC = 0.9;
    /**
     * Height of the window in proportion to the screen. 
     */
    public static final double SCREEN_H_PERC = 0.9;

    /**
     * The width of the screen.
     */
    public static final double SCREEN_W = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

    /**
     * The height of the screen.
     */
    public static final double SCREEN_H = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    /**
     * The height of the game board in the game screen.
     */
    public static final double BOARD_H = SCREEN_H * 0.9;

    /**
     * The height of the pawn in the game.
     */
    public static final double PAWN_HEIGHT = BOARD_H / GameBoardImpl.getBoxesPerRaw() * 0.66;

    private Dimension() { }

}
