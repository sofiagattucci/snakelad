package view.scenes.settings;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import utilities.ImageManager;
import view.BasicButton;
import view.LanguageStringMap;
import view.ViewImpl;

/**
 * It holds the elements in the GUI that manages the music. 
 */
public class MusicManager {

    private static final String SPEAKER_ON = "./res/Icons/volume_on.png";
    private static final String SPEAKER_OFF = "./res/Icons/volume_off.png";
    private static final String MUSIC_KEY = "settings.musicLabel";
    private static final int FONT_SIZE = 20;
    private static final double SPEAKER_SIZE = BasicButton.getButtonHeight() / 2;
    private static final double BOX_SPACING = BasicButton.getButtonHeight() / 3;

    private final Label title = new Label(LanguageStringMap.get().getMap().get(MUSIC_KEY));
    private final ImageView speaker = ImageManager.get().getImageView(SPEAKER_ON);
    private final HBox box = new HBox(this.title, this.speaker);
    private boolean musicOn = true;

    /**
     * Constructor of this class.
     */
    public MusicManager() {

        this.box.setAlignment(Pos.CENTER);
        this.box.setSpacing(BOX_SPACING);
        this.title.setFont(new Font(FONT_SIZE));
        this.speaker.setFitHeight(SPEAKER_SIZE);
        this.speaker.setFitWidth(SPEAKER_SIZE);
        this.speaker.setOnMouseClicked(e -> {
            if (this.musicOn) {
                this.musicOn = false;
                this.speaker.setImage(ImageManager.get().readFromFile(SPEAKER_OFF));
                ViewImpl.getObserver().stopMusic();
            } else {
                this.musicOn = true;
                this.speaker.setImage(ImageManager.get().readFromFile(SPEAKER_ON));
                ViewImpl.getObserver().startMusic();
            }
        });
    }

    /**
     * It updates the language of the elements of this class.
     */
    public void updateLanguage() {
        this.title.setText(LanguageStringMap.get().getMap().get(MUSIC_KEY));
    }

    /**
     * Getter of the parent node of the entire music manager.
     * @return
     *     The parent node of this class elements
     */
    public Node getParentNode() {
        return this.box;
    }
}
