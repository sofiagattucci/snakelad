package model.dice;

import java.util.Optional;

/**
 * Defines a dice with 6 sides between the numbers 5 to 10 included.
 * It's designed using Decorator pattern.
 */
public final class Dice5To10 implements Dice {

    private static final int DELTA = 4;
    private static final int MIN_NUMBER = 5;
    private static final int MAX_NUMBER = 10;

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
        final int finalNumber = this.classicDice.roll() + DELTA;
        this.classicDice.setLastNumberAppeared(Optional.of(finalNumber));
        return finalNumber;
    }

    @Override
    public void setLastNumberAppeared(final Optional<Integer> lastNumberAppeared) throws IllegalArgumentException {
        if (lastNumberAppeared.isPresent()) {
            if (lastNumberAppeared.get() < MIN_NUMBER || lastNumberAppeared.get() > MAX_NUMBER) {
                throw new IllegalArgumentException("Argument out of bounds (" + MIN_NUMBER + " and " + MAX_NUMBER + " included)");
            }
            this.classicDice.setLastNumberAppeared(lastNumberAppeared);
        } else {
            this.classicDice.setLastNumberAppeared(Optional.empty()); //reset the dice to initial configuration.
        }
    }

    @Override
    public int getLastNumberAppeared() throws IllegalStateException {
        return this.classicDice.getLastNumberAppeared();
    }

}
