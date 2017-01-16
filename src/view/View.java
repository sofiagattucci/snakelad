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
    void setInstructions(final String text);

    /**
     * It resets the displayed values at the beginning of each game.
     */
    void firstTurn();
}
