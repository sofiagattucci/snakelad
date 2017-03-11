package view.scenes;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.BasicButton;
import view.LanguageStringMap;

/**
 * This class creates and initializes the statistics scene.
 */
public final class Statistics extends BasicScene {

    private static final String BACK_KEY = "back";

    private static Statistics statisticScene = new Statistics();
    private static Stage statisticStage;

    private final Button back = new BasicButton(LanguageStringMap.get().getMap().get(BACK_KEY));
    private final VBox box = new VBox(this.back);

    private Statistics() {

        this.getDefaultLayout().setCenter(this.box);
        this.box.setAlignment(Pos.CENTER);
        this.back.setOnAction(e -> statisticStage.setScene(Menu.getScene(statisticStage)));
    }

    /**
     * It updates the language of this scene.
     */
    public void updateLanguage() {
        this.back.setText(LanguageStringMap.get().getMap().get(BACK_KEY));
    }

    /**
     * Getter of this scene unique instance.
     * @param st
     *     The main stage of the application
     * @return
     *     The Statistic unique instance scene 
     */
    public static Statistics getScene(final Stage st) {
        statisticStage = st;
        return statisticScene;
    }
}
