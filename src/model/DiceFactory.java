package model;

/**
 * A factory for various kind of dice.
 * It's designed using Factory Method pattern.
 */
public interface DiceFactory {

    /**
     * Creates and returns a classic dice with 6 sides.
     * @return a classic dice with 6 sides.
     */
    Dice createClassicDice();

    /**
     * Creates and returns a dice with 6 sides between the numbers 5 to 10 inclusive.
     * @return a dice with 6 sides between the numbers 5 to 10 inclusive.
     */
    Dice create5To10Dice();

    /**
     * Creates and returns a dice with 7 sides between the negative number -2 to the
     * positive number 5 inclusive. The number 0 is not consider as a dice's side.
     * @return a dice with 7 sides between the negative number -2 to the positive 
     * number 5 inclusive. The number 0 is not consider as a dice's side.
     */
    Dice createNegativeDice();

}
