package tests.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.stream.IntStream;

import org.junit.Test;

import utilities.ConsoleLog;
import utilities.enumeration.TypesOfDice;
import view.dice.DiceTypes;

/**
 * JUnit test for the class DiceTypes.
 */
public class DiceTypesTest {

    private static final int N_SIDES_CLASSIC = 6;
    private static final int N_SIDES_TO10 = 6;
    private static final int N_SIDES_NEGATIVE = 7;
    private static final int MIN_CLASSIC = 1;
    private static final int MAX_CLASSIC = 6;
    private static final int MIN_TO10 = 5;
    private static final int MAX_TO10 = 10;
    private static final int MIN_NEG = -2;
    private static final int MAX_NEG = 5;
    private static final String STANDARD_DICE_PATH = "dices/";
    private static final String DICE_SIDE = "DiceSide";
    private static final String PNG = ".png";
    private static final String CLASSIC_DICE = "classicDice/";
    private static final String TO10_DICE = "_5to10Dice/";
    private static final String NEGATIVE_DICE = "negativeDice/";
    private static final String NEGATIVE = "Negative";
    private static final String POSITIVE = "Positive";

    /**
     * JUnit Tests.
     */
    @Test
    public void test() {

        assertEquals(DiceTypes.get().getClass(), DiceTypes.class);
        assertEquals(DiceTypes.get().getSpecificDiceMap(TypesOfDice.CLASSIC_DICE).size(), N_SIDES_CLASSIC);
        assertEquals(DiceTypes.get().getSpecificDiceMap(TypesOfDice._5_TO_10_DICE).size(), N_SIDES_TO10);
        assertEquals(DiceTypes.get().getSpecificDiceMap(TypesOfDice.NEGATIVE_DICE).size(), N_SIDES_NEGATIVE);

        IntStream.range(MIN_CLASSIC, MAX_CLASSIC + 1)
                 .forEach(i -> assertEquals(DiceTypes.get().getSpecificDiceMap(TypesOfDice.CLASSIC_DICE).get(i),
                         STANDARD_DICE_PATH + CLASSIC_DICE + DICE_SIDE + i + PNG));

        IntStream.range(MIN_TO10, MAX_TO10 + 1)
                 .forEach(i -> assertEquals(DiceTypes.get().getSpecificDiceMap(TypesOfDice._5_TO_10_DICE).get(i),
                    STANDARD_DICE_PATH + TO10_DICE + DICE_SIDE + i + PNG));

        IntStream.range(MIN_NEG, MAX_NEG)
                 .forEach(i -> {
                             if (i < 0) {
                                  assertEquals(DiceTypes.get().getSpecificDiceMap(TypesOfDice.NEGATIVE_DICE).get(i),
                                      STANDARD_DICE_PATH + NEGATIVE_DICE + DICE_SIDE + (-i) + NEGATIVE + PNG);
                             }
                             if (i > 0) {
                                 assertEquals(DiceTypes.get().getSpecificDiceMap(TypesOfDice.NEGATIVE_DICE).get(i),
                                     STANDARD_DICE_PATH + NEGATIVE_DICE + DICE_SIDE + i + POSITIVE + PNG);
                             }
                         });

        for (final TypesOfDice dice: TypesOfDice.values()) {
            try {
                DiceTypes.get().getSpecificDiceMap(dice).put(0, "A");
                fail("Failed test in DiceTypesTest line 68");
            } catch (UnsupportedOperationException e) {
                ConsoleLog.get().print("UnsupportedOperationException thrown witch success in DiceTypesTest line 68");
            }
        }
    }
}
