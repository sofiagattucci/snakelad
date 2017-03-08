package model;

import java.util.Random;

/**
 * Represents a diamond which can be collected by the player inside game's sceneries.
 * It has a specific value and randomly decides to appear or not on the scenery's grid when 
 * isVisible() method is called, in order to to implement apparition's rarity of the diamond.
 */
public final class Diamond extends IntegerValueItems {

    private static final int DIAMOND_VALUE = 10;
    private static final int NUMBER_UPPER_BOUND_RANDOM = 10; //It means that the diamond will appear with probability
                                                             //of a tenth for each isVisible() method call. (High rarity)

    private final Random rand = new Random();

    /**
     * Coin constructor. Initializes a superclass' field.
     */
    public Diamond() {
        super(DIAMOND_VALUE);
    }

    /**
     * Returns the diamond's value.
     * @return the diamond's value.
     */
    public int getValue() {
        return super.getItemValue();
    }

    @Override
    public boolean isVisible() {
        final int randResult = rand.nextInt(NUMBER_UPPER_BOUND_RANDOM);
        return randResult == 0;
    }

}