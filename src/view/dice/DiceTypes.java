package view.dice;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import utilities.TypesOfDice;

/**
 * This class manages the different types of dices (images) available in the game.
 */
public final class DiceTypes {

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

    private static final DiceTypes INSTANCE = new DiceTypes();
    private final Map<TypesOfDice, Map<Integer, String>> diceMap = new HashMap<>();

    private DiceTypes() {

        final Map<Integer, String> classicDiceMap = IntStream.range(MIN_CLASSIC, MAX_CLASSIC + 1)
                                                             .boxed()
                                                             .collect(Collectors.toMap(i -> i,
                                                                  i -> STANDARD_DICE_PATH + CLASSIC_DICE + DICE_SIDE + i + PNG));

        final Map<Integer, String> to10DiceMap = IntStream.range(MIN_TO10, MAX_TO10 + 1)
                                                          .boxed()
                                                          .collect(Collectors.toMap(i -> i,
                                                               i -> STANDARD_DICE_PATH + TO10_DICE + DICE_SIDE + i + PNG));

        final Map<Integer, String> negativeDiceMap = new HashMap<>();
        for (int i = MIN_NEG; i <= MAX_NEG; i++) {
            if (i < 0) {
                negativeDiceMap.put(i, STANDARD_DICE_PATH + NEGATIVE_DICE + DICE_SIDE + (-i) + NEGATIVE + PNG);
            }
            if (i > 0) {
                negativeDiceMap.put(i, STANDARD_DICE_PATH + NEGATIVE_DICE + DICE_SIDE + i + POSITIVE + PNG);
            }

        }

        this.diceMap.put(TypesOfDice.CLASSIC_DICE, classicDiceMap);
        this.diceMap.put(TypesOfDice._5_TO_10_DICE, to10DiceMap);
        this.diceMap.put(TypesOfDice.NEGATIVE_DICE, negativeDiceMap);
    }

    /**
     * Getter of this class unique instance.
     * @return
     *     This class unique instance
     */
    public static DiceTypes get() {
        return INSTANCE;
    }

    /**
     * Getter of the map of the specified dice.
     * @param t
     *     The type of dice 
     * @return
     *     The map linked to the selected type of dice. That map holds its sprite(s).
     */
    public Map<Integer, String> getSpecificDiceMap(final TypesOfDice t) {
        return Collections.unmodifiableMap(this.diceMap.get(t));
    }

    /**
     * Getter of the number of dices available in the game.
     * @return
     *     The number of dices available in the game
     */
    public int getNumDices() {
        return this.diceMap.size();
    }
}
