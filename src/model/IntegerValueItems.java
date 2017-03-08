package model;

/**
 * Abstract class which shapes a basic item whose effect is to return an integer value. This value
 * may be used in different ways (to increase or decrease the player's scores for instance, etc.). 
 * It's designed using Template Method.
 */
public abstract class IntegerValueItems implements SpecialItems {

    private final int value;

    /**
     * IntegerValueItems constructor.
     * @param itemValue
     *                  the value of the item.
     */
    public IntegerValueItems(final int itemValue) {
        this.value = itemValue;
    }

    /**
     * Randomly returns a boolean value. It's true if the item "decides" to appear on the scenery's 
     * grid, false otherwise. It's used in order to implement apparition's rarity of the item.
     * @return true if the item "decides" to appear on the scenery's grid, false otherwise.
     */
    public abstract boolean isVisible(); //template method

    /**
     * Returns the number which represents the item's value.
     * @return the number which represents the item's value.
     */
    public int getItemValue() {
        return this.value;
    }

    @Override
    public Object runEffectGettingResult() {
        return this.getItemValue();
    }

    @Override
    public boolean isVisibleOnSceneryGrid() {
        return this.isVisible();
    }

}
