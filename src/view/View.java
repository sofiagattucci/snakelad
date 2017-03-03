package view;

import java.util.Map;

/**
 * Interface for the View.
 */
public interface View {

    /**
     * It starts the application.
     */ 
    void start();

    /**
     * It resets the displayed values at the beginning of each game.
     */
    void firstTurn();

    /**
     * It updates the game screen each turn. No jump to be done in this turn
     * @param newDiceValue
     *     The new new dice value.
     */
    void updateInfo(int newDiceValue);

    /**
     * It updates the game screen each turn. Required a jump to be done in this turn.
     * @param newDiceValue
     *     The new new dice value.
     * @param finalPosition
     *     The final position if the pawn stops on a snake/ladder
     */
    void updateInfo(int newDiceValue,  int finalPosition);

    /**
     * It updates the language map with the informations of the new language.
     * @param map
     *     The new map containing the new language.
     */
    void setLanguageMap(Map<String, String> map);

    /**
     * It sets the size per side of the active game board.
     * @param n
     *     The number of boxes per side of the active game board
     */
    void setBoardSize(int n);

    /**
     * It sets the elements of the GUI that manage the music.
     * @param min
     *     The minimum value of the music volume
     * @param max
     *     The maximum value of the music volume
     * @param current
     *     The current value of the music volume
     */
    void setMusicVolume(int min, int max, int current);
}
