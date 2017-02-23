package controller;

import utilities.Difficulty;

/**
 * Builder for GameSettings class.
 *
 */
public class GameSettingsBuilder {

    private int numberOfPlayers;
    private Difficulty scenery;
    private int dice;

    /**
     * Set number of player.
     * @param nOfPlayers
     *              the number of players
     * @return the Builder
     */
    public GameSettingsBuilder numOfPlayers(final int nOfPlayers) {
        this.numberOfPlayers = nOfPlayers;
        return this;
    }

    /**
     * Set the scenery choose.
     * @param scenery
     *              the scenery to use
     * @return the Builder
     */
    public GameSettingsBuilder sceneryChoose(final Difficulty scenery) {
        this.scenery = scenery;
        return this;
    }

    /**
     * Set the type of dice choose.
     * @param dice
     *              the type of dice choose
     * @return the Builder
     */
    public GameSettingsBuilder diceChoose(final int dice) {
        this.dice = dice;
        return this;
    }

    /**
     * Build the Controller.
     * @return an instance of Controller
     */
    public GameSettings build() {
        return new GameSettings(numberOfPlayers, scenery, dice);
    }

}
