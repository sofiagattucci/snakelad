package view;

import java.util.HashMap;
import java.util.Map;

/**
 * This class manages the different types of game boards (images) available in the game.
 */
public final class GameBoardTypes {

    private static final String BOARD_PATH1 = "./res/GameBoards/GameBoard1/GameBoard1.png";
    private static final String BOARD_PATH2 = "./res/GameBoards/GameBoard2/GameBoard2.png";
    private static final String BOARD_PATH3 = "./res/GameBoards/GameBoard3/GameBoard3.png";

    private static final GameBoardTypes BOARD_TYPES = new GameBoardTypes();
    private final Map<Integer, String> boardMap = new HashMap<>();

    private GameBoardTypes() {
        this.boardMap.put(1, BOARD_PATH1);
        this.boardMap.put(2, BOARD_PATH2);
        this.boardMap.put(3, BOARD_PATH3);
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
     * @param index
     *     The index of the board
     * @return
     *     The path to the selected board 
     */
    public String getBoard(final int index) {
        return this.boardMap.get(index);
    }
}
