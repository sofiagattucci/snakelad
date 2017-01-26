package model;

import java.util.Optional;
import java.util.Random;

/**
 * Defines a ClassicDice class which represents a dice with six sides. 
 */
public class ClassicDice implements Dice {

    private static final int NUMBER_OF_SIDES = 6;

    private final int numberOfSides = NUMBER_OF_SIDES;
    private Optional<Integer> lastNumberAppeared = Optional.empty();

    // default contructor used

    @Override
    public int roll() {

        final Random randomNumber = new Random();
        this.lastNumberAppeared = Optional.of(randomNumber.nextInt(this.numberOfSides) + 1);
        return this.lastNumberAppeared.get();
    }

    @Override
    public int getLastNumberAppeared() throws IllegalStateException {

        if (!this.lastNumberAppeared.isPresent()) {
            throw new IllegalStateException("ERROR using ClassicDice, the field lastNumberAppeared is empty!");
        }
        return this.lastNumberAppeared.get();
    }

}