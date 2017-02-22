package model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import utilities.Pair;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * This is the main class of the model in MVC pattern.
 * It represents the game overall, with all his entities and main features.
 */
public final class ModelImpl implements Model {

    private static final int PLAYER_INITIAL_POSITION = 0;
    private static final int SEPARATOR = 0;

    //the number of cells in the game board
    private final int numberOfCells;
    //map which contains positions of all snakes in the game board
    private final Map<Integer, Integer> snakesMap = new HashMap<>();
    //map which contains positions of all ladders in the game board
    private final Map<Integer, Integer> laddersMap = new HashMap<>();
    private final List<Player> playersList = new LinkedList<>();
    private final Dice dice;

    /**
     * ModelImpl constructor which sets everything needed in order to start the game.
     * @param data
     *          The list that contains the data (snakes and ladders positions, 
     *          number of cells on the game board) useful to start the game.
     * @param numberOfPlayers
     *          number of players who want to play the game.
     * @param dice
     *          the dice which players want to play with.
     */
    public ModelImpl(final List<Integer> data, final int numberOfPlayers, final Dice dice) {

        final List<Integer> dataList = data;

        //get the first number from dataList. It represents the number of cells in the scenery
        this.numberOfCells = dataList.get(0);

        //remove the first two elements in dataList
        dataList.remove(0);
        dataList.remove(0);

        //fill snakesMap with snakes positions
        this.snakesMap.putAll(this.fillMap(this.fillSnakesMap(dataList)));

        //fill laddersMap with ladders positions
        this.laddersMap.putAll(this.fillMap(this.fillLaddersMap(dataList)));

        //fill playersList with the exact number of players playing the game and set their initial positions
        this.playersList.addAll(IntStream.range(0, numberOfPlayers)
                                         .mapToObj(value -> new Player())
                                         .peek(player -> player.setNewPosition(PLAYER_INITIAL_POSITION))
                                         .collect(Collectors.toList()));
        this.dice = dice;
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

    private List<Integer> fillSnakesMap(final List<Integer> dataList) {
        final List<Integer> list = dataList.stream()
                                           .limit(dataList.indexOf(SEPARATOR))
                                           .collect(Collectors.toList());

        return Collections.unmodifiableList(list);
    }

    private List<Integer> fillLaddersMap(final List<Integer> dataList) {
        final List<Integer> list = dataList.stream()
                                           .skip(dataList.indexOf(SEPARATOR) + 1)
                                           .limit(dataList.indexOf(SEPARATOR))
                                           .collect(Collectors.toList());

        return Collections.unmodifiableList(list);
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
