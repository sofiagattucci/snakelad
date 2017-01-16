package controller;

import java.io.File;

import model.Game;
import model.GameImpl;
import utilities.FileManager;
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
        this.turn = Turn.PLAYER.toString();
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
        this.view.firstTurn();
        this.turn = Turn.PLAYER.toString();
        this.view.showTurn(turn);
    }

    @Override
    public void play() {
        this.game.startGame();
        this.view.showTurn(turn);
        this.view.firstTurn();
        this.turn = Turn.PLAYER.toString();
    }

    @Override
    public void giveUp() {
        this.game.giveUpGame();
        this.view.firstTurn();
        this.turn = Turn.PLAYER.toString();
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
        if (this.turn.equals(Turn.CPU.toString())) {
            this.turn = Turn.PLAYER.toString();
        } else {
            this.turn = Turn.CPU.toString();
        }
        this.view.showTurn(this.turn);
    }
}
