package view;

import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import view.scenes.Menu;

/**
 * This class represents the pause window.
 */
public class PauseBox extends BasicDialogBox {

    private static final String TITLE = "PAUSE";
    private static final String MESSAGE = "What do you want to do?";
    private static final String RESUME = "Resume";
    private static final String GIVE_UP = "Give up";
    private static final String RESTART = "Restart";

    private final ButtonType resume = new ButtonType(RESUME);
    private final ButtonType giveUp = new ButtonType(GIVE_UP);
    private final ButtonType restart = new ButtonType(RESTART);

    /**
     * Constructor of the class.
     * @param parentStage
     *     The parent of the pause box.
     */
    public PauseBox(final Stage parentStage) {
        super(parentStage);
        this.getBox().setTitle(TITLE);
        this.getBox().setHeaderText(MESSAGE);
        this.getBox().getButtonTypes().setAll(resume, restart, giveUp);
    }

    /**
     * It shows the pause box, then manages the choice the user made. 
     */
    @Override
    public void show() {
        final String choose = this.getBox().showAndWait().get().getText();
        if (choose.equals(GIVE_UP)) {
            ViewImpl.getObserver().giveUp();
            this.getStage().setScene(Menu.getScene(this.getStage()));
        }
        if (choose.equals(RESTART)) {
            ViewImpl.getObserver().restart();
        }
    }
}
