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
    private static final int FIRST_INDEX = 0;
    private static final int SEPARATOR = 0;

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
