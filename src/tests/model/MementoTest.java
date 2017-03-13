package tests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.Optional;
import model.CareMementoTaker;
import model.ModelMemento;

/**
 * Junit test used in order to test ModelMemento and CareMementoTaker classes.
 * This class has to achieve success in all its tests.
 */
public final class MementoTest {

    private static final int BIG_NUMBER = 85342;

    private final CareMementoTaker mementoTaker = CareMementoTaker.get();

    /**
     * Tests methods inside ModelMemento and CareMementoTaker classes.
     */
    @Test
    public void testMemento() {

        this.mementoTaker.addMemento(new ModelMemento(Optional.empty(), 0, 0, 0, true, 0));
        ModelMemento memento;
        memento = this.mementoTaker.getMemento();

        assertEquals(memento.getLastNumberAppearedOnDice(), Optional.empty());
        assertEquals(memento.getMaxItemsGeneration(), 0);
        assertEquals(memento.getItemsCollected(), 0);
        assertEquals(memento.getNumberOfDiceRoll(), 0);
        assertTrue(memento.isPlayerTurn());
        assertEquals(memento.getUserScores(), 0);

        this.mementoTaker.addMemento(new ModelMemento(Optional.of(0), 1, 0, 1, false, 0));
        memento = this.mementoTaker.getMemento();

        assertEquals(memento.getLastNumberAppearedOnDice(), Optional.of(0));
        assertEquals(memento.getMaxItemsGeneration(), 1);
        assertEquals(memento.getItemsCollected(), 0);
        assertEquals(memento.getNumberOfDiceRoll(), 1);
        assertFalse(memento.isPlayerTurn());
        assertEquals(memento.getUserScores(), 0);

        this.mementoTaker.addMemento(new ModelMemento(Optional.of(1), 1, 1, 1, false, 1));
        memento = this.mementoTaker.getMemento();

        assertEquals(memento.getLastNumberAppearedOnDice(), Optional.of(1));
        assertEquals(memento.getMaxItemsGeneration(), 1);
        assertEquals(memento.getItemsCollected(), 1);
        assertEquals(memento.getNumberOfDiceRoll(), 1);
        assertFalse(memento.isPlayerTurn());
        assertEquals(memento.getUserScores(), 1);

        this.mementoTaker.addMemento(memento);
        memento = this.mementoTaker.getMemento();

        assertEquals(memento.getLastNumberAppearedOnDice(), Optional.of(1));
        assertEquals(memento.getMaxItemsGeneration(), 1);
        assertEquals(memento.getItemsCollected(), 1);
        assertEquals(memento.getNumberOfDiceRoll(), 1);
        assertFalse(memento.isPlayerTurn());
        assertEquals(memento.getUserScores(), 1);

        this.mementoTaker.addMemento(this.mementoTaker.getMemento());
        memento = this.mementoTaker.getMemento();

        assertEquals(memento.getLastNumberAppearedOnDice(), Optional.of(1));
        assertEquals(memento.getMaxItemsGeneration(), 1);
        assertEquals(memento.getItemsCollected(), 1);
        assertEquals(memento.getNumberOfDiceRoll(), 1);
        assertFalse(memento.isPlayerTurn());
        assertEquals(memento.getUserScores(), 1);

        this.mementoTaker.addMemento(new ModelMemento(Optional.of(BIG_NUMBER), BIG_NUMBER, BIG_NUMBER, BIG_NUMBER, true, BIG_NUMBER));
        memento = this.mementoTaker.getMemento();

        assertEquals(memento.getLastNumberAppearedOnDice(), Optional.of(BIG_NUMBER));
        assertEquals(memento.getMaxItemsGeneration(), BIG_NUMBER);
        assertEquals(memento.getItemsCollected(), BIG_NUMBER);
        assertEquals(memento.getNumberOfDiceRoll(), BIG_NUMBER);
        assertTrue(memento.isPlayerTurn());
        assertEquals(memento.getUserScores(), BIG_NUMBER);

    }

}