package view;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

/**
 * This class manages the different types of pawn available in the game.
 */
public final class PawnTypes {

    private static final String RED_PAWN_PATH = "./res/Pawns/RedPawn.png";
    private static final String LIGHTBLUE_PAWN_PATH = "./res/Pawns/LightBluePawn.png";
    private static final String YELLOW_PAWN_PATH = "./res/Pawns/YellowPawn.png";
    private static final String GREEN_PAWN_PATH = "./res/Pawns/GreenPawn.png";
    private static final String FUCHSIA_PAWN_PATH = "./res/Pawns/FuchsiaPawn.png";

    private static final PawnTypes PAWN_TYPES = new PawnTypes();
    private final Map<Color, String> pawnColor = new HashMap<>();

    private PawnTypes() {
        this.pawnColor.put(Color.RED, RED_PAWN_PATH);
        this.pawnColor.put(Color.LIGHTBLUE, LIGHTBLUE_PAWN_PATH);
        this.pawnColor.put(Color.YELLOW, YELLOW_PAWN_PATH);
        this.pawnColor.put(Color.GREEN, GREEN_PAWN_PATH);
        this.pawnColor.put(Color.FUCHSIA, FUCHSIA_PAWN_PATH);
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
     * @param c
     *     The color of the pawn to be used
     * @return
     *     The path to the selected pawn
     */
    public String getPawn(final Color c) {
        return this.pawnColor.get(c);
    }
}