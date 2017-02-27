package view.scenes.game;

import javafx.stage.Stage;
import view.pawn.AvailableColor;
import view.pawn.PawnsColor;

/**
 * It manages the tool bar shown in the screen in a single player game.
 */
public class SinglePlayerToolbar  extends Toolbar {

    private static final String CPU = "CPU";

    private void setCPU() {
        this.getPawnList().get(SinglePlayerGame.getCPUIndex()).getSecond().setText(CPU);
    }

    /**
     * It updates the language of the elements of this class.
     */
    public void updateLanguage() {
        super.updateLanguage();
        this.setCPU();
    }

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
        return PawnsColor.get().getSingleColor(i);
    }
}
