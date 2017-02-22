package tests.view;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import view.pawns.PawnTypes;

/**
 * Junit test used to test the PawnTypes class in package view.pawns.
 */
public class PawnTypesTest {

    private static final String RED_PAWN_PATH = "./res/Pawns/RedPawn.png";
    private static final String LIGHTBLUE_PAWN_PATH = "./res/Pawns/LightBluePawn.png";
    private static final String YELLOW_PAWN_PATH = "./res/Pawns/YellowPawn.png";
    private static final String GREEN_PAWN_PATH = "./res/Pawns/GreenPawn.png";
    private static final String FUCHSIA_PAWN_PATH = "./res/Pawns/FuchsiaPawn.png";
    private static final int NUM_COLORS = 5;

    private final List<String> pawnColor = new ArrayList<>(Arrays.asList(RED_PAWN_PATH, LIGHTBLUE_PAWN_PATH, YELLOW_PAWN_PATH,
            GREEN_PAWN_PATH, FUCHSIA_PAWN_PATH));


    /**
     * JUnit tests.
     */
    @Test
    public void pawnTypesTest() {
        for (int i = 0; i < NUM_COLORS; i++) {
            assertEquals(PawnTypes.get().getPawn(i), pawnColor.get(i));
        }
    }
}
