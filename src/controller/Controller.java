package controller;

import java.util.Optional;

import model.Model;
import model.ModelImpl;
import utilities.SceneryDataManager;
import utilities.TypesOfDice;
import utilities.LanguagesManager;
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
    private static final String DATA = "./res/GameBoards/GameBoard1/file.txt";

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
        final Optional<Integer> position;
        position = this.game.getPlayerPosition(counter);
        if (position.isPresent()) {
            this.view.updateInfo(value, position.get());
        } else {
            this.view.updateInfo(value);
        }
        if (this.counter % this.settings.get().getNumberOfPlayer() != 0) {
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
    public void play(final int numberOfPlayers, final int scenery, final int dice) {
        this.settings = Optional.of(new GameSettingsBuilder()
                .numOfPlayers(numberOfPlayers)
                .sceneryChoose(scenery)
                .diceChoose(dice)
                .build());
        this.game.startGame(SceneryDataManager.get().readFromFile(DATA), this.settings.get().getNumberOfPlayer(), TypesOfDice.CLASSIC_DICE);
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
        view.setLanguageMap(LanguagesManager.get().getLanguage(language));
    }
}
