package tests.view;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import view.pawn.AvailableColor;
import view.pawn.PawnTypes;

/**
 * JUnit test for the class PawnTypes.
 */
public class PawnTypesTest {

    private static final String RED_PAWN_PATH = "pawns/RedPawn.png";
    private static final String LIGHTBLUE_PAWN_PATH = "pawns/LightBluePawn.png";
    private static final String YELLOW_PAWN_PATH = "pawns/YellowPawn.png";
    private static final String GREEN_PAWN_PATH = "pawns/GreenPawn.png";
    private static final String FUCHSIA_PAWN_PATH = "pawns/FuchsiaPawn.png";
    private static final String BLUE_PAWN_PATH = "pawns/BluePawn.png";
    private static final String BROWN_PAWN_PATH = "pawns/BrownPawn.png";
    private static final String PINK_PAWN_PATH = "pawns/PinkPawn.png";
    private static final String VIOLET_PAWN_PATH = "pawns/VioletPawn.png";

    /**
     * Starting JUnit Tests.
     */
    @Test
    public void test() {

        assertEquals(PawnTypes.get().getClass(), PawnTypes.class);
        assertEquals(PawnTypes.get().getPawn(AvailableColor.RED), RED_PAWN_PATH);
        assertEquals(PawnTypes.get().getPawn(AvailableColor.LIGHTBLUE), LIGHTBLUE_PAWN_PATH); 
        assertEquals(PawnTypes.get().getPawn(AvailableColor.YELLOW), YELLOW_PAWN_PATH);
        assertEquals(PawnTypes.get().getPawn(AvailableColor.GREEN), GREEN_PAWN_PATH);
        assertEquals(PawnTypes.get().getPawn(AvailableColor.FUCHSIA), FUCHSIA_PAWN_PATH);
        assertEquals(PawnTypes.get().getPawn(AvailableColor.BLUE), BLUE_PAWN_PATH);
        assertEquals(PawnTypes.get().getPawn(AvailableColor.BROWN), BROWN_PAWN_PATH);
        assertEquals(PawnTypes.get().getPawn(AvailableColor.PINK), PINK_PAWN_PATH);
        assertEquals(PawnTypes.get().getPawn(AvailableColor.VIOLET), VIOLET_PAWN_PATH);
    }
}
