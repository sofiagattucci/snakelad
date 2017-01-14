package controller;

import model.Game;
import model.GameImpl;
import view.View;
import view.ViewImpl;

/**.
 * Class Controller
 *
 */
public class Controller implements ViewObserver {
    private Game game;
    private View view;

    /**.
     * Constructor
     */
    public Controller() {
        this.game = new GameImpl();
        this.view = new ViewImpl(this);
    }

    @Override
    public void rollDice() {
        int value = this.game.getNumberFromDice();
        this.view.setDiceValue(value);
    }

    @Override
        public void getInstructions() {
    }

    @Override
    public void quit() {
        this.game.giveUpGame();
    }

    @Override
    public void restart() {
        this.game.restartGame();
    }

    @Override
    public void play() {
        this.game.startGame();
    }
    /**.
     * Start the view
     */
    public void startApplication() {
        this.view.start();
    }

}
