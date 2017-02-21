package controller;
/**
 * Class for set the settings of game.
 *
 */
public class GameSettings {

    private final int numberOfPlayers;
    private final int scenery;
    private final int dice;

    /**
     * Constructor.
     * @param numOfPlayers
     *          the number of player
     * @param scenery
     *          the scenery choose
     * @param dice
     *          the dice choose
     */
    public GameSettings(final int numOfPlayers, final int scenery, final int dice) {
        this.numberOfPlayers = numOfPlayers;
        this.scenery = scenery;
        this.dice = dice;
    }

}
