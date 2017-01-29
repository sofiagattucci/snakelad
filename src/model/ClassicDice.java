package model;

import java.util.Optional;
import java.util.Random;

/**
 * Final class which defines a ClassicDice representing a dice with six sides.
 * It's designed using Singleton pattern.
 */
public final class ClassicDice implements Dice {

    private static final ClassicDice SINGLETON = new ClassicDice();
    private static final int NUMBER_OF_SIDES = 6;

    private final int numberOfSides;
    private Optional<Integer> lastNumberAppeared;

    // private constructor
    private ClassicDice() {
        this.numberOfSides = NUMBER_OF_SIDES;
        this.lastNumberAppeared = Optional.empty();
    }

    /**
     * Static method which returns a ClassicDice unique instance.
     * @return a ClassicDice unique instance.
     */
    public static ClassicDice getClassicDice() {
        return ClassicDice.SINGLETON;
    }

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