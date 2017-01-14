package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * Generic implementation for a basic scene of the application.
 */
public class BasicScene extends Scene {

    private final BorderPane bp = new BorderPane();

    /**
     * Sets up some basic informations for the scene.
     */
    protected BasicScene() {
        super(new BorderPane());

        this.setRoot(this.bp);
        bp.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * Getter of the default layout.
     * @return
     *     A border pane layout set as the default one.
     */
    protected BorderPane getDefaultLayout() {
        return this.bp;
    }
}
