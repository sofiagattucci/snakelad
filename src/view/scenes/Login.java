package view.scenes;

import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import view.BasicButton;
import view.LanguageStringMap;
import view.ViewImpl;
import view.dialogboxes.LoginClosureHandler;

/**
 * This class creates and initializes the login scene.
 */
public final class Login extends BasicScene {

    private static final String LABEL_KEY = "login.label";
    private static final String ENTER_KEY = "login.enter";
    private static final String QUIT_KEY = "menu.quit";
    private static final String SPACE_ERR_KEY = "login.error.space";
    private static final String MAX_ERR_KEY = "login.error.max";
    private static final String ERR_LABEL_ID = "ErrorMsg";
    private static final String TITLE_ID = "TitleLabel";
    private static final int MAX_CHARS = 15;
    private static final double BOX_SPACING = BasicButton.getButtonHeight() / 3;
    private static final int FONT_SIZE = 30;
    private static final String SPACE = " ";


    private static Login loginScene = new Login();
    private static Stage loginStage;

    private final VBox box = new VBox();
    private final TextField nameField = new TextField();
    private final Label descLabel = new Label(LanguageStringMap.get().getMap().get(LABEL_KEY));
    private final Label errorLabel = new Label();
    private final Button enter = new BasicButton(LanguageStringMap.get().getMap().get(ENTER_KEY));
    private final Button quit = new BasicButton(LanguageStringMap.get().getMap().get(QUIT_KEY));
    private final LoginClosureHandler closure = new LoginClosureHandler(loginStage);
 
    private Login() {

        this.getDefaultLayout().setCenter(this.box);
        this.box.setAlignment(Pos.CENTER);
        this.box.getChildren().addAll(this.descLabel, this.nameField, this.enter, this.quit, this.errorLabel);
        this.box.setSpacing(BOX_SPACING);
        this.box.setMaxWidth(BasicButton.getButtonWidth());
        this.descLabel.setFont(new Font(FONT_SIZE));
        this.descLabel.setId(TITLE_ID);
        this.getStylesheets().add(ViewImpl.getStylesheet());
        this.errorLabel.setId(ERR_LABEL_ID);
        this.nameField.setOnKeyReleased(e -> {
            if (this.nameField.getText().isEmpty()) {
                this.enter.setDisable(true);
            } else {
                this.enter.setDisable(false);
            }
            if (this.nameField.getText().contains(SPACE)) {
                this.enter.setDisable(true);
                this.errorLabel.setVisible(true);
                this.errorLabel.setText(LanguageStringMap.get().getMap().get(SPACE_ERR_KEY));
            } else { 
                if (this.nameField.getText().length() > MAX_CHARS) {
                    this.enter.setDisable(true);
                    this.errorLabel.setVisible(true);
                    this.errorLabel.setText(LanguageStringMap.get().getMap().get(MAX_ERR_KEY) + MAX_CHARS);
                } else {
                    this.errorLabel.setVisible(false);
                }
            }
        });
        this.enter.setDisable(true);
        this.enter.setOnAction(e -> {
                ViewImpl.setUsername(this.nameField.getText());
                loginStage.setScene(Menu.getScene(loginStage));
                try {
                    ViewImpl.getObserver().login(this.nameField.getText());
                } catch (IllegalArgumentException | IOException ex) {
                    ex.printStackTrace();
                }
        });
        this.quit.setOnAction(e -> this.closure.show());
    }

    /**
     * It updates the language of the elements of this class.
     */
    public void updateLanguage() {
        this.enter.setText((LanguageStringMap.get().getMap().get(ENTER_KEY)));
        this.quit.setText((LanguageStringMap.get().getMap().get(QUIT_KEY)));
        this.descLabel.setText(LanguageStringMap.get().getMap().get(LABEL_KEY));
        this.closure.updateLanguage();
    }

    /**
     * Getter if the login scene.
     * @param stage
     *     The main stage of the whole application
     * @return
     *     The Login scene to be used in the application
     */
    public static Login getScene(final Stage stage) {
        loginStage = stage;
        return loginScene;
    }
}
