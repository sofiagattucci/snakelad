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
    private ViewObserver controller;

    /**.
     * Constructor
     */
    public Controller() {
        controller = new Controller();
        model = new ModelImpl();
        view = new ViewImpl(controller);
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

    private void startApplication() {
        view.start();
    }

}
