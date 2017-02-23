package model;

import java.util.Optional;

/**
 * Defines a dice with 6 sides between the numbers 5 to 10 inclusive.
 * It's designed using Decorator pattern.
 */
public final class Dice5To10 implements Dice {

    private static final int DELTA = 4;

    private final Dice classicDice;

    /**
     * Dice5To10 constructor.
     * @param classicDice
     *                  a classic dice with 6 sides, Decorator pattern used.
     */
    public Dice5To10(final Dice classicDice) {
        super();
        this.classicDice = classicDice;
    }

    @Override
    public int roll() {
        return this.classicDice.roll() + DELTA;
    }

    @Override
    public void setLastNumberAppeared(final Optional<Integer> lastNumberAppeared) {
        this.classicDice.setLastNumberAppeared(lastNumberAppeared);
    }

    @Override
    public int getLastNumberAppeared() throws IllegalStateException {
        return this.classicDice.getLastNumberAppeared() + DELTA;
    }

}
