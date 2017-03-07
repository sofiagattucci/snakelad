package view.scenes;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.BasicButton;
import view.LanguageStringMap;
import view.dialogboxes.ClosureHandler;

/**
 * This class creates and initializes the login scene.
 */
public final class Login extends BasicScene {

    private static final String QUIT_KEY = "menu.quit";
    private static final String ENTER_KEY = "login.enter";

    private static Login loginScene = new Login();
    private static Stage loginStage;

    private final VBox box = new VBox();
    private final TextField nameField = new TextField();
    private final Button enter = new BasicButton(LanguageStringMap.get().getMap().get(ENTER_KEY));
    private final Button quit = new BasicButton(LanguageStringMap.get().getMap().get(QUIT_KEY));
    private final ClosureHandler closure = new ClosureHandler(loginStage);
 
    private Login() {

        this.getDefaultLayout().setCenter(this.box);
        this.box.setAlignment(Pos.CENTER);
        this.box.getChildren().addAll(this.nameField, this.enter, this.quit);
        this.enter.setOnAction(e -> {
            loginStage.setScene(Menu.getScene(loginStage));
        });
        this.quit.setOnAction(e -> this.closure.show());
    }

    /**
     * It updates the language of this scene.
     */
    public void updateLanguage() {
        this.quit.setText(LanguageStringMap.get().getMap().get(QUIT_KEY));
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
