package view.scenes.game;

import javafx.stage.Stage;
import view.pawn.AvailableColor;
import view.pawn.PawnsColor;

/**
 * It manages the tool bar shown in the screen in a player versus player game.
 */
public class MultiPlayerToolbar extends Toolbar {

    /**
     * Getter of the box' s width.
     * @return
     *     The width of the box
     */
    public static double getBoxWidth() {
        return Toolbar.getBoxWidth();
    }

    /**
     * It links the stage that contains the game scene to the one of this class.
     * @param stage
     *     The stage to link
     */
    public static void setStage(final Stage stage) {
        Toolbar.setStage(stage);
    }

    @Override
    protected AvailableColor getColorFromMode(final int i) {
        return PawnsColor.get().getMultiColor(i);
    }
}
