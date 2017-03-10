package controller;

import utilities.enumeration.Difficulty;
import utilities.enumeration.GameMode;
import utilities.enumeration.TypesOfDice;

/**
 * Builder for GameSettings class.
 *
 */
public class GameSettingsBuilder {

    private int numberOfPlayers;
    private Difficulty scenery;
    private TypesOfDice dice;
    private GameMode modality;

    /**
     * Set number of player.
     * @param nOfPlayers
     *          the number of players
     * @return the Builder
     */
    public GameSettingsBuilder numOfPlayers(final int nOfPlayers) {
        this.numberOfPlayers = nOfPlayers;
        return this;
    }

    /**
     * Set the scenery choose.
     * @param scenery
     *          the scenery to use
     * @return the Builder
     */
    public GameSettingsBuilder sceneryChoose(final Difficulty scenery) {
        this.scenery = scenery;
        return this;
    }

    /**
     * Set the type of dice choose.
     * @param dice
     *          the type of dice choose
     * @return the Builder
     */
    public GameSettingsBuilder diceChoose(final TypesOfDice dice) {
        this.dice = dice;
        return this;
    }

    /**
     * Set the mode of game choose.
     * @param modality
     *          the modality choose
     * @return the Builder
     */
    public GameSettingsBuilder modalityChoose(final GameMode modality) {
        this.modality = modality;
        return this;
    }

    /**
     * Build the Controller.
     * @return an instance of Controller
     */
    public GameSettings build() {
        return new GameSettings(numberOfPlayers, scenery, dice, modality);
    }

}
