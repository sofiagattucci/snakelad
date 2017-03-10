package model.items;

/**
 * An interface for special items inside the game. A 'special items' is game's entity which can be collected in 
 * sceneries' grid by the player and produces a kind of specified effect (returns an integer value, returns 
 * a string, helps the player to win the game, changes the background music, etc.). This special items can 
 * randomly decide to appear or not on the scenery's grid when the isVisibleOnSceneryGrid() method is called
 * in order to implement apparition's rarity of the item.
 */
public interface SpecialItems {

    /**
     * Runs the entity's specified effect getting a result of it.
     * @return a result of the effect.
     */
    Object runEffectGettingResult();

    /**
     * Returns a number which represents the position of the special item.
     * @return a number which represents the position of the special item.
     */
    int getPosition();

    /**
     * Randomly returns a boolean value. It's true if the special item "decides" to appear
     * on the scenery's grid, false otherwise.
     * @return true if the special item "decides" to appear on the scenery's grid, false otherwise.
     */
    boolean isVisibleOnSceneryGrid();

}
