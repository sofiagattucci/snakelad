package view;

import controller.Turn;

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
    void showTurn(Turn turn);
}
