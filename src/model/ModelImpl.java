package model;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import utilities.TypesOfDice;
import utilities.TypesOfItem;

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
    private static final int MAX_ITEMS_GENERATION = 15;

    private final User user = UserImpl.get();
    private Scenery scenery;
    private final List<Player> playersList = new LinkedList<>();
    private Dice dice;
    private final List<SpecialItems> itemsList = new LinkedList<>();
    //'isReady' is false if the method called startGame() has never been called, otherwise 
    //true. In fact, the method statGame() must be called before any other method.
    private boolean isReady;
    //contains the maximum number of permitted items generations.
    private int maxItemsGeneration;

    /**
     * ModelImpl constructor.
     */
    public ModelImpl() {
        this.scenery = SceneryImpl.get();
        this.isReady = false;
        this.maxItemsGeneration = MAX_ITEMS_GENERATION;
    }

    private void clearEntities() {
        this.playersList.stream()
                        .forEach(player -> player.setNewPosition(PLAYER_INITIAL_POSITION));

        this.dice.setLastNumberAppeared(Optional.empty());
        this.itemsList.clear();
        this.maxItemsGeneration = MAX_ITEMS_GENERATION;
    }

    //private method called to avoid too much repetition of identical code in getPlayerPosition() method.
    private Optional<Integer> playerPositionUtils(final int index, final int position) {
        this.playersList.get(index).setNewPosition(position);
        return Optional.of(this.playersList.get(index).getPosition());
    }
 
    //private method called to avoid too much repetition of identical code in tryGenerateCoin() and tryGenerateDiamond() methods.
    private synchronized Optional<Integer> generateItemsUtils(final TypesOfItem typeOfItem) {
        if (!this.isReady) {
            throw ILLEGAL_STATE_EXCEPTION_SUPPLIER.get();
        }

        final ItemsGenerator itemGenerator = ItemsGeneratorImpl.get();
        final List<Integer> occupiedPositionsList = new LinkedList<>();
        this.itemsList.stream()
                      .forEach(item -> occupiedPositionsList.add(item.getPosition()));

        final Optional<Integer> generationResult = itemGenerator.tryGenerateItem(this.scenery.getNumberOfBoxes(), 
                                                                                 occupiedPositionsList, typeOfItem);
        if (!generationResult.isPresent()) {
            return generationResult;
        }

        this.maxItemsGeneration--;
        if (maxItemsGeneration < 0) {
            maxItemsGeneration = 0;
            return Optional.empty();
        }

        this.itemsList.add((typeOfItem == TypesOfItem.COIN) ? new Coin(generationResult.get()) 
                           : (typeOfItem == TypesOfItem.DIAMOND) ? new Diamond(generationResult.get())
                           : new Skull(generationResult.get()));

        return generationResult;
    }

    @Override
    public Optional<Integer> getPlayerPosition(final int playerIndex) {
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
    public void startGame(final List<Integer> data, final int numberOfPlayers, final TypesOfDice dice) throws IllegalArgumentException {
        if (numberOfPlayers <= 1) {
            throw new IllegalArgumentException("Number of players less or equal to 1!");
        }

        this.isReady = true;
        this.scenery = new SceneryFactoryImpl().createScenery(data);

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
    public int getUserScores() {
        return this.user.getScores();
    }

    @Override
    public int getGameBoardSideSize() {
        if (!this.isReady) {
            throw ILLEGAL_STATE_EXCEPTION_SUPPLIER.get();
        }

        return this.scenery.getSideSize();
    }

    @Override
    public int getNumberFromDice() {
        if (!this.isReady) {
            throw ILLEGAL_STATE_EXCEPTION_SUPPLIER.get();
        }

        return this.dice.roll();
    }

    @Override
    public void restartGame() {
        if (!this.isReady) {
            throw ILLEGAL_STATE_EXCEPTION_SUPPLIER.get();
        }

        this.clearEntities();
    }

    @Override
    public void giveUpGame() {
        if (!this.isReady) {
            throw ILLEGAL_STATE_EXCEPTION_SUPPLIER.get();
        }

        this.clearEntities();
        this.scenery.clearMaps();
        this.isReady = false;
    }

    @Override
    public Optional<Integer> tryGenerateCoin() {

        return this.generateItemsUtils(TypesOfItem.COIN);
    }

    @Override
    public Optional<Integer> tryGenerateDiamond() {

        return this.generateItemsUtils(TypesOfItem.DIAMOND);
    }

    @Override
    public Optional<Integer> tryGenerateSkull() {

        return this.generateItemsUtils(TypesOfItem.SKULL);
    }

}
