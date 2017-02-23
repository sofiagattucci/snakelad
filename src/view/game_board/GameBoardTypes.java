package view.game_board;

import java.util.HashMap;
import java.util.Map;

import utilities.Difficulty;

/**
 * This class manages the different types of game boards (images) available in the game.
 */
public final class GameBoardTypes {

    private static final String BOARD_PATH1 = "./res/GameBoards/GameBoard1/GameBoard1.png";
    private static final String BOARD_PATH2 = "./res/GameBoards/GameBoard2/GameBoard2.png";
    private static final String BOARD_PATH3 = "./res/GameBoards/GameBoard3/GameBoard3.png";

    private static final GameBoardTypes BOARD_TYPES = new GameBoardTypes();
    private final Map<Difficulty, String> boardMap = new HashMap<>();

    private GameBoardTypes() {
        this.boardMap.put(Difficulty.BEGINNER, BOARD_PATH1);
        this.boardMap.put(Difficulty.EASY, BOARD_PATH2);
        this.boardMap.put(Difficulty.MEDIUM, BOARD_PATH3);
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
     * It select the right pawn image to use.
     * @param diff
     *     The difficulty of the board
     * @return
     *     The path to the selected board 
     */
    public String getBoard(final Difficulty diff) {
        return this.boardMap.get(diff);
    }
}
