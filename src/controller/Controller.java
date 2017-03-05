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

    private static final Controller SINGLETON = new Controller();
    private static final String DATA1 = "./res/GameBoards/GameBoard1/file.txt";
    private static final String DATA2 = "./res/GameBoards/GameBoard2/file.txt";
    private static final String DATA3 = "./res/GameBoards/GameBoard3/file.txt";
    private static final String DATA4 = "./res/GameBoards/GameBoard4/file.txt";
    private final Model game;
    private final View view;
    private final Song playSong;
    private int counter;
    private boolean control;
    private Optional<GameSettings> settings;

    /**
     * Constructor.
     */
    private Controller() {
        this.playSong = new SongImpl();
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
        if (this.control) {
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
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void quit() {
        if (this.control) {
            this.stopMusic();
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void restart() {
        if (this.control) {
            this.game.restartGame();
            this.counter = 0;
            this.view.firstTurn();
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void play(final int numberOfPlayers, final Difficulty scenery, final TypesOfDice dice) {
        if (this.control) {
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
                case HIGH:
                    this.game.startGame(SceneryDataManager.get().readFromFile(DATA4), this.settings.get().getNumberOfPlayer(), dice);
                    break;
                default:
                        this.game.startGame(SceneryDataManager.get().readFromFile(DATA1), this.settings.get().getNumberOfPlayer(), dice);
                        break;
                }
            this.counter = 0;
            this.view.firstTurn();
            this.view.setBoardSize(this.game.getGameBoardSideSize());
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void giveUp() {
        if (this.control) {
            this.view.firstTurn();
            this.game.giveUpGame();
        } else {
            throw new IllegalStateException();
        }
        this.counter = 0;
    }
    /**
     * Start the view.
     */
    public void start() {
        this.control = true;
        this.view.start();
    }

    @Override
    public void setLanguage(final Language language) {
        if (this.control) {
        view.setLanguageMap(LanguageLoader.get().getLanguage(language));
        } else {
            throw new IllegalStateException();
        }
    }
    /**
     * Getter for counter field.
     * @return the counter field
     */
    public int getCounter() {
        return this.counter;
    }


    @Override
    public void startMusic() {
        if (this.control) {
            final Song currentSong = new SongImpl();
            currentSong.start();
            this.view.setMusicVolume(currentSong.getMinimum(), currentSong.getMaximum(), currentSong.getCurrent());
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void stopMusic() {
        this.playSong.setStop(false);
    }
}
