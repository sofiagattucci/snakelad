package model;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import utilities.Pair;
import utilities.Statistic;
import utilities.enumeration.Jump;
import utilities.enumeration.Turn;
import utilities.enumeration.TypesOfDice;
import utilities.enumeration.TypesOfItem;

/**
 * It represents the interface for the model in MVC pattern.
 * Defines the main methods of Model class.
 */
public interface Model {

    /**
     * Return a random number rolling the dice.
     * @return the number released by the dice's roll.
     * @throws IllegalStateException if this method is called before startGame() method.
     */
    int rollDice() throws IllegalStateException;

    /**
     * Sets everything needed in order to start the game.
     * @param sceneryData
     *          The list that contains the data (snakes and ladders positions, 
     *          number of cells on the game board) useful to start the game.
     * @param numberOfPlayers
     *          Number of players who want to play the game.
     * @param dice
     *          The dice which players want to play with.
     * @throws IllegalArgumentException if argument 'numberOfPlayers' or 'dice' is not permitted.
     */
    void startGame(List<Integer> sceneryData, int numberOfPlayers, TypesOfDice dice) throws IllegalArgumentException;

    /**
     * Creates and returns a partial snapshot of a Model's subclass internal state.
     * @return a partial snapshot of a Model's subclass internal state.
     * @throws IllegalStateException if this method is called before startGame() method.
     */
    ModelMemento createMemento() throws IllegalStateException;

    /**
     * Sets the memento's state inside a Model's subclass so that the subclass can restore its internal state.
     * @param memento
     *                  The memento which contains the Model's subclass internal state to set.
     * @throws IllegalStateException if this method is called before startGame() method.
     */
    void setStateFromMemento(ModelMemento memento) throws IllegalStateException;

    /**
     * Calculate the position of the player whose index is specified, returning an 
     * Optional<Integer> which represents the movement of the required player.
     * @param playerIndex
     *          The index which identifies the player whose position is required.
     * @return a pair composed of an Optional<Integer> which represents the movement of the required player
     * and a type of jump. The Optional is an Optional.of(Integer) if the specified player has to jump (a 
     * "jump" means the achievement of either a snake or a ladder) and the Integer value represents the 
     * final player's position after the jump. Otherwise it's an Optional.empty, to indicate the specified
     * player shouldn't jump. The cause of jumping (snake, ladder or nothing) is identified by the second 
     * element of the pair.
     * @throws IllegalStateException if this method is called before startGame() method.
     */
    Pair<Optional<Integer>, Jump> getPlayerPosition(int playerIndex) throws IllegalStateException;

    /**
     * Returns the side's size of the active game board.
     * @return the side's size of the active game board.
     * @throws IllegalStateException if this method is called before startGame() method.
     */
    int getGameBoardSideSize() throws IllegalStateException;

    /**
     * Restarts the game, setting all needed in order to restart it.
     * @throws IllegalStateException if this method is called before startGame() method.
     */
    void restartGame() throws IllegalStateException;


    /**
     * Quit the game, setting all needed in order to quit it.
     * @throws IllegalStateException if this method is called before startGame() method.
     */
    void giveUpGame() throws IllegalStateException;

    /**
     * Tries to generate a coin, returning the Optional which describes the coin's position 
     * on the scenery's grid.
     * @return an Optional<Integer> if the coin has decided to appear on the scenery's grid and the 
     * Integer represents the coin's position, an Optional<Empty> if the coin has decided not to appear.
     * @throws IllegalStateException if this method is called before startGame() method.
     */
    Optional<Integer> tryGenerateCoin() throws IllegalStateException;

    /**
     * Tries to generate a diamond, returning the Optional which describes the diamond's position 
     * on the scenery's grid.
     * @return an Optional<Integer> if the diamond has decided to appear on the scenery's grid and the 
     * Integer represents the diamond's position, an Optional<Empty> if the diamond has decided not to appear.
     * @throws IllegalStateException if this method is called before startGame() method.
     */
    Optional<Integer> tryGenerateDiamond() throws IllegalStateException;

    /**
     * Tries to generate a skull, returning the Optional which describes the skull's position 
     * on the scenery's grid.
     * @return an Optional<Integer> if the skull has decided to appear on the scenery's grid and the 
     * Integer represents the skull's position, an Optional<Empty> if the skull has decided not to appear.
     * @throws IllegalStateException if this method is called before startGame() method.
     */
    Optional<Integer> tryGenerateSkull() throws IllegalStateException;

    /**
     * Reports that the user's pawn has collected an item on the game grid.
     * Sets everything needed to update the user's score.
     * @param itemIndex
     *                  The index which specifies the item.
     * @param turn
     *                  The turn which specify who has collected the item (Player of CPU).
     * @return an item's type.
     * @throws IllegalArgumentException if the item's index passed is not present in the Model.
     * @throws NoSuchElementException if there is a problem during deleting the item from Model's map of items.
     */
    TypesOfItem itemCollected(int itemIndex, Turn turn) throws IllegalArgumentException, NoSuchElementException;

    /**
     * Builds a Statistic object which contains all user's game statistics and returns it.
     * @return a Statistic object which contains all user's game statistics.
     */
    Statistic getStatistics();

    /**
     * Clears the current user's statistics.
     * @throws IOException if an error during clearing statistics inside file happened.
     */
    void clearStatistics() throws IOException;

    /**
     * Retorts the match is finished, so the user's statistics must be written inside the correct .properties file. 
     * @param turn
     *          The turn that specifies who won the game.
     * @throws IllegalStateException if this method is called before startGame() method.
     * @throws IOException if an error during writing statistics inside file happened.
     */
    void matchFinished(Turn turn) throws IllegalStateException, IOException;

}
