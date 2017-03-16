package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import model.Model;
import model.ModelImpl;
import utilities.SceneryDataManager;
import utilities.UserLogin;
import utilities.enumeration.AudioTrack;
import utilities.enumeration.Difficulty;
import utilities.enumeration.GameMode;
import utilities.enumeration.Jump;
import utilities.enumeration.Language;
import utilities.enumeration.Turn;
import utilities.enumeration.TypesOfDice;
import utilities.enumeration.TypesOfItem;
import utilities.LanguageLoader;
import utilities.Pair;
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
    private static final String SNAKE = "./res/soundEffects/snake.wav";
    private static final String LADDER = "./res/soundEffects/ladder.wav";
    private static final String WIN = "./res/soundEffects/win.wav";
    private static final String LOSE = "./res/soundEffects/lose.wav";
    private final Map<TypesOfItem, String> clipPath;
    private final Model game;
    private final View view;
    private final Song playSong;
    private final UserLogin userLogin;
    private Jump clipJump;
    private ItemsClip itemClip;
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
        this.clipPath = new HashMap<>();
        this.clipPath.put(TypesOfItem.COIN, "./res/soundEffects/coin.wav");
        this.clipPath.put(TypesOfItem.DIAMOND, "./res/soundEffects/diamond.wav");
        this.clipPath.put(TypesOfItem.SKULL, "./res/soundEffects/skull.wav");
        this.clipJump = Jump.NO_JUMP;
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
    public void rollDice() {
        if (this.control) {
<<<<<<< HEAD
            final int value = this.game.rollDice();
            final Optional<Integer> positionAndJump;
            positionAndJump = this.game.getPlayerPosition(counter);
            if (positionAndJump.isPresent()) {
                this.view.updateInfo(value, positionAndJump.get());
=======
            final int value = this.game.getNumberFromDice();
            final Pair<Optional<Integer>, Jump> positionAndJump = this.game.getPlayerPosition(counter);
            this.clipJump = positionAndJump.getSecond();
            if (positionAndJump.getFirst().isPresent()) {
                this.view.updateInfo(value, positionAndJump.getFirst().get());
>>>>>>> b644499802ec068fbd9dd5f52bbf7ccbbbb66ca8
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
                synchronized (coinsGenerator) {
                    this.coinsGenerator.resume();
                }
            }
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void pause() {
        if (this.settings.get().getModality() == GameMode.SINGLE_PLAYER) {
            synchronized (coinsGenerator) {
                this.coinsGenerator.suspende();

            }
        }
    }

    @Override
    public void resume() {
        if (this.settings.get().getModality() == GameMode.SINGLE_PLAYER) {
            synchronized (coinsGenerator) {
                this.coinsGenerator.resume();
            }
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
            if (this.settings.get().getModality() == GameMode.SINGLE_PLAYER) {
                this.coinsGenerator = new CoinsGenerator(this.view, this.game);
                this.coinsGenerator.start();
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
                    this.coinsGenerator.resume();
                    this.coinsGenerator.stopGenerate();
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
    public void startMusic(final AudioTrack newSong) {
        if (this.control) {
            this.playSong.start(newSong);
            this.playSong.setVolume(this.playSong.getDefault());
            this.view.setMusicVolume(this.playSong.getMinimum(), this.playSong.getMaximum(), this.playSong.getCurrent());
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void stopMusic() {
        if (this.control) {
            this.playSong.setVolume(this.playSong.getMute());
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void setVolume(final float volume) {
        if (this.control) {
            this.playSong.setVolume(volume);
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void login(final String name) throws IllegalArgumentException, IOException {
        if (this.control) {
            this.userLogin.login(name);
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void collisionHappened(final int position) {
        TypesOfItem type;
        if (this.control) {
            if (this.settings.get().getModality() == GameMode.SINGLE_PLAYER) {
                if (this.counter == 1) {
                    type = this.game.itemCollected(position, Turn.PLAYER);
                } else {
                    type = this.game.itemCollected(position, Turn.CPU);
                }
                this.itemClip = new ItemsClip();
                synchronized (this) {
                    this.itemClip.start(this.clipPath.get(type), this.playSong.getCurrent());
                }
            }
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void statistics() {
        if (this.control) {
            this.view.setStatistics(this.game.getStatistics());
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void gameFinished(final Turn turn) throws IOException {
        if (this.control) {
<<<<<<< HEAD
            this.game.matchFinished(turn);
=======
            if (this.settings.get().getModality() == GameMode.SINGLE_PLAYER) {
                synchronized (this) {
                    if (this.counter == 1) {
                        this.itemClip = new ItemsClip();
                        this.itemClip.start(WIN, this.playSong.getCurrent());
                    } else {
                        this.itemClip = new ItemsClip();
                        this.itemClip.start(LOSE, this.playSong.getCurrent());
                    }
                }
                this.game.gameFinished(turn);
            }
>>>>>>> b644499802ec068fbd9dd5f52bbf7ccbbbb66ca8
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void clearStatistics() throws IOException {
        if (this.control) {
            this.game.clearStatistics();
            this.view.setStatistics(this.game.getStatistics());
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void changeMusic(final AudioTrack newSong) {
        final float currentVolume = this.playSong.getCurrent();
        this.playSong.stop();
        this.playSong.start(newSong);
        this.playSong.setVolume(currentVolume);
    }

    @Override
    public synchronized void startClipJump() {
        if (this.settings.get().getModality() == GameMode.SINGLE_PLAYER) {
            switch (this.clipJump) {
                case SNAKE:
                    this.itemClip = new ItemsClip();
                    this.itemClip.start(SNAKE, this.playSong.getCurrent());
                    break;
                case LADDER:
                    this.itemClip = new ItemsClip();
                    this.itemClip.start(LADDER, this.playSong.getCurrent());
                    break;
                    default:
                        break;
            }
        }
    }
}
