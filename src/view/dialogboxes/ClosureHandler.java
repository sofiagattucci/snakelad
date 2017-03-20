package view.dialogboxes;

import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import view.ViewImpl;
import view.scenes.Login;

/**
 * This class handles the closure of the application from the menu scene. 
 */
public final class ClosureHandler extends LoginClosureHandler {

    private static final String LOG_OUT = "Logout";
    private static final String OK = "OK";

     /**
     * Constructor of this class.
     * @param parentStage
     *     The parent stage of this closure handler
     */
    public ClosureHandler(final Stage parentStage) {
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
        if (choose.equals(LOG_OUT)) {
            ViewImpl.getAppStage().setScene(Login.getScene(ViewImpl.getAppStage()));
        }
    }

    /**
     * It updates the language of this dialog box.
     */
    public void updateLanguage() {
        super.updateLanguage();
        this.prepareBox();
    }

    private void prepareBox() {

        final ButtonType logout = new ButtonType(LOG_OUT);
        this.getBox().getButtonTypes().add(logout);
    }
}
