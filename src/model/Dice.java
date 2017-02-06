package model;

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
     * Gets the last number released rolling the dice.
     * @return the last number released rolling the dice.
     */
    int getLastNumberAppeared();

}