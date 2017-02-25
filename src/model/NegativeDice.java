package model;

import java.util.Optional;
import java.util.Random;

/**
 * Defines a dice with 7 sides between the negative number -2 to the
 * positive number 5 included. The number 0 is not considered as a dice side.
 * It's designed using Decorator pattern.
 */
public class NegativeDice implements Dice {

    private static final int MAX_NUMBER = 5;
    private static final int MIN_NUMBER = -2;
    private static final int DELTA = 3;
    private static final int RANDOM_BOUND = 2;

    private final Dice classicDice;
    private final Random rand = new Random(); 

    /**
     * NegativeDice constructor.
     * @param classicDice
     *                  a classic dice with 6 sides, Decorator pattern used.
     */
    public NegativeDice(final Dice classicDice) {
        super();
        this.classicDice = classicDice;
    }

    //calculates a number uniformly distributed between -2 and 5 included
    private int calculateFinalNumber(final int initialNumber) {
        if (initialNumber > MAX_NUMBER) {
            final int finalNumber = this.calculateFinalNumber(this.rand.nextInt(MAX_NUMBER) + 1);
            this.classicDice.setLastNumberAppeared(Optional.of(finalNumber));
            return finalNumber;
        }

        if (initialNumber >= DELTA) {
            this.classicDice.setLastNumberAppeared(Optional.of(initialNumber));
            return initialNumber;
        }

        final int bit = this.rand.nextInt(RANDOM_BOUND);
        if (bit == 0) {
            final int finalNumber = -initialNumber;
            this.classicDice.setLastNumberAppeared(Optional.of(finalNumber));
            return finalNumber; //negative number (either -2 or -1)
        }
        this.classicDice.setLastNumberAppeared(Optional.of(initialNumber));
        return  initialNumber; //positive number (either 2 or 1)
    }

    @Override
    public int roll() {
        return this.calculateFinalNumber(this.classicDice.roll());
    }

    @Override
    public void setLastNumberAppeared(final Optional<Integer> lastNumberAppeared) throws IllegalArgumentException {
        if (lastNumberAppeared.isPresent()) {
            if (lastNumberAppeared.get() < MIN_NUMBER || lastNumberAppeared.get() > MAX_NUMBER || lastNumberAppeared.get() == 0) {
                throw new IllegalArgumentException("Argument out of bounds (" + MIN_NUMBER + " and " + MAX_NUMBER + " included) or equal to 0");
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
