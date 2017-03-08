package model;

/**
 * An interface for special items inside the game. A 'special items' is an entity which can 
 * be collected in sceneries by the player and produces a kind of specified effect (improve
 * scores for instance, etc.).
 */
public interface SpecialItems {

    /**
     * Runs the entity's specified effect getting the result of it.
     * @return the result of the effect.
     */
    Object runEffectGettingResult();

}
