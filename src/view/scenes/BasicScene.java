package view.scenes;

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
    private Color backColor = Color.LIGHTBLUE;

    /**
     * Sets up some basic informations for the scene.
     */
    protected BasicScene() {
        super(new BorderPane());
        this.setRoot(this.bp);
        this.bp.setBackground(new Background(new BackgroundFill(this.backColor, CornerRadii.EMPTY, Insets.EMPTY))); 
    }

    /**
     * Getter of the default layout.
     * @return
     *     A border pane layout set as the default one.
     */
    protected BorderPane getDefaultLayout() {
        return this.bp;
    }

    /**
     * Getter of the default background color.
     * @return
     *     The default background color
     */
    protected Color getBackColor() {
        return this.backColor;
    }

    /**
     * Setter of the background color of the scene.
     * @param c
     *     The new Color used in the scene
     */
    protected void setBackColor(final Color c) {
        this.backColor = c;
        this.bp.setBackground(new Background(new BackgroundFill(this.backColor, CornerRadii.EMPTY, Insets.EMPTY))); 
    }
}
