package tests.view;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import utilities.TypesOfDice;
import view.dice.DiceTypes;

/**
 * JUnit test for the class DiceTypes.
 */
public class DiceTypesTest {

    private static final int N_DICES = 3;
    private static final int N_SIDES_CLASSIC = 6;
    private static final int N_SIDES_TO10 = 6;
    private static final int N_SIDES_NEGATIVE = 7;
    private static final int MIN_CLASSIC = 1;
    private static final int MAX_CLASSIC = 6;
    private static final int MIN_TO10 = 5;
    private static final int MAX_TO10 = 10;
    private static final int MIN_NEG = -2;
    private static final int MAX_NEG = 5;
    private static final String STANDARD_DICE_PATH = "./res/Dice/";
    private static final String DICE_SIDE = "DiceSide";
    private static final String PNG = ".png";
    private static final String CLASSIC_DICE = "ClassicDice/";
    private static final String TO10_DICE = "5to10Dice/";
    private static final String NEGATIVE_DICE = "NegativeDice/";
    private static final String NEGATIVE = "Negative";
    private static final String POSITIVE = "Positive";

    /**
     * JUnit Tests.
     */
    @Test
    public void test() {

        assertEquals(DiceTypes.get().getClass(), DiceTypes.class);
        assertEquals(DiceTypes.get().getNumDices(), N_DICES);
        assertEquals(DiceTypes.get().getSpecificDiceMap(TypesOfDice.CLASSIC_DICE).size(), N_SIDES_CLASSIC);
        assertEquals(DiceTypes.get().getSpecificDiceMap(TypesOfDice._5_TO_10_DICE).size(), N_SIDES_TO10);
        assertEquals(DiceTypes.get().getSpecificDiceMap(TypesOfDice.NEGATIVE_DICE).size(), N_SIDES_NEGATIVE);

        for (int i = MIN_CLASSIC; i <= MAX_CLASSIC; i++) {
            assertEquals(DiceTypes.get().getSpecificDiceMap(TypesOfDice.CLASSIC_DICE).get(i),
                    STANDARD_DICE_PATH + CLASSIC_DICE + DICE_SIDE + i + PNG);
        }
        for (int i = MIN_TO10; i <= MAX_TO10; i++) {
            assertEquals(DiceTypes.get().getSpecificDiceMap(TypesOfDice._5_TO_10_DICE).get(i),
                    STANDARD_DICE_PATH + TO10_DICE + DICE_SIDE + i + PNG);
        }
        for (int i = MIN_NEG; i <= MAX_NEG; i++) {
            if (i < 0) {
                assertEquals(DiceTypes.get().getSpecificDiceMap(TypesOfDice.NEGATIVE_DICE).get(i),
                        STANDARD_DICE_PATH + NEGATIVE_DICE + DICE_SIDE + (-i) + NEGATIVE + PNG);
            }
            if (i > 0) {
                assertEquals(DiceTypes.get().getSpecificDiceMap(TypesOfDice.NEGATIVE_DICE).get(i),
                        STANDARD_DICE_PATH + NEGATIVE_DICE + DICE_SIDE + i + POSITIVE + PNG);
            }
        }
    }

    /**
     * JUnit test to verify that an exception is thrown.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testException() {
        for (final TypesOfDice dice: TypesOfDice.values()) {
            DiceTypes.get().getSpecificDiceMap(dice).put(0, "A");
        }
    }
}
