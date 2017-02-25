package view.pawn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.paint.Color;

/**
 * It manages the colors of the pawns used in the game and permits to switch it.
 */
public final class PawnsColor {

    private static final PawnsColor INSTANCE = new PawnsColor();
    private final List<Color> singleColorAssigned = new ArrayList<>();
    private final List<Color> multiColorAssigned = new ArrayList<>();

    private PawnsColor() {
        this.singleColorAssigned.addAll(Arrays.asList(Color.RED, Color.LIGHTBLUE));
        this.multiColorAssigned.addAll(Arrays.asList(Color.RED, Color.LIGHTBLUE, Color.YELLOW, Color.GREEN, Color.FUCHSIA, Color.BLUE));
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
     * Getter of the color of the selected pawn (single player).
     * @param index
     *     The index of the pawn I want to know the color
     * @return
     *     The color used for the selected pawn
     */
    public Color getSingleColor(final int index) {
        return this.singleColorAssigned.get(index);
    }

    /**
     * Getter of the color of the selected pawn (player versus player).
     * @param index
     *     The index of the pawn I want to know the color
     * @return
     *     The color used for the selected pawn
     */
    public Color getMultiColor(final int index) {
        return this.multiColorAssigned.get(index);
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
