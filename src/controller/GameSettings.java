package controller;

import utilities.Difficulty;
import utilities.GameMode;
import utilities.TypesOfDice;

/**
 * Class for set the settings of game.
 *
 */
public class GameSettings {

    private final int numberOfPlayers;
    private final Difficulty scenery;
    private final TypesOfDice dice;
    private final GameMode modality;

    /**
     * Constructor.
     * @param numOfPlayers
     *          the number of player
     * @param scenery
     *          the scenery choose
     * @param dice
     *          the dice choose
     * @param modality
     *          the modality of game choose
     */
    public GameSettings(final int numOfPlayers, final Difficulty scenery, final TypesOfDice dice, final GameMode modality) {
        this.numberOfPlayers = numOfPlayers;
        this.scenery = scenery;
        this.dice = dice;
        this.modality = modality;
    }

    /**
     * Getter for number of players.
     * @return the number of players.
     */
    public int getNumberOfPlayer() {
        return this.numberOfPlayers;
    }

    /**
     * Getter for scenery choose.
     * @return the scenery choose.
     */
    public Difficulty getScenery() {
        return this.scenery;
    }

    /**
     * Getter for dice choose.
     * @return the dice choose.
     */
    public TypesOfDice getDice() {
        return this.dice;
    }

    /**
     * Getter for modality of game choose.
     * @return the modality choose.
     */
    public GameMode getModality() {
        return this.modality;
    }

}
