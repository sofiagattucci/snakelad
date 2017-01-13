package model.entities;

/**
 * Defines a ClassicDice class which represents a dice with six sides. 
 */
public class ClassicDice implements Dice {

    private static final int NUMBER_OF_SIDES = 6;

    private final int numberOfSides = NUMBER_OF_SIDES;

    @Override
    public int roll() {
        return this.numberOfSides;
    }

}
