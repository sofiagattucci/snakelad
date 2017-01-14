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

        /**.
         * Get Text file of instruction
         */
        void getInstructions();

        /**.
         * Quit game
         */
        void quit();

        /**.
         * Reset all game
         */
        void restart();

        /**.
         * Start new game
         */
        void play();

}
