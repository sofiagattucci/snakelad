package controller;

import model.Model;
import model.GameImpl;
import view.View;
import view.ViewImpl;

/**.
 * Class Controller
 *
 */
public class Controller implements ViewObserver {
    private Model model;
    private View view;

    /**.
     * Constructor
     */
    public Controller() {
        this.model = new GameImpl();
        this.view = new ViewImpl(this);
    }

    @Override
    public void rollDice() {
        int value = this.model.getRandomNumber();
        this.view.setDiceValue(value);
    }

    @Override
        public void instruction() {
    }

    @Override
    public void quit() {
        this.model.reset();
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void play() {
        this.model.newGame();
    }

    public void startApplication() {
        this.view.start();
    }

}
