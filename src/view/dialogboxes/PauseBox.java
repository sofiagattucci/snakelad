package view.dialogboxes;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import view.LanguageStringMap;
import view.ViewImpl;
import view.scenes.Menu;

/**
 * This class represents the pause window.
 */
public class PauseBox extends BasicDialogBox {

    private static final String TITLE_KEY = "pause.pause";
    private static final String MSG_KEY = "pause.msg";
    private static final String RESUME_KEY = "pause.resume";
    private static final String GIVE_UP_KEY = "pause.giveUp";
    private static final String RESTART_KEY = "pause.restart";

    private final Label titleLabel = new Label(LanguageStringMap.get().getMap().get(TITLE_KEY));
    private final Label msgLabel = new Label(LanguageStringMap.get().getMap().get(MSG_KEY));
    private ButtonType giveUp;
    private ButtonType restart;

    /**
     * Constructor of the class.
     * @param parentStage
     *     The parent of the pause box.
     */
    public PauseBox(final Stage parentStage) {
        super(parentStage);
        this.prepareBox();
    }

    /**
     * It shows the pause box, then manages the choice the user made. 
     */
    @Override
    public void show() {
        final String choose = this.getBox().showAndWait().get().getText();
        if (choose.equals(this.giveUp.getText())) {
            ViewImpl.getObserver().giveUp();
            this.getStage().setScene(Menu.getScene(this.getStage()));
        }
        if (choose.equals(this.restart.getText())) {
            ViewImpl.getObserver().restart();
        }
    }

    /**
     * It updates the language in the elements in the tool bar.
     */
    public void updateLanguage() {
        this.titleLabel.setText(LanguageStringMap.get().getMap().get(TITLE_KEY));
        this.msgLabel.setText(LanguageStringMap.get().getMap().get(MSG_KEY));
        this.getBox().getButtonTypes().clear();
        this.prepareBox();
    }

    private void prepareBox() {
        final ButtonType resume = new ButtonType(LanguageStringMap.get().getMap().get(RESUME_KEY));
        this.giveUp = new ButtonType(LanguageStringMap.get().getMap().get(GIVE_UP_KEY));
        this.restart = new ButtonType(LanguageStringMap.get().getMap().get(RESTART_KEY));
        this.getBox().setTitle(titleLabel.getText());
        this.getBox().setHeaderText(msgLabel.getText());
        this.getBox().getButtonTypes().addAll(resume, this.restart, this.giveUp);
    }
}
