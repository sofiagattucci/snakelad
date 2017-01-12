package controller;

/**.
 *Interface of Controller
 *
 */

public interface ViewObserver {

        /**.
         * Set the value of dice
         */
        void rollDice();

        /**
         * 
         */
        void instruction();

        /**.
         * Quit game
         */
        void quit();

        /**.
         * Reset all game
         */
        void resetGame();

        /**.
         * Start new game
         */
        void play();

}
