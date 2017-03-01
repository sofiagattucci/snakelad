package model;

import java.util.Optional;
import java.util.Random;

/**
 * Defines a classic dice with 6 sides.
 * It's designed using Singleton pattern.
 */
public final class ClassicDice implements Dice {

    private static final ClassicDice SINGLETON = new ClassicDice();
    private static final int NUMBER_OF_SIDES = 6;

    private Optional<Integer> lastNumberAppeared;

    //private constructor
    private ClassicDice() {
        this.lastNumberAppeared = Optional.empty();
    }

    /**
     * Static method which returns a ClassicDice unique instance.
     * @return a ClassicDice unique instance.
     */
    public static Dice get() {
        return ClassicDice.SINGLETON;
    }

    @Override
    public int roll() {
        final Random randomNumber = new Random();
        this.lastNumberAppeared = Optional.of(randomNumber.nextInt(NUMBER_OF_SIDES) + 1);
        return this.lastNumberAppeared.get();
    }

    @Override
    public void setLastNumberAppeared(final Optional<Integer> lastNumberAppeared) {
            this.lastNumberAppeared = lastNumberAppeared;
    }

    @Override
    public int getLastNumberAppeared() throws IllegalStateException {
        if (!this.lastNumberAppeared.isPresent()) {
            throw new IllegalStateException("ERROR using ClassicDice, the field lastNumberAppeared is empty!");
        }
        return this.lastNumberAppeared.get();
    }

}