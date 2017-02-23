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
     * Creates and returns a dice with 6 sides between the numbers 5 to 10 included.
     * @param dice
     *          a dice passed to this method using Decorator pattern.
     * @return a dice with 6 sides between the numbers 5 to 10 included.
     */
    Dice create5To10Dice(Dice dice);

    /**
     * Creates and returns a dice with 7 sides between the negative number -2 to the
     * positive number 5 included. The number 0 is not consider as a dice's side.
     * @param dice
     *          a dice passed to this method using Decorator pattern.
     * @return a dice with 7 sides between the negative number -2 to the positive 
     * number 5 included. The number 0 is not consider as a dice's side.
     */
    Dice createNegativeDice(Dice dice);

}
