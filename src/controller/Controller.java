package controller;

import java.util.Optional;

import model.Model;
import model.ModelImpl;
import utilities.SceneryDataManager;
import utilities.TypesOfDice;
import utilities.LanguageLoader;
import utilities.Difficulty;
import utilities.Language;
import view.View;
import view.ViewImpl;

/**
 * Class Controller.
 * This class using Singleton pattern.
 *
 */
public final class Controller implements ViewObserver {

    private final Model game;
    private final View view;
    private int counter;
    private Optional<GameSettings> settings;
    private static final Controller SINGLETON = new Controller();
    private static final String DATA1 = "./res/GameBoards/GameBoard1/file.txt";
    private static final String DATA2 = "./res/GameBoards/GameBoard2/file.txt";
    private static final String DATA3 = "./res/GameBoards/GameBoard3/file.txt";

    /**
     * Constructor.
     */
    private Controller() {
        this.game = new ModelImpl();
        this.view = new ViewImpl(this);
        this.counter = 0;
        this.settings = Optional.empty();
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
        final int value = this.game.getNumberFromDice();
        final Optional<Integer> positionAndJump;
        positionAndJump = this.game.getPlayerPosition(counter);
        if (positionAndJump.isPresent()) {
            this.view.updateInfo(value, positionAndJump.get());
        } else {
            this.view.updateInfo(value);
        }
        if (this.counter < this.settings.get().getNumberOfPlayer() - 1) {
            this.counter++;
        }  else {
            this.counter = 0;
        }
    }

    @Override
    public void quit() {
        this.game.giveUpGame();
    }

    @Override
    public void restart() {
        this.game.restartGame();
        this.counter = 0;
        this.view.firstTurn();
    }

    @Override
    public void play(final int numberOfPlayers, final Difficulty scenery, final TypesOfDice dice) {
        this.settings = Optional.of(new GameSettingsBuilder()
                .numOfPlayers(numberOfPlayers)
                .sceneryChoose(scenery)
                .diceChoose(dice)
                .build());
        switch(scenery) {
            case BEGINNER:
                this.game.startGame(SceneryDataManager.get().readFromFile(DATA1), this.settings.get().getNumberOfPlayer(), dice);
                break;
            case EASY:
                this.game.startGame(SceneryDataManager.get().readFromFile(DATA2), this.settings.get().getNumberOfPlayer(), dice);
                break;
            case MEDIUM:
                this.game.startGame(SceneryDataManager.get().readFromFile(DATA3), this.settings.get().getNumberOfPlayer(), dice);
                break;
                default:
                    this.game.startGame(SceneryDataManager.get().readFromFile(DATA1), this.settings.get().getNumberOfPlayer(), dice);
                    break;
            }
        this.counter = 0;
        this.view.firstTurn();
    }

    @Override
    public void giveUp() {
        this.game.giveUpGame();
        this.view.firstTurn();
        this.counter = 0;
    }
    /**
     * Start the view.
     */
    public void start() {
        this.view.start();
    }

    @Override
    public void setLanguage(final Language language) {
        view.setLanguageMap(LanguageLoader.get().getLanguage(language));
    }
    /**
     * Getter for counter field.
     * @return the counter field
     */
    public int getCounter() {
        return this.counter;
    }
}
