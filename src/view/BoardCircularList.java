package view;

import javafx.scene.Node;
import javafx.scene.image.Image;
import utilities.Difficulty;
import utilities.ImageManager;
import view.gameboard.GameBoardTypes;
import view.scenes.SetUpGame;

/**
 * This class manages the choice of the scenery and its elements in the GUI.
 * It implemented by using a circular list defined in the extended class CircularList.
 */
public class BoardCircularList extends ImagesCircularList {

    private static final String BEGINNER_KEY = "difficulty.beginner";
    private static final String EASY_KEY = "difficulty.easy";
    private static final String MEDIUM_KEY = "difficulty.medium";
    private static final String SCENERY_LABEL_KEY = "setUp.selectBoard";
    private static final int NUM_SCENARY = 3;
    private static final double BOARD_SIZE = BasicButton.getButtonHeight() * 1.5;

    private Difficulty boardType = Difficulty.BEGINNER;
    private final Node nextNode;

    /**
     * Constructor of this class.
     * @param next
     *     The next node of the layout graph to show in the GUI
     */
    public BoardCircularList(final Node next) {
        super(NUM_SCENARY, SCENERY_LABEL_KEY, BOARD_SIZE);
        this.nextNode = next;
        for (final Difficulty d: Difficulty.values()) {
            this.insertElem(ImageManager.get().readFromFile(GameBoardTypes.get().getBoard(d)));
        }
    }

    private Difficulty calculateDifficulty(final int n) {
        switch(n) {
            case 0: return Difficulty.BEGINNER; 
            case 1: return Difficulty.EASY;
            case 2: return Difficulty.MEDIUM;
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
            default:
        }
    }

    @Override
    protected void setParameter(final int n) {
        switch(n) {
            case 0: this.boardType = Difficulty.BEGINNER; 
                    break;
            case 1: this.boardType = Difficulty.EASY;
                    break;
            case 2: this.boardType = Difficulty.MEDIUM;
                    break;
            default:
        }
        SetUpGame.setBoardType(this.boardType);
    }

    @Override
    protected Image getImage() {
        return ImageManager.get().readFromFile(GameBoardTypes.get().getBoard(this.calculateDifficulty(this.getCounter())));
    }

    @Override
    protected Node nextToShow() {
        return this.nextNode;
    }

    /**
     * Getter of the type of the selected board.
     * @return
     *     The type of the selected board
     */
    public Difficulty getSelectedType() {
        return this.boardType;
    }

    /**
     * It updates the language of the elements of this class.
     */
    public void updateLanguage() {
        this.getTitleLabel().setText(LanguageStringMap.get().getMap().get(SCENERY_LABEL_KEY));
    }
}
