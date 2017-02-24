package view.pawn;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This class manages the different types of pawns available in the game.
 */
public final class PawnTypes {

    private static final String RED_PAWN_PATH = "./res/Pawns/RedPawn.png";
    private static final String LIGHTBLUE_PAWN_PATH = "./res/Pawns/LightBluePawn.png";
    private static final String YELLOW_PAWN_PATH = "./res/Pawns/YellowPawn.png";
    private static final String GREEN_PAWN_PATH = "./res/Pawns/GreenPawn.png";
    private static final String FUCHSIA_PAWN_PATH = "./res/Pawns/FuchsiaPawn.png";
    private static final String BLUE_PAWN_PATH = "./res/Pawns/BluePawn.png";
    private static final String BROWN_PAWN_PATH = "./res/Pawns/BrownPawn.png";
    private static final String PINK_PAWN_PATH = "./res/Pawns/PinkPawn.png";
    private static final String VIOLET_PAWN_PATH = "./res/Pawns/VioletPawn.png";
    private static final int N0 = 0;
    private static final int N1 = 1;
    private static final int N2 = 2;
    private static final int N3 = 3;
    private static final int N4 = 4;
    private static final int N5 = 5;
    private static final int N6 = 6;
    private static final int N7 = 7;
    private static final int N8 = 8;

    private static final PawnTypes PAWN_TYPES = new PawnTypes();
    private final Map<Integer, String> pawnColor = new HashMap<>();

    private PawnTypes() {
        this.pawnColor.put(N0, RED_PAWN_PATH);
        this.pawnColor.put(N1, LIGHTBLUE_PAWN_PATH);
        this.pawnColor.put(N2, YELLOW_PAWN_PATH);
        this.pawnColor.put(N3, GREEN_PAWN_PATH);
        this.pawnColor.put(N4, FUCHSIA_PAWN_PATH);
        this.pawnColor.put(N5, BLUE_PAWN_PATH);
        this.pawnColor.put(N6, BROWN_PAWN_PATH);
        this.pawnColor.put(N7, PINK_PAWN_PATH);
        this.pawnColor.put(N8, VIOLET_PAWN_PATH);
    }

    /**
     * Static method which returns a PawnTypes unique instance.
     * @return
     *     The PawnTypes unique instance
     */
    public static PawnTypes get() {
        return PAWN_TYPES;
    }

    /**
     * It select the right pawn image to use.
     * @param index
     *     The index of the pawn to be used
     * @return
     *     The path to the selected pawn
     */
    public String getPawn(final int index) {
        return this.pawnColor.get(index);
    }

    /**
     * Getter of the map that holds the different types of colors possible for the pawns.
     * @return
     *     The (unmodifiable) map that holds the different types of colors possible for the pawns
     */
    public Map<Integer, String> getMap() {
        return Collections.unmodifiableMap(this.pawnColor);
    }
}
