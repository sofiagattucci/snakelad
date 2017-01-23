package model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import utilities.DataManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This is the main class of the model in MVC pattern.
 * It represents the game overall, with all his entities and main features.
 */
public class GameImpl implements Game {

    private static final int PLAYER_INITIAL_POSITION = 0;
    private static final int FIRST_INDEX = 0;
    private static final int SEPARATOR = 0;

    // the number of cells in the game board
    private final int numberOfCells;
    // the list which contains positions of all snakes in the current game board
    private final Map<Integer, Integer> snakesMap = new HashMap<>();
    // the list which contains positions of all ladders in the current game board
    private final Map<Integer, Integer> laddersMap = new HashMap<>();
    private final List<Player> playersList = new ArrayList<>();
    private final Dice dice;

    /**
     * GameImpl constructor.
     */
    public GameImpl() {

        final List<Integer> listDataFromFile = DataManager.get().read(new File("./res/GameBoards/GameBoard1/file.txt"));
        listDataFromFile.remove(FIRST_INDEX);
        this.numberOfCells = listDataFromFile.get(FIRST_INDEX);
        listDataFromFile.remove(FIRST_INDEX);
        listDataFromFile.remove(FIRST_INDEX);

        List<Integer> list = listDataFromFile.stream()
                                             .limit(listDataFromFile.indexOf(SEPARATOR))
                                             .collect(Collectors.toList());
        Iterator<Integer> iter = list.iterator();
        while (iter.hasNext()) {
            final Integer key = iter.next();
            final Integer value = iter.next();
            snakesMap.put(key, value);
        }

        list = listDataFromFile.stream()
                               .skip(listDataFromFile.indexOf(SEPARATOR) + 1)
                               .limit(listDataFromFile.indexOf(SEPARATOR))
                               .collect(Collectors.toList());
        iter = list.iterator();
        while (iter.hasNext()) {
           final Integer key = iter.next();
           final Integer value = iter.next();
           laddersMap.put(key, value);
       }

        final Player player1 = new Player();
        final Player player2 = new Player();
        this.playersList.add(player1);
        this.playersList.add(player2);

        this.dice = new ClassicDice();

    }

    @Override
    public void startGame() {

        for (final Player player : this.playersList) {
            player.setNewPlayerPosition(PLAYER_INITIAL_POSITION);
        }
    }

    @Override
    public int getNumberFromDice() {

        return this.dice.roll();
    }

    @Override
    public int getPositionFirstPlayer() {

        this.playersList.get(0).setNewPlayerPosition(this.playersList.get(0).getPlayerPosition()
                                                     + this.dice.getLastNumberAppeared());
        return this.playersList.get(0).getPlayerPosition();
    }

    @Override
    public int getPositionSecondPlayer() {

        this.playersList.get(1).setNewPlayerPosition(this.playersList.get(1).getPlayerPosition()
                                                     + this.dice.getLastNumberAppeared());
        return this.playersList.get(1).getPlayerPosition();
    }

    @Override
    public void restartGame() {

        for (final Player player : this.playersList) {
            player.setNewPlayerPosition(PLAYER_INITIAL_POSITION);
        }
    }

    @Override
    public void giveUpGame() {

    }

}
