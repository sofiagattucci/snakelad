package view.pawn;

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

    private static final PawnTypes PAWN_TYPES = new PawnTypes();
    private final Map<AvailableColor, String> pawnColor = new HashMap<>();

    private PawnTypes() {
        this.pawnColor.put(AvailableColor.RED, RED_PAWN_PATH);
        this.pawnColor.put(AvailableColor.LIGHTBLUE, LIGHTBLUE_PAWN_PATH); 
        this.pawnColor.put(AvailableColor.YELLOW, YELLOW_PAWN_PATH);
        this.pawnColor.put(AvailableColor.GREEN, GREEN_PAWN_PATH);
        this.pawnColor.put(AvailableColor.FUCHSIA, FUCHSIA_PAWN_PATH);
        this.pawnColor.put(AvailableColor.BLUE, BLUE_PAWN_PATH);
        this.pawnColor.put(AvailableColor.BROWN, BROWN_PAWN_PATH);
        this.pawnColor.put(AvailableColor.PINK, PINK_PAWN_PATH);
        this.pawnColor.put(AvailableColor.VIOLET, VIOLET_PAWN_PATH);
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
     * @param color
     *     The color of the pawn to be used
     * @return
     *     The path to the selected pawn
     */
    public String getPawn(final AvailableColor color) {
        return this.pawnColor.get(color);
    }
}
