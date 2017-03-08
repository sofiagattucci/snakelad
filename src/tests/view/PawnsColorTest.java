package tests.view;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import view.pawn.AvailableColor;
import view.pawn.PawnsColor;

/**
 * JUnit test for PawnsColor class.
 */
public class PawnsColorTest {

    private static final int DOUBLE_PLAYERS = 6;

    /**
     * Starting JUnit tests.
     */
    @Test
    public void test() {

        assertEquals(PawnsColor.get().getClass(), PawnsColor.class);

        assertEquals(PawnsColor.get().getSingleColor(0), AvailableColor.RED);
        assertEquals(PawnsColor.get().getSingleColor(1), AvailableColor.LIGHTBLUE);

        assertEquals(PawnsColor.get().getMultiColor(0), AvailableColor.RED);
        assertEquals(PawnsColor.get().getMultiColor(1), AvailableColor.LIGHTBLUE);
        assertEquals(PawnsColor.get().getMultiColor(2), AvailableColor.YELLOW);
        assertEquals(PawnsColor.get().getMultiColor(3), AvailableColor.GREEN);
        assertEquals(PawnsColor.get().getMultiColor(4), AvailableColor.FUCHSIA);
        assertEquals(PawnsColor.get().getMultiColor(DOUBLE_PLAYERS - 1), AvailableColor.BLUE);

        for (final AvailableColor c: AvailableColor.values()) {
            PawnsColor.get().switchColorSingle(0, c);
            PawnsColor.get().switchColorSingle(1, c);
            assertEquals(PawnsColor.get().getSingleColor(0), c);
            assertEquals(PawnsColor.get().getSingleColor(1), c);

            PawnsColor.get().switchColorMulti(0, c);
            PawnsColor.get().switchColorMulti(1, c);
            PawnsColor.get().switchColorMulti(2, c);
            PawnsColor.get().switchColorMulti(3, c);
            PawnsColor.get().switchColorMulti(4, c);
            PawnsColor.get().switchColorMulti(DOUBLE_PLAYERS - 1, c);
            assertEquals(PawnsColor.get().getMultiColor(0), c);
            assertEquals(PawnsColor.get().getMultiColor(1), c);
            assertEquals(PawnsColor.get().getMultiColor(2), c);
            assertEquals(PawnsColor.get().getMultiColor(3), c);
            assertEquals(PawnsColor.get().getMultiColor(4), c);
            assertEquals(PawnsColor.get().getMultiColor(DOUBLE_PLAYERS - 1), c);
        }
    }

    /**
     * JUnit test to verify that an exception is thrown.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testSingleException() {
        PawnsColor.get().getSingleColor(2);
    }

    /**
     * JUnit test to verify that an exception is thrown.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testMultiException() {
        PawnsColor.get().getMultiColor(DOUBLE_PLAYERS); //List starting from index 0
    }
}
