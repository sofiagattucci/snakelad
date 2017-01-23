package view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utilities.ImageLoader;

/**
 * This class creates and initializes the main frame of the application. 
 */
public class MainFrame extends Application {

    private static final String TITLE = "SnakeNLadder";
    private static final String ICONE = "./res/logo.png";

    @Override
    public void start(final Stage defaultStage) {

        defaultStage.initStyle(StageStyle.UNDECORATED);
        defaultStage.setTitle(TITLE);
        defaultStage.getIcons().add(ImageLoader.get().getImage(ICONE));

        defaultStage.setHeight(Dimension.SCREEN_H * Dimension.SCREEN_H_PERC);
        defaultStage.setWidth(Dimension.SCREEN_W * Dimension.SCREEN_W_PERC);
        defaultStage.setMaximized(true);
        defaultStage.centerOnScreen();
        defaultStage.setResizable(false);

        defaultStage.setScene(Menu.getScene(defaultStage));
        defaultStage.show();
    }
}
