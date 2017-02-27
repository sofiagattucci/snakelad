package view.scenes.settings;

import view.pawn.AvailableColor;
import view.pawn.PawnsColor;

/**
 * It manages the elements of the GUI that permit the switch the color of the pawns in player versus player mode. 
 */
public class MultiPawnSwitcher extends PawnColorSwitcher {

    private static final String MULTI_KEY = "setUp.multi";

    /**
     * Constructor of this class.
     * @param nPlayers
     *     The number of players to manage
     */
    public MultiPawnSwitcher(final int nPlayers) {
        super(MULTI_KEY, nPlayers);
    }

    @Override
    protected AvailableColor getColorKey(final int i) {
        return PawnsColor.get().getMultiColor(i);
    }

    @Override
    protected void switchColor(final int index, final AvailableColor c) {
        PawnsColor.get().switchColorMulti(index, c);
    }

}
