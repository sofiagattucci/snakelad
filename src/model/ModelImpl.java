package model;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import utilities.TypesOfDice;
import java.util.LinkedList;

/**
 * This is the main class of the model in MVC pattern.
 * It represents the game overall, with all his entities and main features.
 */
public final class ModelImpl implements Model {

    private static final Supplier<RuntimeException> EXCEPTION_SUPPLIER = () -> new IllegalStateException("The method statGame() "
                                                                                                       + "must be called before "
                                                                                                       + "calling this method!");
    private static final int PLAYER_INITIAL_POSITION = 0;

    private Scenery scenery;
    private final List<Player> playersList = new LinkedList<>();
    private Dice dice;
    //'isReady' is false if the method called startGame() has never been called, otherwise 
    //true. In fact, the method statGame() must be called before any other method.
    private boolean isReady;

    /**
     * ModelImpl constructor.
     */
    public ModelImpl() {
        this.scenery = SceneryImpl.get();
        this.isReady = false;
    }

    private void clearEntities() {
        this.playersList.stream()
                        .forEach(player -> player.setNewPosition(PLAYER_INITIAL_POSITION));

        this.dice.setLastNumberAppeared(Optional.empty());
    }

    //private method called to avoid too much repetition of identical code in getPlayerPosition() method.
    private Optional<Integer> playerPositionUtils(final int index, final int position) {
        this.playersList.get(index).setNewPosition(position);
        return Optional.of(this.playersList.get(index).getPosition());
    }

    @Override
    public Optional<Integer> getPlayerPosition(final int playerIndex) {
        if (!this.isReady) {
            throw EXCEPTION_SUPPLIER.get();
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
    public int getGameBoardSideSize() {
        if (!this.isReady) {
            throw EXCEPTION_SUPPLIER.get();
        }

        return this.scenery.getSideSize();
    }

    @Override
    public int getNumberFromDice() {
        if (!this.isReady) {
            throw EXCEPTION_SUPPLIER.get();
        }

        return this.dice.roll();
    }

    @Override
    public void restartGame() {
        if (!this.isReady) {
            throw EXCEPTION_SUPPLIER.get();
        }

        this.clearEntities();
    }

    @Override
    public void giveUpGame() {
        if (!this.isReady) {
            throw EXCEPTION_SUPPLIER.get();
        }

        this.clearEntities();
        this.scenery.clearMaps();
        this.isReady = false;
    }

}
