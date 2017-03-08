package view.pawn;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class manages the different types of pawns available in the game.
 */
public final class PawnTypes {

    private static final String BASIC_PATH = "./res/Pawns/";
    private static final String RED_PAWN_PATH = BASIC_PATH + "RedPawn.png";
    private static final String LIGHTBLUE_PAWN_PATH = BASIC_PATH + "LightBluePawn.png";
    private static final String YELLOW_PAWN_PATH = BASIC_PATH + "YellowPawn.png";
    private static final String GREEN_PAWN_PATH = BASIC_PATH + "GreenPawn.png";
    private static final String FUCHSIA_PAWN_PATH = BASIC_PATH + "FuchsiaPawn.png";
    private static final String BLUE_PAWN_PATH = BASIC_PATH + "BluePawn.png";
    private static final String BROWN_PAWN_PATH = BASIC_PATH + "BrownPawn.png";
    private static final String PINK_PAWN_PATH = BASIC_PATH + "PinkPawn.png";
    private static final String VIOLET_PAWN_PATH = BASIC_PATH + "VioletPawn.png";
    private static final String RAINBOW_PAWN_PATH = BASIC_PATH + "RainbowPawn.png";
    private static final String THERMO_PAWN_PATH = BASIC_PATH + "ThermoPawn.png";
    private static final String EGGS_PAWN_PATH = BASIC_PATH + "EggsPawn.png";
    private static final String ANONYMOUS_PAWN_PATH = BASIC_PATH + "AnonymousPawn.png";
    private static final String FANTASY_PAWN_PATH = BASIC_PATH + "FantasyPawn.png";
    private static final int N_PAWN = 14;

    private static final PawnTypes PAWN_TYPES = new PawnTypes();
    private final Map<AvailableColor, String> pawnColor;

    private PawnTypes() {

        final List<String> paths = Arrays.asList(RED_PAWN_PATH, LIGHTBLUE_PAWN_PATH, YELLOW_PAWN_PATH, GREEN_PAWN_PATH,
            FUCHSIA_PAWN_PATH, BLUE_PAWN_PATH, BROWN_PAWN_PATH, PINK_PAWN_PATH, VIOLET_PAWN_PATH, RAINBOW_PAWN_PATH,
            THERMO_PAWN_PATH, EGGS_PAWN_PATH, ANONYMOUS_PAWN_PATH, FANTASY_PAWN_PATH);

        final List<AvailableColor> colors = Arrays.asList(AvailableColor.values());

        this.pawnColor = IntStream.range(0, N_PAWN)
                 .boxed()
                 .collect(Collectors.toMap(i -> colors.get(i), i -> paths.get(i)));

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
