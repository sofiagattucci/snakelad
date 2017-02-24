package view.pawn;

import java.util.ArrayList;
import java.util.List;

/**
 * It manages the colors of the pawns used in the game and permits to switch it.
 */
public final class PawnsColor {

    private static final int MAX_PLAYERS = 6;

    private static final PawnsColor INSTANCE = new PawnsColor();
    private final List<Integer> colorAssigned = new ArrayList<>();

    private PawnsColor() {
        for (int i = 0; i < MAX_PLAYERS; i++) {
            this.colorAssigned.add(i);
        }
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
    public int getColor(final int index) {
        return this.colorAssigned.get(index);
    }

    /**
     * It permits to change the assigned color of a pawn with another.
     * @param index
     *     The index of the pawn I want to change the color
     * @param color
     *     The new color for the pawn
     */
    public void switchColor(final int index, final int color) {
        this.colorAssigned.set(index, color);
    }
}
