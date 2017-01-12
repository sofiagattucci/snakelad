package controller;

import model.Model;
import model.ModelImpl;
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
        this.model = new ModelImpl();
        this.view = new ViewImpl(new Controller());
    }

    @Override
    public void rollDice() {
        int value = model.getRandomNumber();
        view.setDiceValue(value);
    }

    @Override
        public void instruction() {
    }

    @Override
    public void quit() {
        model.reset();
    }

    @Override
    public void resetGame() {
        model.reset();
    }

    @Override
    public void play() {
        model.newGame();
    }

    public void startApplication() {
        view.start();
    }

}
