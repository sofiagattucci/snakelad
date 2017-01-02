package view;

import java.awt.Toolkit;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class creates and initializes the main frame of the application. 
 */
public class MainFrame extends Application {

    private static final double SCREEN_W_PERC = 0.9;
    private static final double SCREEN_H_PERC = 0.9;
    private static final String TITLE = "SnakeNLadder";

    private Stage mainSt = null;

    @Override
    public void start(final Stage defaultStage) {

        mainSt = defaultStage;
        mainSt.setTitle(TITLE);
        mainSt.setHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * SCREEN_H_PERC);
        mainSt.setWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * SCREEN_W_PERC);
        mainSt.centerOnScreen();
        mainSt.setResizable(false);

        mainSt.show();
    }
}
