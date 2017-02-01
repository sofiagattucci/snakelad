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
 * This class using Singleton pattern.
 *
 */
public final class Controller implements ViewObserver {

    private final Game game;
    private final View view;
    private String turn;
    private static final Controller SINGLETON = new Controller();
    private static final String INSTRUCTIONS = "./res/Instructions.txt";
    private static final String DATA = "./res/GameBoards/GameBoard1/file.txt";
    private static final int PLAYER_INDEX = 0;
    private static final int CPU_INDEX = 1;

    /**
     * Constructor.
     */
    private Controller() {
        this.game = new GameImpl();
        this.view = new ViewImpl(this);
        this.turn = Turn.PLAYER.toString();
    }

    /**
     * Method that return an instance of Controller.
     * @return instance of Controller.
     */
    public static Controller getController() {
        return SINGLETON;
    }

    @Override
    public void rollDice() {
        changeTurn();
        final int value = this.game.getNumberFromDice();

        if (this.turn.equals(Turn.PLAYER.toString())) {
            final Pair<Integer, Boolean> pair = this.game.getPlayerPosition(PLAYER_INDEX);
            if (pair.getSecond()) {
                this.view.updateInfo(this.turn, value, pair.getFirst());
            } else {
                this.view.updateInfo(this.turn, value);
            }
        } else {
            final Pair<Integer, Boolean> pair = this.game.getPlayerPosition(CPU_INDEX);
            if (pair.getSecond()) {
                this.view.updateInfo(this.turn, value, pair.getFirst());
            } else {
                this.view.updateInfo(this.turn, value);
            }
        }
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
        this.turn = Turn.PLAYER.toString();
        this.view.firstTurn();
        this.view.showTurn(this.turn);
    }

    @Override
    public void play() {
        this.game.startGame(SceneryDataManager.get().readFromFile(DATA));
        this.turn = Turn.PLAYER.toString();
        this.view.firstTurn();
        this.view.showTurn(this.turn);
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
