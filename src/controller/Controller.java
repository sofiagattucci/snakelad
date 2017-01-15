package controller;

import java.io.File;

import model.Game;
import model.GameImpl;
import utility.FileManager;
import view.View;
import view.ViewImpl;

/**
 * Class Controller.
 *
 */
public class Controller implements ViewObserver {
    private final Game game;
    private final View view;
    private String turn;
    private static final String FILE_NAME = "./res/Instructions.txt";

    /**
     * Constructor.
     */
    public Controller() {
        this.game = new GameImpl();
        this.view = new ViewImpl(this);
        this.turn = Turn.PLAYER1.toString();
    }

    @Override
    public void rollDice() {
        final int value = this.game.getNumberFromDice();
        this.view.setDiceValue(value);
        changeTurn();
    }

    @Override
        public void getInstructions() {
        this.view.setInstructions(FileManager.get().read(new File(FILE_NAME)));
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

    @Override
    public void giveUp() {
        this.game.giveUpGame();
    }
    /**
     * Start the view.
     */
    public void start() {
        this.view.start();
    }
    /**
     * Switch the turn of game.
     */
    private void changeTurn() {
        if (this.turn.equals(Turn.COMPUTER.toString())) {
            this.turn = Turn.PLAYER1.toString();
        } else {
            this.turn = Turn.COMPUTER.toString();
        }
        this.view.showTurn(this.turn);
    }
}
