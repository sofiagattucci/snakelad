package model;

import java.util.Random;

/**
 * Represents a skull which can be hit by the player inside game's sceneries.
 * It has a specific value and randomly decides to appear or not on the scenery's grid 
 * when isVisible() method is called, in order to implement apparition's rarity of itself.
 */
public final class Skull extends IntegerValueItems {

    private static final int SKULL_VALUE = -2;
    private static final int NUMBER_UPPER_BOUND_RANDOM = 6; //It means that the skull will appear with probability
                                                            //of a sixth for each isVisible() method call. (Medium rarity)

    private final Random rand = new Random();

    /**
     * Skull constructor. Initializes a superclass' field.
     * @param position
     *                 the skull's position on the scenery's grid.
     */
    public Skull(final int position) {
        super(SKULL_VALUE, position);
    }

    /**
     * Returns the skull's value.
     * @return the skull's value.
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
