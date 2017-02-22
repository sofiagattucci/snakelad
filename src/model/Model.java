package model;

import java.util.Optional;

/**
 * It represents the interface for the model in MVC pattern.
 * Defines the main methods of Model class.
 */
public interface Model {

    /**
     * Return a random number rolling the dice.
     * @return the number released by the dice's roll.
     */
    int getNumberFromDice();

    /**
     * Returns an Optional<Integer> which represents the movement of the required player.
     * @param playerIndex
     *          The index which identifies the player whose position is required.
     * @return an Optional<Integer> which represents the movement of the required player. It's 
     * an Optional.of(Integer) if the specified player has to jump (a "jump" means the achievement 
     * of either a snake or a ladder) and the Integer value represents the final player's position 
     * after the jump. Otherwise it's an Optional.empty, to indicate the specified player shouldn't
     * jump (in this case the View'll calculate the final player's position on the game board). 
     */
    Optional<Integer> getPlayerPosition(int playerIndex);

    /**
     * Restarts the game, setting all needed in order to restart it.
     */
    void restartGame();


    /**
     * Quit the game, setting all needed in order to quit it.
     */
    void giveUpGame();

}
