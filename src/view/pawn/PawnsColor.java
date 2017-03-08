package view.pawn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * It manages the colors of the pawns used in the game and permits to switch it.
 */
public final class PawnsColor {

    private static final PawnsColor INSTANCE = new PawnsColor();
    private final List<AvailableColor> singleColorAssigned = new ArrayList<>();
    private final List<AvailableColor> multiColorAssigned = new ArrayList<>();

    private PawnsColor() {
        this.singleColorAssigned.addAll(Arrays.asList(AvailableColor.RED, AvailableColor.LIGHTBLUE));
        this.multiColorAssigned.addAll(Arrays.asList(AvailableColor.RED, AvailableColor.LIGHTBLUE,
                AvailableColor.YELLOW, AvailableColor.GREEN, AvailableColor.FUCHSIA, AvailableColor.BLUE));
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
    public AvailableColor getSingleColor(final int index) {
        return this.singleColorAssigned.get(index);
    }

    /**
     * Getter of the color of the selected pawn (player versus player).
     * @param index
     *     The index of the pawn I want to know the color
     * @return
     *     The color used for the selected pawn
     */
    public AvailableColor getMultiColor(final int index) {
        return this.multiColorAssigned.get(index);
    }

    /**
     * It permits to change the assigned color of a pawn with another (Single game).
     * @param index
     *     The index of the pawn I want to change the color
     * @param color
     *     The new color for the pawn
     */
    public void switchColorSingle(final int index, final AvailableColor color) {
        this.singleColorAssigned.set(index, color);
    }

    /**
     * It permits to change the assigned color of a pawn with another (Player versus player).
     * @param index
     *     The index of the pawn I want to change the color
     * @param color
     *     The new color for the pawn
     */
    public void switchColorMulti(final int index, final AvailableColor color) {
        this.multiColorAssigned.set(index, color);
    }
}
