package view.dialogboxes;

import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import view.LanguageStringMap;
import view.ViewImpl;

/**
 * This class handles the closure of the application from the login scene. 
 */
public class LoginClosureHandler extends BasicDialogBox {

    private static final String QUIT_MSG_KEY = "closure.quit";
    private static final String CONFIRMATION_MSG_KEY = "closure.confirmation"; 
    private static final String CANCEL_KEY = "closure.cancel";
    private static final String OK = "OK";

    private final  Label quitMsg = new Label(LanguageStringMap.get().getMap().get(QUIT_MSG_KEY));
    private final Label confirmationMsg = new Label(LanguageStringMap.get().getMap().get(CONFIRMATION_MSG_KEY));

     /**
     * Constructor of this class.
     * @param parentStage
     *     The parent stage of this closure handler
     */
    public LoginClosureHandler(final Stage parentStage) {
        super(parentStage);
        this.prepareBox();
    }

    @Override
    public void show() {
        final String choose = this.getBox().showAndWait().get().getText();
        if (choose.equals(OK)) {
             Platform.exit();
             ViewImpl.getObserver().quit();
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
