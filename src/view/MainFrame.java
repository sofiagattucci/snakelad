package view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utilities.ImageManager;
import view.scenes.Login;
import view.scenes.settings.MusicManager;

/**
 * This class creates and initializes the main frame of the application. 
 */
public class MainFrame extends Application {

    private static final String TITLE = "SnakeNLadder";
    private static final String ICONE = "./res/icons/logo.png";

    @Override
    public void start(final Stage defaultStage) {

        defaultStage.initStyle(StageStyle.UNDECORATED);
        defaultStage.setTitle(TITLE);
        defaultStage.getIcons().add(ImageManager.get().readFromFile(ICONE));

        defaultStage.setHeight(Dimension.SCREEN_H * Dimension.SCREEN_H_PERC);
        defaultStage.setWidth(Dimension.SCREEN_W * Dimension.SCREEN_W_PERC);
        defaultStage.setMaximized(true);
        defaultStage.centerOnScreen();
        defaultStage.setResizable(false);

        defaultStage.setScene(Login.getScene(defaultStage));
        defaultStage.show();
        ViewImpl.setAppStage(defaultStage);
        ViewImpl.getObserver().startMusic(MusicManager.getDefaultTrack());
    }
}
