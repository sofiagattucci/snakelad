package view.gameboard;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import utilities.Difficulty;
import utilities.Pair;

/**
 * This class manages the different types of game boards (images) available in the game.
 */
public final class GameBoardTypes {

    private static final String STANDARD_BOARD_PATH = "./res/gameBoards/";
    private static final String GAME_BOARD = "gameBoard";
    private static final String ESCAPE = "/";
    private static final String MINI = "_mini";
    private static final String PNG = ".png";
    private static final int NUM_SCENERY = Difficulty.values().length;

    private static final GameBoardTypes BOARD_TYPES = new GameBoardTypes();
    private final Map<Difficulty, Pair<String, String>> boardMap;

    private GameBoardTypes() {
 
        this.boardMap = IntStream.range(1, NUM_SCENERY + 1)
                                 .boxed()
                                 .collect(Collectors.toMap(i -> this.calculateDifficulty(i), i -> new Pair<String, String>(
                                         STANDARD_BOARD_PATH + GAME_BOARD + i + ESCAPE + GAME_BOARD + i + PNG,
                                         STANDARD_BOARD_PATH + GAME_BOARD + i + ESCAPE + GAME_BOARD + i + MINI + PNG)));
    }

    private Difficulty calculateDifficulty(final int n) {
        switch(n) {
            case 1: return Difficulty.BEGINNER;
            case 2: return Difficulty.EASY;
            case 3: return Difficulty.MEDIUM;
            case 4: return Difficulty.HIGH;
            default: return Difficulty.BEGINNER;
        }
    }

    /**
     * Getter of this class unique instance.
     * @return
     *     The instance of this class
     */
    public static GameBoardTypes get() {
        return BOARD_TYPES;
    }

    /**
     * It select the right board image to use.
     * @param diff
     *     The difficulty of the board
     * @return
     *     The path to the selected board 
     */
    public String getBoard(final Difficulty diff) {
        return this.boardMap.get(diff).getFirst();
    }

    /**
     * It select the right board thumb nail image to use.
     * @param diff
     *     The difficulty of the board
     * @return
     *     The path to the selected board thumb nail
     */
    public String getBoardMini(final Difficulty diff) {
        return this.boardMap.get(diff).getSecond();
    }
}
