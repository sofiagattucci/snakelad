package model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import utilities.Pair;

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
    private static final int FIRST_PLAYER_INDEX = 0;
    private static final int SECOND_PLAYER_INDEX = 1;

    // the number of cells in the game board
    private int numberOfCells;
    // the list which contains positions of all snakes in the current game board
    private final Map<Integer, Integer> snakesMap;
    // the list which contains positions of all ladders in the current game board
    private final Map<Integer, Integer> laddersMap;
    private final List<Player> playersList;
    private final Dice dice;

    /**
     * GameImpl constructor.
     */
    public GameImpl() {

        this.snakesMap = new HashMap<>();
        this.laddersMap = new HashMap<>();
        this.playersList = new ArrayList<>();

        final Player player1 = new Player();
        final Player player2 = new Player();
        this.playersList.add(player1);
        this.playersList.add(player2);

        this.dice = new ClassicDice();

    }

    @Override
    public void startGame(final List<Integer> listData) {

        final List<Integer> listDataFromFile = listData;

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

        for (final Player player : this.playersList) {
            player.setNewPlayerPosition(PLAYER_INITIAL_POSITION);
        }
    }

    @Override
    public int getNumberFromDice() {

        return this.dice.roll();
    }

    @Override
    public Pair<Integer, Boolean> getPositionFirstPlayer() {

        int partialPositionFirstPlayer = this.playersList.get(FIRST_PLAYER_INDEX).getPlayerPosition() 
                                         + this.dice.getLastNumberAppeared();

        partialPositionFirstPlayer = partialPositionFirstPlayer > this.numberOfCells
                                     ? this.numberOfCells - (partialPositionFirstPlayer - this.numberOfCells)
                                     : partialPositionFirstPlayer;

        if (this.laddersMap.containsKey(partialPositionFirstPlayer)) {
            partialPositionFirstPlayer = this.laddersMap.get(partialPositionFirstPlayer);
            this.playersList.get(FIRST_PLAYER_INDEX).setNewPlayerPosition(partialPositionFirstPlayer);
            return new Pair<Integer, Boolean>(this.playersList.get(FIRST_PLAYER_INDEX).getPlayerPosition(), true);
        }

        if (this.snakesMap.containsKey(partialPositionFirstPlayer)) {
            partialPositionFirstPlayer = this.snakesMap.get(partialPositionFirstPlayer);
            this.playersList.get(FIRST_PLAYER_INDEX).setNewPlayerPosition(partialPositionFirstPlayer);
            return new Pair<Integer, Boolean>(this.playersList.get(FIRST_PLAYER_INDEX).getPlayerPosition(), true);
        }

        this.playersList.get(FIRST_PLAYER_INDEX).setNewPlayerPosition(partialPositionFirstPlayer);
        return new Pair<Integer, Boolean>(this.playersList.get(FIRST_PLAYER_INDEX).getPlayerPosition(), false);
    }

    @Override
    public Pair<Integer, Boolean> getPositionSecondPlayer() {

        int partialPositionSecondPlayer = this.playersList.get(SECOND_PLAYER_INDEX).getPlayerPosition() 
                                          + this.dice.getLastNumberAppeared();

        partialPositionSecondPlayer = partialPositionSecondPlayer > this.numberOfCells
                                      ? this.numberOfCells - (partialPositionSecondPlayer - this.numberOfCells)
                                      : partialPositionSecondPlayer;

        if (this.laddersMap.containsKey(partialPositionSecondPlayer)) {
            partialPositionSecondPlayer = this.laddersMap.get(partialPositionSecondPlayer);
            this.playersList.get(SECOND_PLAYER_INDEX).setNewPlayerPosition(partialPositionSecondPlayer);
            return new Pair<Integer, Boolean>(this.playersList.get(SECOND_PLAYER_INDEX).getPlayerPosition(), true);
        }

        if (this.snakesMap.containsKey(partialPositionSecondPlayer)) {
            partialPositionSecondPlayer = this.snakesMap.get(partialPositionSecondPlayer);
            this.playersList.get(SECOND_PLAYER_INDEX).setNewPlayerPosition(partialPositionSecondPlayer);
            return new Pair<Integer, Boolean>(this.playersList.get(SECOND_PLAYER_INDEX).getPlayerPosition(), true);
        }

        this.playersList.get(SECOND_PLAYER_INDEX).setNewPlayerPosition(partialPositionSecondPlayer);
        return new Pair<Integer, Boolean>(this.playersList.get(SECOND_PLAYER_INDEX).getPlayerPosition(), false);
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
