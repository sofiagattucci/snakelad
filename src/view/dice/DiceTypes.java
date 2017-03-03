package view.dice;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    private static final DiceTypes INSTANCE = new DiceTypes();
    private final Map<TypesOfDice, Map<Integer, String>> diceMap = new HashMap<>();
    private final Map<Integer, String> classicDiceMap = new HashMap<>();
    private final Map<Integer, String> the5to10DiceMap = new HashMap<>();
    private final Map<Integer, String> negativeDiceMap = new HashMap<>();

    private DiceTypes() {

        for (int i = MIN_CLASSIC; i <= MAX_CLASSIC; i++) {
            this.classicDiceMap.put(i, "./res/Dice/ClassicDice/DiceSide" + i + ".png");
        }

        for (int i = MIN_TO10; i <= MAX_TO10; i++) {
            this.the5to10DiceMap.put(i, "./res/Dice/5to10Dice/DiceSide" + i + ".png");
        }

        for (int i = MIN_NEG; i <= MAX_NEG; i++) {
            if (i < 0) {
                this.negativeDiceMap.put(i, "./res/Dice/NegativeDice/DiceSide" + (-i) + "Negative.png");
            }
            if (i > 0) {
                this.negativeDiceMap.put(i, "./res/Dice/NegativeDice/DiceSide" + i + "Positive.png");
            }

        }

        this.diceMap.put(TypesOfDice.CLASSIC_DICE, this.classicDiceMap);
        this.diceMap.put(TypesOfDice._5_TO_10_DICE, this.the5to10DiceMap);
        this.diceMap.put(TypesOfDice.NEGATIVE_DICE, this.negativeDiceMap);
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
