package view.pawn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.paint.Color;

/**
 * It manages the colors of the pawns used in the game and permits to switch it.
 */
public final class PawnsColor {

    //private static final int MAX_PLAYERS = 6;
    /*private static final int N0 = 0;
    private static final int N1 = 1;
    private static final int N2 = 2;
    private static final int N3 = 3;
    private static final int N4 = 4;
    private static final int N5 = 5;
    private static final int N6 = 6;
    private static final int N7 = 7;
    private static final int N8 = 8;*/

    private static final PawnsColor INSTANCE = new PawnsColor();
    private final List<Color> singleColorAssigned = new ArrayList<>();

    private PawnsColor() {
        this.singleColorAssigned.addAll(Arrays.asList(Color.RED, Color.LIGHTBLUE));
    }

    /**
     * Getter of this class unique instance.
     * @return
     *     This class unique instance
     */
    public static PawnsColor get() {
        return INSTANCE;
    }

    /**
     * Getter of the color of the selected pawn.
     * @param index
     *     The index of the pawn I want to know the color
     * @return
     *     The color used for the selected pawn
     */
    public Color getColor(final int index) {
        return this.singleColorAssigned.get(index);
    }

    /**
     * It permits to change the assigned color of a pawn with another.
     * @param index
     *     The index of the pawn I want to change the color
     * @param color
     *     The new color for the pawn
     */
    public void switchColor(final int index, final Color color) {
        this.singleColorAssigned.set(index, color);
    }
}
