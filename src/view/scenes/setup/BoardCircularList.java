package view.scenes.setup;

import javafx.scene.Node;
import javafx.scene.image.Image;
import utilities.Difficulty;
import utilities.ImageManager;
import view.BasicButton;
import view.LanguageStringMap;
import view.gameboard.GameBoardTypes;

/**
 * This class manages the choice of the scenery and its elements in the GUI.
 * It implemented by using a circular list defined in the extended class CircularList.
 */
public class BoardCircularList extends ImagesCircularList<Difficulty> {

    private static final String BEGINNER_KEY = "difficulty.beginner";
    private static final String EASY_KEY = "difficulty.easy";
    private static final String MEDIUM_KEY = "difficulty.medium";
    private static final String HIGH_KEY = "difficulty.high";
    private static final String SCENERY_LABEL_KEY = "setUp.selectBoard";
    private static final double BOARD_SIZE = BasicButton.getButtonHeight() * 1.8;

    /**
     * Constructor of this class.
     * @param next
     *     The next node of the layout graph to show in the GUI
     */
    public BoardCircularList(final Node next) {
        super(Difficulty.values().length, SCENERY_LABEL_KEY, BOARD_SIZE, Difficulty.BEGINNER, next);
    }

    private Difficulty calculateDifficulty(final int n) {
        switch(n) {
            case 0: return Difficulty.BEGINNER; 
            case 1: return Difficulty.EASY;
            case 2: return Difficulty.MEDIUM;
            case 3: return Difficulty.HIGH;
            default: return Difficulty.BEGINNER;
        }
    }

    @Override
    protected void updateDescLabel(final int n) {
        switch(n) {
            case 0: this.getDescLabel().setText(LanguageStringMap.get().getMap().get(BEGINNER_KEY)); 
                    break;
            case 1: this.getDescLabel().setText(LanguageStringMap.get().getMap().get(EASY_KEY));
                    break;
            case 2: this.getDescLabel().setText(LanguageStringMap.get().getMap().get(MEDIUM_KEY));
                    break;
            case 3: this.getDescLabel().setText(LanguageStringMap.get().getMap().get(HIGH_KEY));
            break;
            default:
        }
    }

    @Override
    protected void setParameter(final int n) {
        switch(n) {
            case 0: this.setParameterValue(Difficulty.BEGINNER); 
                    break;
            case 1: this.setParameterValue(Difficulty.EASY);
                    break;
            case 2: this.setParameterValue(Difficulty.MEDIUM);
                    break;
            case 3: this.setParameterValue(Difficulty.HIGH);
            break;
            default:
        }
        SetUpGame.setBoardType(this.getParameterValue());
    }

    @Override
    protected Image getImage() {
        return ImageManager.get().readFromFile(GameBoardTypes.get().getBoardMini(this.calculateDifficulty(this.getCounter())));
    }

    /**
     * It updates the language of the elements of this class.
     */
    public void updateLanguage() {
        super.updateLanguage(SCENERY_LABEL_KEY);
    }
}
