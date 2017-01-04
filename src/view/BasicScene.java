package view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

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
