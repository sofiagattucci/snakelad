package tests.view;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import view.pawn.AvailableColor;
import view.pawn.PawnTypes;

/**
 * JUnit test for the class PawnTypes.
 */
public class PawnTypesTest {

    private static final String RED_PAWN_PATH = "./res/Pawns/RedPawn.png";
    private static final String LIGHTBLUE_PAWN_PATH = "./res/Pawns/LightBluePawn.png";
    private static final String YELLOW_PAWN_PATH = "./res/Pawns/YellowPawn.png";
    private static final String GREEN_PAWN_PATH = "./res/Pawns/GreenPawn.png";
    private static final String FUCHSIA_PAWN_PATH = "./res/Pawns/FuchsiaPawn.png";
    private static final String BLUE_PAWN_PATH = "./res/Pawns/BluePawn.png";
    private static final String BROWN_PAWN_PATH = "./res/Pawns/BrownPawn.png";
    private static final String PINK_PAWN_PATH = "./res/Pawns/PinkPawn.png";
    private static final String VIOLET_PAWN_PATH = "./res/Pawns/VioletPawn.png";

    /**
     * JUnit Tests.
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
