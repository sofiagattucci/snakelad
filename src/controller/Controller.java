package controller;

import java.io.IOException;
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
    private static final String SNAKE = "./res/soundEffects/snake.wav";
    private static final String LADDER = "./res/soundEffects/ladder.wav";
    private static final String WIN = "./res/soundEffects/win.wav";
    private static final String LOSE = "./res/soundEffects/lose.wav";
    private final Model game;
    private final View view;
    private final BackgroundMusic playSong;
    private final UserLogin userLogin;
    private final PathMap path;
    private Jump clipJump;
    private ItemsClip itemClip;
    private int turn;
    private boolean control;
    private Optional<GameSettings> settings;
    private CoinsGenerator coinsGenerator;

    /**
     * Constructor.
     */
    private Controller() {
        this.playSong = new BackgroundMusic();
        this.game = new ModelImpl();
        this.view = new ViewImpl(this);
        this.turn = 0;
        this.clipJump = Jump.NO_JUMP;
        this.settings = Optional.empty();
        this.path = new PathMapBuilder().itemClipMap(TypesOfItem.COIN, "./res/soundEffects/coin.wav")
                .itemClipMap(TypesOfItem.DIAMOND, "./res/soundEffects/diamond.wav")
                .itemClipMap(TypesOfItem.SKULL, "./res/soundEffects/skull.wav")
                .sceneryMap(Difficulty.BEGINNER, "./res/gameBoards/gameBoard1/file.txt")
                .sceneryMap(Difficulty.EASY, "./res/gameBoards/gameBoard2/file.txt")
                .sceneryMap(Difficulty.MEDIUM, "./res/gameBoards/gameBoard3/file.txt")
                .sceneryMap(Difficulty.HIGH, "./res/gameBoards/gameBoard4/file.txt")
                .songMap(AudioTrack.SNAKELAD, "./res/music/Snakelad.wav")
                .songMap(AudioTrack.CAVE_OF_DRAGONS, "./res/music/ID.wav")
                .build();
        this.userLogin = UserLogin.get();
    }

    //If the modality of game is Single player return true, otherwise return false
    private boolean checkModality() {
        return (this.settings.get().getModality() == GameMode.SINGLE_PLAYER) ? true : false;
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
            final int value = this.game.rollDice();
            final Pair<Optional<Integer>, Jump> positionAndJump = this.game.getPlayerPosition(turn);
            this.clipJump = positionAndJump.getSecond();
            if (positionAndJump.getFirst().isPresent()) {
                this.view.updateInfo(value, positionAndJump.getFirst().get());
            } else {
                this.view.updateInfo(value);
            }
            if (this.turn < this.settings.get().getNumberOfPlayer() - 1) {
                this.turn++;
            }  else {
                this.turn = 0;
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
            this.turn = 0;
            this.view.firstTurn();
            if (this.checkModality()) {
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
        if (this.checkModality()) {
            synchronized (coinsGenerator) {
                this.coinsGenerator.suspende();

            }
        }
    }

    @Override
    public void resume() {
        if (this.checkModality()) {
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
                    this.game.startGame(SceneryDataManager.get().readFromFile(this.path.getSceneryMap().get(Difficulty.BEGINNER)), this.settings.get().getNumberOfPlayer(), dice);
                    break;
                case EASY:
                    this.game.startGame(SceneryDataManager.get().readFromFile(this.path.getSceneryMap().get(Difficulty.EASY)), this.settings.get().getNumberOfPlayer(), dice);
                    break;
                case MEDIUM:
                    this.game.startGame(SceneryDataManager.get().readFromFile(this.path.getSceneryMap().get(Difficulty.MEDIUM)), this.settings.get().getNumberOfPlayer(), dice);
                    break;
                case HIGH:
                    this.game.startGame(SceneryDataManager.get().readFromFile(this.path.getSceneryMap().get(Difficulty.HIGH)), this.settings.get().getNumberOfPlayer(), dice);
                    break;
                default:
                        this.game.startGame(SceneryDataManager.get().readFromFile(this.path.getSceneryMap().get(Difficulty.BEGINNER)), this.settings.get().getNumberOfPlayer(), dice);
                        break;
                }
            this.turn = 0;
            this.view.firstTurn();
            this.view.setBoardSize(this.game.getGameBoardSideSize());
            if (this.checkModality()) {
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
        this.turn = 0;
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
            this.playSong.start(this.path.getSongMap().get(newSong));
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
            if (this.checkModality()) {
                if (this.turn == 1) {
                    type = this.game.itemCollected(position, Turn.PLAYER);
                } else {
                    type = this.game.itemCollected(position, Turn.CPU);
                }
                this.itemClip = new ItemsClip();
                synchronized (this) {
                    this.itemClip.start(this.path.getClipMap().get(type), this.playSong.getCurrent());
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
            if (this.checkModality()) {
                synchronized (this) {
                    if (this.turn == 1) {
                        this.itemClip = new ItemsClip();
                        this.itemClip.start(WIN, this.playSong.getCurrent());
                    } else {
                        this.itemClip = new ItemsClip();
                        this.itemClip.start(LOSE, this.playSong.getCurrent());
                    }
                }
                this.game.matchFinished(turn);
            }
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
        if (this.control) {
            final float currentVolume = this.playSong.getCurrent();
            this.playSong.stop();
            this.playSong.start(this.path.getSongMap().get(newSong));
            this.playSong.setVolume(currentVolume);
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public synchronized void startClipJump() {
        if (this.control) {
            if (this.checkModality()) {
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
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * Start the view.
     */
    public void start() {
        this.control = true;
        this.view.start();
    }
}
