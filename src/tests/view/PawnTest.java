package tests.view;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import view.Direction;
import view.pawns.Pawn;
import view.pawns.PawnImpl;
import view.pawns.PawnTypes;

/**
 * Junit test used to test the PawnImpl class in package view.pawns.
 */
public final class PawnTest {

    private final Pawn pawn = new PawnImpl(PawnTypes.get().getPawn(0));

    /**
     * JUnit tests.
     */
    @Test
    public void pawnTest() {
        assertEquals(this.pawn.getPositionInRow(), 0);
        assertEquals(this.pawn.getPositionInRow(), 0);
        assertEquals(this.pawn.getDirection(), Direction.RIGHT);
        this.pawn.setRow(1);
        assertEquals(this.pawn.getRow(), 1);
        this.pawn.setPositionInRow(1);
        assertEquals(this.pawn.getPositionInRow(), 1);
        this.pawn.changeDirection();
        assertEquals(this.pawn.getDirection(), Direction.LEFT);
        this.pawn.changeDirection();
        assertEquals(this.pawn.getDirection(), Direction.RIGHT);
        this.pawn.reset();
        assertEquals(this.pawn.getPositionInRow(), 0);
        assertEquals(this.pawn.getPositionInRow(), 0);
        assertEquals(this.pawn.getDirection(), Direction.RIGHT);
    }
}
