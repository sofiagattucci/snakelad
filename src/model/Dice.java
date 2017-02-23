package model;

import java.util.Optional;

/** 
 * Interface of a generic Dice. 
 * Defines default dice's methods inside the model.
 */
public interface Dice {

    /**
     * Returns a number released rolling the dice.
     * @return the number released rolling the dice. 
     */
    int roll();

    /**
     * Sets the last number appeared from dice.
     * @param lastNumberAppeared
     *                  The Optional describing the last number appeared from dice. It's an
     *                  Optional.empty if you want to reset the dice to initial configuration.
     */
    void setLastNumberAppeared(Optional<Integer> lastNumberAppeared);


    /**
     * Gets the last number released rolling the dice.
     * @return the last number released rolling the dice.
     * @throws IllegalStateException if it called when is empty (with no previous number appeared).
     */
    int getLastNumberAppeared() throws IllegalStateException;

}