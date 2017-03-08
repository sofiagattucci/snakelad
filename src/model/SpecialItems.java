package model;

/**
 * An interface for special items inside the game. A 'special items' is an entity which can 
 * be collected in sceneries by the player and produces a kind of specified effect (improve
 * scores for instance, etc.). This special entity randomly decides to appear or not on the 
 * scenery's grid (isVisibleOnSceneryGrid() method).
 */
public interface SpecialItems {

    /**
     * Runs the entity's specified effect getting the result of it.
     * @return the result of the effect.
     */
    Object runEffectGettingResult();

    /**
     * Randomly returns a boolean value. It's true if the special item "decides" to appear
     * on the scenery's grid, false otherwise.
     * @return true if the special item "decides" to appear on the scenery's grid, false otherwise.
     */
    boolean isVisibleOnSceneryGrid();

}
