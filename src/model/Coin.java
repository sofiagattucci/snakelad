package model;

import java.util.Random;

/**
 * Represents a coin which can be collected by the player inside game's sceneries.
 * It has a specific value and randomly decides to appear or not on the scenery's grid when 
 * isVisible() method is called, in order to to implement apparition's rarity of the coin.
 */
public final class Coin extends IntegerValueItems {

    private static final int COIN_VALUE = 1;
    private static final int NUMBER_UPPER_BOUND_RANDOM = 3; //It means that the coin will appear with probability
                                                            //of one third for each isVisible() method call. (Low rarity)

    private final Random rand = new Random();

    /**
     * Coin constructor. Initializes a superclass' field.
     * @param position
     *                 the coin's position on the scenery's grid.
     */
    public Coin(final int position) {
        super(COIN_VALUE, position);
    }

    /**
     * Returns the coin's value.
     * @return the coin's value.
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
