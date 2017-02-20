package view;

import utilities.Turn;

/**
 * Interface for the View.
 */
public interface View {

    /**
     * It starts the application.
     */ 
    void start();

    /**
     * It sets the instructions' text shown in the GUI.
     * @param text
     *     The text to set.
     */
    void setInstructions(String text);

    /**
     * It resets the displayed values at the beginning of each game.
     */
    void firstTurn();

    /**
     * It updates the game screen each turn. No jump to be done in this turn
     * @param nextTurn
     *     The next turn
     * @param newDiceValue
     *     The new new dice value.
     */
    void updateInfo(Turn nextTurn, int newDiceValue);

    /**
     * It updates the game screen each turn. Required a jump to be done in this turn.
     * @param nextTurn
     *     The next turn
     * @param newDiceValue
     *     The new new dice value.
     * @param finalPosition
     *     The final position if the pawn stops on a snake/ladder
     */
    void updateInfo(Turn nextTurn, int newDiceValue,  int finalPosition);
}
