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
public class ModelImpl implements Model {

    private static final int PLAYER_INITIAL_POSITION = 0;
    private static final int SEPARATOR = 0;

    //the number of cells in the game board
    private int numberOfCells;
    //map which contains positions of all snakes in the game board
    private final Map<Integer, Integer> snakesMap;
    //map which contains positions of all ladders in the game board
    private final Map<Integer, Integer> laddersMap;
    private final List<Player> playersList;
    private final Dice dice;

    /**
     * GameImpl constructor.
     */
    public ModelImpl() {
        this.snakesMap = new HashMap<>();
        this.laddersMap = new HashMap<>();
        this.playersList = new ArrayList<>();

        final Player player1 = new Player();
        final Player player2 = new Player();
        this.playersList.add(player1);
        this.playersList.add(player2);

        this.dice = ClassicDice.get();
    }

    private Map<Integer, Integer> fillMap(final List<Integer> list) {
        final Iterator<Integer> iterator = list.iterator();
        final Map<Integer, Integer> map = new HashMap<>();
        while (iterator.hasNext()) {
            final Integer key = iterator.next();
            final Integer value = iterator.next();
            map.put(key, value);
        }

        return map;
    }

    private void fillSnakesMap(final List<Integer> dataList) {
        final List<Integer> list = dataList.stream()
                                           .limit(dataList.indexOf(SEPARATOR))
                                           .collect(Collectors.toList());

        this.snakesMap.putAll(this.fillMap(list));
    }

    private void fillLaddersMap(final List<Integer> dataList) {
        final List<Integer> list = dataList.stream()
                                           .skip(dataList.indexOf(SEPARATOR) + 1)
                                           .limit(dataList.indexOf(SEPARATOR))
                                           .collect(Collectors.toList());

        this.laddersMap.putAll(this.fillMap(list));
    }

    @Override
    public void startGame(final List<Integer> data) {

        final List<Integer> dataList = data;

        //get the first number from dataList. It represents the number of cells in the scenery
        this.numberOfCells = dataList.get(0);

        //remove the first two elements in dataList
        dataList.remove(0);
        dataList.remove(0);

        //fill this.snakesMap with snakes positions
        this.fillSnakesMap(dataList);

        //fill this.laddersMap with ladders positions
        this.fillLaddersMap(dataList);

        for (final Player player : this.playersList) {
            player.setNewPosition(PLAYER_INITIAL_POSITION);
        }
    }

    @Override
    public int getNumberFromDice() {
        return this.dice.roll();
    }

    @Override
    public Pair<Integer, Boolean> getPlayerPosition(final int playerIndex) {

        int partialPlayerPosition = this.playersList.get(playerIndex).getPosition() 
                                         + this.dice.getLastNumberAppeared();

        partialPlayerPosition = partialPlayerPosition > this.numberOfCells
                                ? this.numberOfCells - (partialPlayerPosition - this.numberOfCells)
                                : partialPlayerPosition;

        if (this.laddersMap.containsKey(partialPlayerPosition)) {
            partialPlayerPosition = this.laddersMap.get(partialPlayerPosition);
            this.playersList.get(playerIndex).setNewPosition(partialPlayerPosition);
            return new Pair<Integer, Boolean>(this.playersList.get(playerIndex).getPosition(), true);
        }

        if (this.snakesMap.containsKey(partialPlayerPosition)) {
            partialPlayerPosition = this.snakesMap.get(partialPlayerPosition);
            this.playersList.get(playerIndex).setNewPosition(partialPlayerPosition);
            return new Pair<Integer, Boolean>(this.playersList.get(playerIndex).getPosition(), true);
        }

        this.playersList.get(playerIndex).setNewPosition(partialPlayerPosition);
        return new Pair<Integer, Boolean>(this.playersList.get(playerIndex).getPosition(), false);
    }

    @Override
    public void restartGame() {
        for (final Player player : this.playersList) {
            player.setNewPosition(PLAYER_INITIAL_POSITION);
        }
    }

    @Override
    public void giveUpGame() {

    }

}
