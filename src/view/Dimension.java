package view;

import java.awt.Toolkit;

/**
 * This class contains fixed values for the dimension of elements of the GUI.
 */
public final class Dimension {

    private static final double PAWN_HEIGHT_CONST = 0.66;
    private static final double ITEM_HEIGHT_CONST = 0.55;

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

    private static double pawnHeight = BOARD_H / 8 * PAWN_HEIGHT_CONST;

    private static double coinHeight = BOARD_H / 8 * ITEM_HEIGHT_CONST;

    /**
     * Setter of the height of a pawns of the game.
     * @param n
     *     The number of boxes per side of the selected gameBoard
     */
    public static void setPawnHeight(final int n) {
        pawnHeight = BOARD_H / n * PAWN_HEIGHT_CONST;
    }

    /**
     * Getter of the height of a pawn in the game.
     * @return
     *     The height of a pawn of the game
     */
    public static double getPawnHeight() {
        return pawnHeight;
    }

    /**
     * Setter of the height of a coin of the game.
     * @param n
     *     The number of boxes per side of the selected gameBoard
     */
    public static void setItemHeight(final int n) {
        coinHeight = BOARD_H / n * ITEM_HEIGHT_CONST;
    }

    /**
     * Getter of the height of a coin in the game.
     * @return
     *     The height of a coin of the game
     */
    public static double getItemHeight() {
        return coinHeight;
    }

    private Dimension() { }

}
