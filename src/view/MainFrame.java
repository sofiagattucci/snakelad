package view;

import java.awt.Toolkit;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * This class creates and initializes the main frame of the application. 
 */
public class MainFrame extends Application {

    private static final String TITLE = "SnakeNLadder";

    @Override
    public void start(final Stage defaultStage) {

        defaultStage.initStyle(StageStyle.UNDECORATED);
        defaultStage.setTitle(TITLE);
        defaultStage.setHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * Dimension.SCREEN_H_PERC);
        defaultStage.setWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * Dimension.SCREEN_W_PERC);
        defaultStage.setMaximized(true);
        defaultStage.centerOnScreen();
        defaultStage.setResizable(false);
        defaultStage.setScene(Menu.getScene(defaultStage));
        defaultStage.show();
    }
}
