package controller;

import java.io.IOException;
import java.util.Optional;
import model.Model;
import model.ModelImpl;
import utilities.SceneryDataManager;
import utilities.UserLogin;
import utilities.enumeration.Difficulty;
import utilities.enumeration.GameMode;
import utilities.enumeration.Language;
import utilities.enumeration.Turn;
import utilities.enumeration.TypesOfDice;
import utilities.LanguageLoader;
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
            if (this.settings.get().getModality() == GameMode.SINGLE_PLAYER) {
//                System.out.println("Restart before: " + this.coinsGenerator.nameThread() + " is alive? " + this.coinsGenerator.isAlive());
//                this.coinsGenerator = new CoinsGenerator(this);
//                this.coinsGenerator.start();
                this.coinsGenerator.resume();
//                System.out.println("Restart after: " + this.coinsGenerator.nameThread() + " is alive? " + this.coinsGenerator.isAlive());
            }
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void pause() {
        if (this.settings.get().getModality() == GameMode.SINGLE_PLAYER) {
            synchronized (coinsGenerator) {
//                System.out.println("Pause before call setStop: " + this.coinsGenerator.nameThread() + " is alive? " + this.coinsGenerator.isAlive());
                this.coinsGenerator.suspende();
//                System.out.println("Pause after call setStop: " + this.coinsGenerator.nameThread() + " is alive? " + this.coinsGenerator.isAlive());

            }
        }
    }

    @Override
    public void resume() {
        if (this.settings.get().getModality() == GameMode.SINGLE_PLAYER) {
//            System.out.println("Resume before: " + this.coinsGenerator.nameThread() + " is alive? " + this.coinsGenerator.isAlive());
//            this.coinsGenerator = new CoinsGenerator(this);
//            this.coinsGenerator.start();
            this.coinsGenerator.resume();
//            System.out.println("Resume after: " + this.coinsGenerator.nameThread() + " is alive? " + this.coinsGenerator.isAlive());
        }
    }


    @Override
    public void play(final int numberOfPlayers, final Difficulty scenery, final TypesOfDice dice, final GameMode modality) {
        if (this.control) {
            this.settings = Optional.of(new GameSettingsBuilder()
                    .numOfPlayers(numberOfPlayers)
                    .sceneryChoose(scenery)
                    .diceChoose(dice)
                    .modalityChoose(modality)
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
            System.out.println("How many player? " + this.settings.get().getModality());
            if (this.settings.get().getModality() == GameMode.SINGLE_PLAYER) {
                this.coinsGenerator = new CoinsGenerator(this);
                this.coinsGenerator.start();
                System.out.println("Play: " + this.coinsGenerator.nameThread() + " is alive? " + this.coinsGenerator.isAlive());
            }
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void giveUp() {
        if (this.control) {
            this.view.firstTurn();
                if (this.settings.get().getModality() == GameMode.SINGLE_PLAYER) {
                synchronized (coinsGenerator) {
                    System.out.println("Give up before calling stop: " + this.coinsGenerator.nameThread() + " is alive? " + this.coinsGenerator.isAlive());
                    this.coinsGenerator.resume();
                    this.coinsGenerator.stopGenerate();
                    System.out.println("Give up after calling stop: " + this.coinsGenerator.nameThread() + " is alive? " + this.coinsGenerator.isAlive());
                }
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
    public void login(final String name) throws IllegalArgumentException, IOException {
        this.userLogin.login(name);
    }

    @Override
    public void collisionHappened(final int position) {
        if (this.settings.get().getModality() == GameMode.SINGLE_PLAYER) {
            if (this.counter == 1) {
                this.game.itemCollected(position, Turn.PLAYER);
            } else {
                this.game.itemCollected(position, Turn.CPU);
            }
        }
    }

    @Override
    public void statistics() {
        this.view.setStatistics(this.game.getStatistics());
    }

    @Override
    public void gameFinished(final Turn turn) throws IOException {
        this.game.gameFinished(turn);
    }

}
