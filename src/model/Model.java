package model;

import java.util.List;
import java.util.Optional;

import utilities.TypesOfDice;

/**
 * It represents the interface for the model in MVC pattern.
 * Defines the main methods of Model class.
 */
public interface Model {

    /**
     * Returns the name of the user who's playing the game.
     * @return the name of the user who's playing the game.
     */
    String getUserName();

    /**
     * Gets the scores of the user who's playing the game.
     * @return the scores of the user who's playing the game.
     */
    int getUserScores();

    /**
     * Return a random number rolling the dice.
     * @return the number released by the dice's roll.
     */
    int getNumberFromDice();

    /**
     * Sets everything needed in order to start the game.
     * @param data
     *          The list that contains the data (snakes and ladders positions, 
     *          number of cells on the game board) useful to start the game.
     * @param numberOfPlayers
     *          Number of players who want to play the game.
     * @param dice
     *          The dice which players want to play with.
     * @throws IllegalArgumentException if argument 'numberOfPlayers' or 'dice' is not permitted.
     */
    void startGame(List<Integer> data, int numberOfPlayers, TypesOfDice dice) throws IllegalArgumentException;

    /**
     * Calculate the position of the player whose index is specified, returning an 
     * Optional<Integer> which represents the movement of the required player.
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
     * Returns the side's size of the active game board.
     * @return the side's size of the active game board.
     */
    int getGameBoardSideSize();

    /**
     * Restarts the game, setting all needed in order to restart it.
     */
    void restartGame();


    /**
     * Quit the game, setting all needed in order to quit it.
     */
    void giveUpGame();

    /**
     * Tries to generate a coin, returning the Optional which describes the coin's position 
     * on the scenery's grid.
     * @return an Optional<Integer> if the coin has decided to appear on the scenery's grid and the 
     * Integer represents the coin's position, an Optional<Empty> if the coin has decided not to appear.
     */
    Optional<Integer> tryGenerateCoin();

    /**
     * Tries to generate a diamond, returning the Optional which describes the diamond's position 
     * on the scenery's grid.
     * @return an Optional<Integer> if the diamond has decided to appear on the scenery's grid and the 
     * Integer represents the diamond's position, an Optional<Empty> if the diamond has decided not to appear.
     */
    Optional<Integer> tryGenerateDiamond();

    /**
     * Tries to generate a skull, returning the Optional which describes the skull's position 
     * on the scenery's grid.
     * @return an Optional<Integer> if the skull has decided to appear on the scenery's grid and the 
     * Integer represents the skull's position, an Optional<Empty> if the skull has decided not to appear.
     */
    Optional<Integer> tryGenerateSkull();

    /**
     * Reports that the last player moved on the game grid has collected an item.
     * Sets everything needed to update the player's score.
     */
    void itemCollected();

}
