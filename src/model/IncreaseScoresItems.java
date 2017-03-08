package model;

/**
 * Abstract class which shapes a basic items useful to increase player's scores in the game.
 * It's designed using Template Method.
 */
public abstract class IncreaseScoresItems implements SpecialItems {

    private final int value;

    /**
     * EarningItems constructor.
     * @param itemValue
     *                  the value of the item.
     */
    public IncreaseScoresItems(final int itemValue) {
        this.value = itemValue;
    }

    /**
     * Randomly returns a boolean value. It's true if the item "decides" to appear on the 
     * scenery's grid, false otherwise.
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
