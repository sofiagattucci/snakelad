package view;

/**
 * Interface for the View.
 */
public interface View {

    /**
     * It sets the dice value in play scene.
     * @param value
     *     The value to be set
     */
    void setDiceValue(int value);

    /**
     * It starts the application.
     */ 
    void start();

    /**
     * It changes the turn shown in the GUI.
     * @param turn
     *     the new turn.
     */
    void showTurn(String turn);

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
     * It updates the game screen each turn.
     * @param turn
     *     the new turn
     * @param newDiceValue
     *     The new new dice value.
     * @param jump
     *     If there' s a snake/ladder in the arrival box so I need to change the pawn position
     * @param finalPosition
     *     The final position if the pawn stops on a snake/ladder
     */
    void updateInfo(String turn, int newDiceValue, final boolean jump, final int finalPosition);
}
