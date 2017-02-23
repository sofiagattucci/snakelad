package view.dialog_boxes;

import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import view.LanguageStringMap;

/**
 * This class handles the closure of the application. 
 */
public final class ClosureHandler extends BasicDialogBox {

    private static final String QUIT_MSG_KEY = "closure.quit";
    private static final String CONFIRMATION_MSG_KEY = "closure.confirmation"; 
    private static final String CANCEL_KEY = "closure.cancel"; 

    private final  Label quitMsg = new Label(LanguageStringMap.get().getMap().get(QUIT_MSG_KEY));
    private final Label confirmationMsg = new Label(LanguageStringMap.get().getMap().get(CONFIRMATION_MSG_KEY));

     /**
     * Constructor of the ClosureHandler.
     * @param parentStage
     *     The parent stage of this closure handler
     */
    public ClosureHandler(final Stage parentStage) {
        super(parentStage);
        this.prepareBox();
    }

    @Override
    public void show() {
        if (this.getBox().showAndWait().get() == ButtonType.OK) {
             Platform.exit();
        }
    }

    /**
     * It updates the language of this dialog box.
     */
    public void updateLanguage() {
        this.quitMsg.setText(LanguageStringMap.get().getMap().get(QUIT_MSG_KEY));
        this.confirmationMsg.setText(LanguageStringMap.get().getMap().get(CONFIRMATION_MSG_KEY));
        this.getBox().getButtonTypes().clear();
        this.prepareBox();
    }

    private void prepareBox() {
        this.getBox().setTitle(this.quitMsg.getText());
        this.getBox().setHeaderText(this.confirmationMsg.getText());
        final ButtonType cancel = new ButtonType(LanguageStringMap.get().getMap().get(CANCEL_KEY));
        this.getBox().getButtonTypes().addAll(ButtonType.OK, cancel);
    }
}
