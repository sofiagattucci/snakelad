package model.items;

import utilities.enumeration.TypesOfItem;

/**
 * Abstract class which shapes a basic item which has a position on the scenery's grid and whose 
 * effect is to return an integer value. This value can be used in different ways (to increase or 
 * decrease the player's scores for instance, etc.). 
 * It's designed using Template Method pattern.
 */
public abstract class IntegerReturningItem implements SpecialItem {

    private final int value;
    private final int position;

    /**
     * IntegerValueItems constructor.
     * @param itemValue
     *                  the value of the item.
     * @param position
     *                  the item's position on the scenery's grid.
     */
    public IntegerReturningItem(final int itemValue, final int position) {
        this.value = itemValue;
        this.position = position;
    }

    /**
     * Randomly returns a boolean value. It's true if the item "decides" to appear on the scenery's 
     * grid, false otherwise. It's used in order to implement apparition's rarity of the item.
     * @return true if the item "decides" to appear on the scenery's grid, false otherwise.
     */
    public abstract boolean isVisible(); //template method

    /**
     * Returns the item's type.
     * @return the item's type.
     */
    public abstract TypesOfItem getType(); //template method

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
    public int getPosition() {
        return this.position;
    }

    @Override
    public boolean isVisibleOnScenery() {
        return this.isVisible();
    }

    @Override
    public TypesOfItem getItemType() {
        return this.getType();
    }

}
