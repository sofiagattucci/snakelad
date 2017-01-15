package model.entities;

import java.util.Random;

/**
 * Defines a ClassicDice class which represents a dice with six sides. 
 */
public class ClassicDice implements Dice {

    private static final int NUMBER_OF_SIDES = 6;

    private final int numberOfSides = NUMBER_OF_SIDES;

    // we use default contructor

    @Override
    public int roll() {
        final Random randomNumber = new Random();
        return randomNumber.nextInt(this.numberOfSides) + 1;
    }

}