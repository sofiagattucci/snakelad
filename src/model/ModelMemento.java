package model;

import java.util.Optional;

/**
 * Captures and externalises a partial snapshot of ModelImpl's internal state, so that ModelImpl can be restored to this state later.
 * It's designed using Memento patterns.
 */
public final class ModelMemento {

    private final Optional<Integer> lastNumberAppearedOnDice;
    private final int maxItemsGeneration;
    private final int itemsCollected;
    private final int numberOfDiceRoll;
    private final boolean playerTurn;
    private final int userScores;

    /**
     * ModelMemento constructor. Sets a partial snapshot of ModelImpl's internal state inside the memento.
     * @param lastNumberAppearedOnDice
     *                  The last number appear rolling the dice.
     * @param maxItemsGeneration
     *                  The maximum number of permitted items generations.
     * @param itemsCollected
     *                  The number of items collected by the player or CPU.
     * @param numberOfDiceRoll
     *                  The number of times the user roll a dice.
     * @param playerTurn
     *                  True if it's the player's turn, otherwise false.
     * @param userScores
     *                  The current user's scores.
     */
    public ModelMemento(final Optional<Integer> lastNumberAppearedOnDice, final int maxItemsGeneration,
                        final int itemsCollected, final int numberOfDiceRoll, final boolean playerTurn, final int userScores) {

        this.lastNumberAppearedOnDice = lastNumberAppearedOnDice;
        this.maxItemsGeneration = maxItemsGeneration;
        this.itemsCollected = itemsCollected;
        this.numberOfDiceRoll = numberOfDiceRoll;
        this.playerTurn = playerTurn;
        this.userScores = userScores;
    }

    /**
     * Returns the last number appear rolling the dice.
     * @return the last number appear rolling the dice.
     */
    public Optional<Integer> getLastNumberAppearedOnDice() {
        return this.lastNumberAppearedOnDice;
    }

    /**
     * Returns the maximum number of permitted items generations.
     * @return the maximum number of permitted items generations.
     */
    public int getMaxItemsGeneration() {
        return this.maxItemsGeneration;
    }

    /**
     * Returns the number of items collected by the player or CPU.
     * @return the number of items collected by the player or CPU.
     */
    public int getItemsCollected() {
        return this.itemsCollected;
    }

    /**
     * Returns the number of times the user roll a dice.
     * @return the number of times the user roll a dice.
     */
    public int getNumberOfDiceRoll() {
        return this.numberOfDiceRoll;
    }

    /**
     * Returns true if it's the player's turn, otherwise false.
     * @return true if it's the player's turn, otherwise false.
     */
    public boolean isPlayerTurn() {
        return this.playerTurn;
    }

    /**
     * Returns the current user's scores.
     * @return the current user's scores.
     */
    public int getUserScores() {
        return this.userScores;
    }

}
