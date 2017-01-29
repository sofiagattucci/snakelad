package controller;

import model.Game;
import model.GameImpl;
import utilities.SceneryDataManager;
import utilities.InstructionsManager;
import utilities.Pair;
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
    private static final String INSTRUCTIONS = "./res/Instructions.txt";
    private static final String DATA = "./res/GameBoards/GameBoard1/file.txt";

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

        if (this.turn.equals(Turn.PLAYER.toString())) {
            final Pair<Integer, Boolean> pair = this.game.getPositionFirstPlayer();
            if (pair.getSecond()) {
                this.view.updateInfo(turn, value, pair.getFirst());
            } else {
                this.view.updateInfo(turn, value);
            }
        } else {
            final Pair<Integer, Boolean> pair = this.game.getPositionSecondPlayer();
            if (pair.getSecond()) {
                this.view.updateInfo(turn, value, pair.getFirst());
            } else {
                this.view.updateInfo(turn, value);
            }
        }
        changeTurn();
    }

    @Override
    public void getInstructions() {
        this.view.setInstructions(InstructionsManager.get().readFromFile(INSTRUCTIONS));
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
        this.game.startGame(SceneryDataManager.get().readFromFile(DATA));
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
