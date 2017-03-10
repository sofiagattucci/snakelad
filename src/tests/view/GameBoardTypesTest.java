package tests.view;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import utilities.enumeration.Difficulty;
import view.gameboard.GameBoardTypes;

/**
 * JUnit Test for the class GameBoardTypes.
 */
public class GameBoardTypesTest {

    private static final String STANDARD_BOARD_PATH = "./res/gameBoards/";
    private static final String GAME_BOARD = "gameBoard";
    private static final String ESCAPE = "/";
    private static final String MINI = "_mini";
    private static final String PNG = ".png";

    /**
     * Starting JUnit tests.
     */
    @Test
    public void test() {

        assertEquals(GameBoardTypes.get().getClass(), GameBoardTypes.class);
        //Checking sceneries
        assertEquals(GameBoardTypes.get().getBoard(Difficulty.BEGINNER),
                STANDARD_BOARD_PATH + GAME_BOARD + 1 + ESCAPE + GAME_BOARD + 1 + PNG);
        assertEquals(GameBoardTypes.get().getBoard(Difficulty.EASY),
                STANDARD_BOARD_PATH + GAME_BOARD + 2 + ESCAPE + GAME_BOARD + 2 + PNG);
        assertEquals(GameBoardTypes.get().getBoard(Difficulty.MEDIUM),
                STANDARD_BOARD_PATH + GAME_BOARD + 3 + ESCAPE + GAME_BOARD + 3 + PNG);
        assertEquals(GameBoardTypes.get().getBoard(Difficulty.HIGH),
                STANDARD_BOARD_PATH + GAME_BOARD + 4 + ESCAPE + GAME_BOARD + 4 + PNG);
        //Checking thumb nails
        assertEquals(GameBoardTypes.get().getBoardMini(Difficulty.BEGINNER),
                STANDARD_BOARD_PATH + GAME_BOARD + 1 + ESCAPE + GAME_BOARD + 1 + MINI + PNG);
        assertEquals(GameBoardTypes.get().getBoardMini(Difficulty.EASY),
                STANDARD_BOARD_PATH + GAME_BOARD + 2 + ESCAPE + GAME_BOARD + 2 + MINI + PNG);
        assertEquals(GameBoardTypes.get().getBoardMini(Difficulty.MEDIUM),
                STANDARD_BOARD_PATH + GAME_BOARD + 3 + ESCAPE + GAME_BOARD + 3 + MINI + PNG);
        assertEquals(GameBoardTypes.get().getBoardMini(Difficulty.HIGH),
                STANDARD_BOARD_PATH + GAME_BOARD + 4 + ESCAPE + GAME_BOARD + 4 + MINI + PNG);
    }
}
