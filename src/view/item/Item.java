package view.item;

import javafx.scene.image.ImageView;

/**
 * Interface for a generic item shown in the GUI.
 */
public interface Item {

    /**
     * It resizes the item and sets its position when necessary.
     */
    void resize();

    /**
     * Getter of the item ImageView.
     * @return
     *     The item ImageView
     */
    ImageView getItemImageView();
}
