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

        this.mainSt = defaultStage;
        this.mainSt.setTitle(TITLE);
        this.mainSt.setHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * SCREEN_H_PERC);
        this.mainSt.setWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * SCREEN_W_PERC);
        this.mainSt.centerOnScreen();
        this.mainSt.setResizable(false);
        this.mainSt.setScene(Menu.getScene());
        this.mainSt.show();
    }
}
