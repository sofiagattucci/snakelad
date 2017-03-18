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
import utilities.Pair;
import utilities.Statistic;
import utilities.StatisticImpl;
import utilities.UserStatisticsFileWriter;
import utilities.enumeration.Jump;
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
    private final CareMementoTaker mementoTaker;
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
        this.mementoTaker = CareMementoTaker.get();
        this.isReady = false;
        this.maxItemsGeneration = MAX_ITEMS_GENERATION;
        this.itemsCollected = 0;
        this.numberOfDiceRoll = 0;
        this.isPlayerTurn = true;
        this.userScores = 0;
    }

    //private method called to avoid too much repetition of identical code
    private void checkModelImplReady() {
        if (!this.isReady) {
            throw ILLEGAL_STATE_EXCEPTION_SUPPLIER.get();
        }
    }

    //private method called to avoid too much repetition of identical code in restartGame() and giveUpGame() methods
    private void clearFieldsListAndMap() {
        this.playersList.stream()
                        .forEach(player -> player.setPosition(PLAYER_INITIAL_POSITION));

        this.itemsMap.clear();
    }

    //private method called to avoid too much repetition of identical code in getPlayerPosition() method.
    private synchronized Optional<Integer> playerPositionUtils(final int index, final int position) {
        this.playersList.get(index).setPosition(position);
        return Optional.of(this.playersList.get(index).getPosition());
    }

    private void deleteItemCollected(final int itemIndex) {
        if (!this.itemsMap.remove(itemIndex, this.itemsMap.get(itemIndex))) {
            throw new NoSuchElementException("Cannot remove item from itemsMap!");
        }
    }
 
    //private method called to avoid too much repetition of identical code in tryGenerateCoin(), 
    //tryGenerateDiamond() and tryGenerateSkull() methods.
    private synchronized Optional<Integer> generateItemsUtils(final TypesOfItem typeOfItem) {
        this.checkModelImplReady();

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

    private ModelMemento createMemento() throws IllegalStateException {
        this.checkModelImplReady();

        final ModelMemento memento;
        Optional<Integer> lastNumberFromDice;
        try {
            lastNumberFromDice = Optional.of(this.dice.getLastNumberAppeared());
        } catch (final IllegalStateException exc) {
            lastNumberFromDice = Optional.empty();
        }

        memento = new ModelMemento(lastNumberFromDice, this.maxItemsGeneration, this.itemsCollected,
                                   this.numberOfDiceRoll, this.isPlayerTurn, this.userScores);

        return memento;
    }

    private void setStateFromMemento(final ModelMemento memento) throws IllegalStateException {
        this.checkModelImplReady();

        this.dice.setLastNumberAppeared(memento.getLastNumberAppearedOnDice());
        this.maxItemsGeneration = memento.getMaxItemsGeneration();
        this.itemsCollected = memento.getItemsCollected();
        this.numberOfDiceRoll = memento.getNumberOfDiceRoll();
        this.isPlayerTurn = memento.isPlayerTurn();
        this.userScores = memento.getUserScores();
    }

    @Override
    public synchronized Pair<Optional<Integer>, Jump> getPlayerPosition(final int playerIndex) throws IllegalStateException {
        this.checkModelImplReady();

        int partialPlayerPosition = this.playersList.get(playerIndex).getPosition() 
                                    + this.dice.getLastNumberAppeared();

        partialPlayerPosition = partialPlayerPosition < 0 ? 0 : partialPlayerPosition;

        partialPlayerPosition = partialPlayerPosition > this.scenery.getNumberOfBoxes()
                                ? this.scenery.getNumberOfBoxes() - (partialPlayerPosition - this.scenery.getNumberOfBoxes())
                                : partialPlayerPosition;

        if (this.scenery.getSnakesMap().containsKey(partialPlayerPosition)) {
            final int finalPlayerPosition = this.scenery.getSnakesMap().get(partialPlayerPosition);
            return new Pair<>(this.playerPositionUtils(playerIndex, finalPlayerPosition), Jump.SNAKE);
        }

        if (this.scenery.getLaddersMap().containsKey(partialPlayerPosition)) {
            final int finalPlayerPosition = this.scenery.getLaddersMap().get(partialPlayerPosition);
            return new Pair<>(this.playerPositionUtils(playerIndex, finalPlayerPosition), Jump.LADDER);
        }

        //the specified player don't achieve neither a snake or a ladder
        this.playerPositionUtils(playerIndex, partialPlayerPosition);
        return new Pair<>(Optional.empty(), Jump.NO_JUMP);
    }

    @Override
    public synchronized void startGame(final List<Integer> data, final int numberOfPlayers, final TypesOfDice dice) throws IllegalArgumentException {
        if (numberOfPlayers <= 1) {
            throw new IllegalArgumentException("Number of players less or equal to 1!");
        }

        this.isReady = true;
        this.scenery = new SceneryFactoryImpl().createScenery(data);

        //fill playersList with the exact number of players playing the game and set their initial positions
        this.playersList.addAll(IntStream.range(0, numberOfPlayers)
                        .mapToObj(value -> new PlayerImpl())
                        .peek(player -> player.setPosition(PLAYER_INITIAL_POSITION))
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

        this.mementoTaker.addMemento(this.createMemento());
    }

    @Override
    public int getGameBoardSideSize() throws IllegalStateException {
        this.checkModelImplReady();

        return this.scenery.getSideSize();
    }

    @Override
    public int rollDice() throws IllegalStateException {
        this.checkModelImplReady();

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
        this.checkModelImplReady();

        this.clearFieldsListAndMap();
        this.setStateFromMemento(this.mementoTaker.getMemento());
    }

    @Override
    public synchronized void giveUpGame() throws IllegalStateException {
        this.checkModelImplReady();

        this.clearFieldsListAndMap();
        this.setStateFromMemento(this.mementoTaker.getMemento());
        this.playersList.clear();
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
    public synchronized TypesOfItem itemCollected(final int itemIndex, final Turn turn) throws IllegalArgumentException, NoSuchElementException {
        this.checkModelImplReady();

        if (!this.itemsMap.containsKey(itemIndex)) {
            throw new IllegalArgumentException("The item's index is not present in the Model!");
        }

        //add scores to user only if it's player's turn
        if (turn == Turn.PLAYER) {
            this.userScores += (int) this.itemsMap.get(itemIndex).runEffectGettingResult();
        }

        final SpecialItem item = this.itemsMap.get(itemIndex);
        this.deleteItemCollected(itemIndex);

        this.itemsCollected++;
        if (this.itemsCollected % (MAX_ITEMS_GENERATION / 2) == 0) {
            this.maxItemsGeneration = MAX_ITEMS_GENERATION / 2;
        }

        return item.getItemType();
    }

    @Override
    public Statistic getStatistics() {
        //build Statistic object
        final Statistic userStatistics = new StatisticImpl.StatisticBuilder()
                                                          .gamesWon(this.user.getGamesWon())
                                                          .gamesLost(this.user.getGamesLost())
                                                          .numberOfDiceRoll(this.user.getNumberOfDiceRoll())
                                                          .score(this.user.getScore())
                                                          .build();

        return userStatistics;
    }

    @Override
    public void clearStatistics() throws IOException {
        this.user.setGamesLost(0);
        this.user.setGamesWon(0);
        this.user.setNumberOfDiceRoll(0);
        this.user.setScore(0);

        final UserStatisticsFileWriter statWriter = UserStatisticsFileWriter.get();
        statWriter.writeUserStatistics(this.user.getScore(), this.user.getNumberOfDiceRoll(), 
                                       this.user.getGamesWon(), this.user.getGamesLost());
    }

    @Override
    public void matchFinished(final Turn turn) throws IllegalStateException, IOException {
        this.checkModelImplReady();

        final UserStatisticsFileWriter statWriter = UserStatisticsFileWriter.get();
        this.user.addScore(this.userScores);
        this.user.setNumberOfDiceRoll(this.user.getNumberOfDiceRoll() + this.numberOfDiceRoll);
        if (turn == Turn.PLAYER) {
            this.user.setGamesWon(this.user.getGamesWon() + 1);
        } else {
            this.user.setGamesLost(this.user.getGamesLost() + 1);
        }

        statWriter.writeUserStatistics(this.user.getScore(), this.user.getNumberOfDiceRoll(), 
                                       this.user.getGamesWon(), this.user.getGamesLost());
    }

}
