package controller;

import java.util.Optional;

import model.Model;
import model.ModelImpl;
import utilities.SceneryDataManager;
import utilities.TypesOfDice;
import utilities.UserLogin;
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
    private static final String DATA1 = "./res/gameBoards/gameBoard1/file.txt";
    private static final String DATA2 = "./res/gameBoards/gameBoard2/file.txt";
    private static final String DATA3 = "./res/gameBoards/gameBoard3/file.txt";
    private static final String DATA4 = "./res/gameBoards/gameBoard4/file.txt";
    private final Model game;
    private final View view;
    private final Song playSong;
    private final UserLogin userLogin;
    private int counter;
    private boolean control;
    private Optional<GameSettings> settings;
    private CoinsGenerator coinsGenerator;

    /**
     * Constructor.
     */
    private Controller() {
        this.playSong = new SongImpl();
        this.game = new ModelImpl();
        this.view = new ViewImpl(this);
        this.counter = 0;
        this.settings = Optional.empty();
        this.userLogin = UserLogin.get();
    }

    /**
     * Method that return an instance of Controller.
     * @return instance of Controller.
     */
    public static Controller getController() {
        return SINGLETON;
    }

    @Override
    public Model getGame() {
        return this.game;
    }

    @Override
    public View getView() {
        return this.view;
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
            synchronized (coinsGenerator) {
                this.coinsGenerator.start();
            }
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void pause() {
        synchronized (coinsGenerator) {
            this.coinsGenerator.setStop();
        }
    }

    @Override
    public void resume() {
        synchronized (coinsGenerator) {
            this.coinsGenerator.start();
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
            this.coinsGenerator = new CoinsGenerator(this);
            this.coinsGenerator.start();
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void giveUp() {
        if (this.control) {
            this.view.firstTurn();
            synchronized (coinsGenerator) {
                this.coinsGenerator.setStop();
            }
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

    @Override
    public void startMusic() {
        if (this.control) {
            this.playSong.start();
            this.view.setMusicVolume(this.playSong.getMinimum(), this.playSong.getMaximum(), this.playSong.getCurrent());
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void stopMusic() {
        this.playSong.setStop();
    }

    @Override
    public void setVolume(final float volume) {
       this.playSong.setVolume(volume);
    }

    @Override
    public void login(final String name) {
        this.userLogin.login(name);
    }

    @Override
    public void collisionHappened() {
        this.game.itemCollected();
    }

}
