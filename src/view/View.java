package view;

/**
 * Interface for the View.
 */
public interface View {

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
     *     The new turn
     * @param newDiceValue
     *     The new new dice value.
     */
    void updateInfo(String turn, int newDiceValue);

    /**
     * It updates the game screen each turn.
     * @param turn
     *     The new turn
     * @param newDiceValue
     *     The new new dice value.
     * @param finalPosition
     *     The final position if the pawn stops on a snake/ladder
     */
    void updateInfo(String turn, int newDiceValue, final int finalPosition);
}
