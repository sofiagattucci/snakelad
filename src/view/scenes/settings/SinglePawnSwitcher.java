package view.scenes.settings;

import view.LanguageStringMap;
import view.pawn.AvailableColor;
import view.pawn.PawnsColor;

/**
 * It manages the elements of the GUI that permit the switch the color of the pawns in single player mode. 
 */
public class SinglePawnSwitcher extends PawnColorSwitcher {

    private static final String SINGLE_KEY = "setUp.single";
    private static final String PLAYER_KEY = "game.player";
    private static final String CPU = "CPU";
    private static final int NUM_PLAYERS = 2;
    private static final int PLAYER_INDEX = 0;
    private static final int CPU_INDEX = 1;

    /**
     * Constructor of this class.
     */
    public SinglePawnSwitcher() {
        super(SINGLE_KEY, NUM_PLAYERS);
        this.setLabels();
    }

    private void setLabels() {
        this.getPawnList().get(CPU_INDEX).getFirst().setText(CPU);
        this.getPawnList().get(PLAYER_INDEX).getFirst().setText(LanguageStringMap.get().getMap().get(PLAYER_KEY));
    }

    @Override
    protected AvailableColor getColorKey(final int i) {
        return PawnsColor.get().getSingleColor(i);
    }

    /**
     * It updates the language of the elements of this class.
     */
    public void updateLanguage() {
        super.updateLanguage();
        this.setLabels();
    }

    @Override
    protected void switchColor(final int index, final AvailableColor c) {
        PawnsColor.get().switchColorSingle(index, c);
    }
}
