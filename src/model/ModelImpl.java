package model;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import model.dice.Dice;
import model.dice.DiceFactory;
import model.dice.DiceFactoryImpl;
import model.items.Coin;
import model.items.Diamond;
import model.items.ItemsGenerator;
import model.items.ItemsGeneratorImpl;
import model.items.Skull;
import model.items.SpecialItem;
import model.player.Player;
import model.player.PlayerImpl;
import model.scenery.Scenery;
import model.scenery.SceneryFactoryImpl;
import model.user.User;
import model.user.UserImpl;
import utilities.Statistic;
import utilities.StatisticImpl;
import utilities.UserStatisticsFileWriter;
import utilities.enumeration.Turn;
import utilities.enumeration.TypesOfDice;
import utilities.enumeration.TypesOfItem;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * This is the main class of the model in MVC pattern.
 * It represents the game overall, with all his entities and main features.
 */
public final class ModelImpl implements Model {

    private static final Supplier<RuntimeException> ILLEGAL_STATE_EXCEPTION_SUPPLIER = () -> new IllegalStateException("The method startGame() "
                                                                                                                     + "must be called before "
                                                                                                                     + "calling this method!");
    private static final int PLAYER_INITIAL_POSITION = 0; 
    private static final int MAX_ITEMS_GENERATION = 20;

    private final User user;
    private Scenery scenery;
    private final List<Player> playersList = new LinkedList<>();
    private Dice dice;
    private final Map<Integer, SpecialItem> itemsMap = new HashMap<>();
    //'isReady' is false if the method called startGame() has never been called, otherwise 
    //true. In fact, the method statGame() must be called before any other method.
    private boolean isReady;
    //contains the maximum number of permitted items generations.
    private int maxItemsGeneration;
    //contains the number of items collected by the player or CPU.
    private int itemsCollected;
    //the number of times the user roll a dice
    private int numberOfDiceRoll;
    private boolean isPlayerTurn;
    //the user's scores
    private int userScores;

    /**
     * ModelImpl constructor.
     */
    public ModelImpl() {
        this.user = UserImpl.get();
        this.isReady = false;
        this.maxItemsGeneration = MAX_ITEMS_GENERATION;
        this.itemsCollected = 0;
        this.numberOfDiceRoll = 0;
        this.isPlayerTurn = true;
        this.userScores = 0;
    }

    //private method called to avoid too much repetition of identical code in restartGame() and giveUpGame() methods.
    private synchronized void clearEntities() {
        this.playersList.stream()
                        .forEach(player -> player.setNewPosition(PLAYER_INITIAL_POSITION));

        this.dice.setLastNumberAppeared(Optional.empty());
        this.itemsMap.clear();
        this.maxItemsGeneration = MAX_ITEMS_GENERATION;
        this.itemsCollected = 0;
        this.numberOfDiceRoll = 0;
        this.isPlayerTurn = true;
        this.userScores = 0;
    }

    //private method called to avoid too much repetition of identical code in getPlayerPosition() method.
    private synchronized Optional<Integer> playerPositionUtils(final int index, final int position) {
        this.playersList.get(index).setNewPosition(position);
        return Optional.of(this.playersList.get(index).getPosition());
    }
 
    //private method called to avoid too much repetition of identical code in tryGenerateCoin(), 
    //tryGenerateDiamond() and tryGenerateSkull() methods.
    private synchronized Optional<Integer> generateItemsUtils(final TypesOfItem typeOfItem) {
        if (!this.isReady) {
            throw ILLEGAL_STATE_EXCEPTION_SUPPLIER.get();
        }

        final ItemsGenerator itemGenerator = ItemsGeneratorImpl.get();
        //this list will contain all items and players' current positions on the game's grid
        final List<Integer> occupiedPositionsList = new LinkedList<>();
        this.itemsMap.values().stream()
                              .forEach(item -> occupiedPositionsList.add(item.getPosition()));

        this.playersList.stream()
                        .forEach(player -> occupiedPositionsList.add(player.getPosition()));

        final Optional<Integer> generationResult = itemGenerator.tryGenerateItem(this.scenery.getNumberOfBoxes(), 
                                                                                 occupiedPositionsList, typeOfItem);
        if (!generationResult.isPresent()) {
            return Optional.empty();
        }

        this.maxItemsGeneration--;
        if (maxItemsGeneration < 0) {
            maxItemsGeneration = 0;
            return Optional.empty();
        }

        this.itemsMap.put(generationResult.get(), (typeOfItem == TypesOfItem.COIN) ? new Coin(generationResult.get())
                                                  : (typeOfItem == TypesOfItem.DIAMOND) ? new Diamond(generationResult.get())
                                                  : new Skull(generationResult.get()));

        return generationResult;
    }

    @Override
    public synchronized Optional<Integer> getPlayerPosition(final int playerIndex) throws IllegalStateException {
        if (!this.isReady) {
            throw ILLEGAL_STATE_EXCEPTION_SUPPLIER.get();
        }

        int partialPlayerPosition = this.playersList.get(playerIndex).getPosition() 
                                    + this.dice.getLastNumberAppeared();

        partialPlayerPosition = partialPlayerPosition < 0 ? 0 : partialPlayerPosition;

        partialPlayerPosition = partialPlayerPosition > this.scenery.getNumberOfBoxes()
                                ? this.scenery.getNumberOfBoxes() - (partialPlayerPosition - this.scenery.getNumberOfBoxes())
                                : partialPlayerPosition;

        if (this.scenery.getSnakesMap().containsKey(partialPlayerPosition)) {
            final int finalPlayerPosition = this.scenery.getSnakesMap().get(partialPlayerPosition);
            return this.playerPositionUtils(playerIndex, finalPlayerPosition);
        }

        if (this.scenery.getLaddersMap().containsKey(partialPlayerPosition)) {
            final int finalPlayerPosition = this.scenery.getLaddersMap().get(partialPlayerPosition);
            return this.playerPositionUtils(playerIndex, finalPlayerPosition);
        }

        //the specified player don't achieve neither a snake or a ladder
        this.playerPositionUtils(playerIndex, partialPlayerPosition);
        return Optional.empty();
    }

    @Override
    public synchronized void startGame(final List<Integer> data, final int numberOfPlayers, final TypesOfDice dice) throws IllegalArgumentException {
        if (numberOfPlayers <= 1) {
            throw new IllegalArgumentException("Number of players less or equal to 1!");
        }

        this.isReady = true;
        this.scenery = new SceneryFactoryImpl().setUpScenery(data);

        //fill playersList with the exact number of players playing the game and set their initial positions
        this.playersList.addAll(IntStream.range(0, numberOfPlayers)
                        .mapToObj(value -> new PlayerImpl())
                        .peek(player -> player.setNewPosition(PLAYER_INITIAL_POSITION))
                        .collect(Collectors.toList()));

        final DiceFactory diceFactory = new DiceFactoryImpl();
        switch (dice) {
        case CLASSIC_DICE:
            this.dice = diceFactory.createClassicDice();
            break;
        case _5_TO_10_DICE:
            this.dice = diceFactory.create5To10Dice(diceFactory.createClassicDice());
            break;
        case NEGATIVE_DICE:
            this.dice = diceFactory.createNegativeDice(diceFactory.createClassicDice());
            break;
        default:
            throw new IllegalArgumentException("The type of dice passed in argument is not supported!");
        }
    }

    @Override
    public String getUserName() {
        return this.user.getName();
    }

    @Override
    public int getGameBoardSideSize() throws IllegalStateException {
        if (!this.isReady) {
            throw ILLEGAL_STATE_EXCEPTION_SUPPLIER.get();
        }

        return this.scenery.getSideSize();
    }

    @Override
    public int getNumberFromDice() throws IllegalStateException {
        if (!this.isReady) {
            throw ILLEGAL_STATE_EXCEPTION_SUPPLIER.get();
        }

        if (isPlayerTurn) {
            this.numberOfDiceRoll++;
            this.isPlayerTurn = false;
        } else {
            this.isPlayerTurn = true;
        }

        return this.dice.roll();
    }

    @Override
    public synchronized void restartGame() throws IllegalStateException {
        if (!this.isReady) {
            throw ILLEGAL_STATE_EXCEPTION_SUPPLIER.get();
        }

        this.clearEntities();
    }

    @Override
    public synchronized void giveUpGame() throws IllegalStateException {
        if (!this.isReady) {
            throw ILLEGAL_STATE_EXCEPTION_SUPPLIER.get();
        }

        this.clearEntities();
        this.scenery.clearMaps();
        this.isReady = false;
    }

    @Override
    public Optional<Integer> tryGenerateCoin() throws IllegalStateException {
        return this.generateItemsUtils(TypesOfItem.COIN);
    }

    @Override
    public Optional<Integer> tryGenerateDiamond() throws IllegalStateException {
        return this.generateItemsUtils(TypesOfItem.DIAMOND);
    }

    @Override
    public Optional<Integer> tryGenerateSkull() throws IllegalStateException {
        return this.generateItemsUtils(TypesOfItem.SKULL);
    }

    @Override
    public synchronized void itemCollected(final int itemIndex, final Turn turn) throws IllegalArgumentException, NoSuchElementException {
        if (!this.isReady) {
            throw ILLEGAL_STATE_EXCEPTION_SUPPLIER.get();
        }

        if (!this.itemsMap.containsKey(itemIndex)) {
            throw new IllegalArgumentException("The user's index is not present in the Model!");
        }

        //add scores to user only if it's player's turn
        if (turn == Turn.PLAYER) {
            this.userScores += (int) this.itemsMap.get(itemIndex).runEffectGettingResult();
        }

        //delete item collected
        if (!this.itemsMap.remove(itemIndex, this.itemsMap.get(itemIndex))) {
            throw new NoSuchElementException("Cannot remove entry from itemsMap!");
        }

        this.itemsCollected++;
        if (this.itemsCollected == MAX_ITEMS_GENERATION / 2) {
            this.itemsCollected = 0;
            this.maxItemsGeneration = MAX_ITEMS_GENERATION / 2;
        }
    }

    @Override
    public Statistic getStatistic() {
        //build Statistic object
        final Statistic userStatistics = new StatisticImpl.Builder()
                                                          .gameWon(this.user.getGamesWon())
                                                          .gameLost(this.user.getGamesLost())
                                                          .numberOfDiceRoll(this.user.getNumberOfDiceRoll())
                                                          .scores(this.user.getScores())
                                                          .build();
        return userStatistics;
    }

    @Override
    public void gameFinished(final Turn turn) throws IOException {
        if (!this.isReady) {
            throw ILLEGAL_STATE_EXCEPTION_SUPPLIER.get();
        }

        final UserStatisticsFileWriter statWriter = UserStatisticsFileWriter.get();
        this.user.addScores(this.userScores);
        this.user.setNumberOfDiceRoll(this.user.getNumberOfDiceRoll() + this.numberOfDiceRoll);
        if (turn == Turn.PLAYER) {
            this.user.setGamesWon(this.user.getGamesWon() + 1);
        } else {
            this.user.setGamesLost(this.user.getGamesLost() + 1);
        }
        statWriter.writeUserStatistics(this.user.getScores(), this.user.getNumberOfDiceRoll(), 
                                       this.user.getGamesWon(), this.user.getGamesLost());
    }

}
