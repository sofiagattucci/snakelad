package controller;

import model.Model;
import view.View;

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
    /**.
     * Main to start application
     * @param args
     */
    public static void main(final String[] args) {
        Controller controller = new Controller();
        controller.startApplication();
    }

}
