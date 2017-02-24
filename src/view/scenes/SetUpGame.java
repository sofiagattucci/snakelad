package view.scenes;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utilities.Difficulty;
import utilities.ImageManager;
import utilities.TypesOfDice;
import view.BasicButton;
import view.Dimension;
import view.LanguageStringMap;
import view.Toolbar;
import view.ViewImpl;
import view.dice.DiceTypes;
import view.gameboard.GameBoardTypes;

/**
 * It's the scene shown when the user pushes the play button. It manages the settings to use for this game. 
 */
public final class SetUpGame extends BasicScene {

    private static final String BEGINNER_KEY = "difficulty.beginner";
    private static final String EASY_KEY = "difficulty.easy";
    private static final String MEDIUM_KEY = "difficulty.medium";
    private static final String SINGLE_KEY = "setUp.single";
    private static final String MULTI_KEY = "setUp.multi";
    private static final String BACK_KEY = "back";
    private static final String START_KEY = "setUp.start";
    private static final String HOW_MANY_KEY = "setUp.selectPlayers";
    private static final String SCENERY_LABEL_KEY = "setUp.selectBoard";
    private static final String DICE_LABEL_KEY = "setUp.selectDice";
    private static final String TITLE_KEY = "setUp.title";
    private static final String OK = "OK";
    private static final double BOX_SPACING = BasicButton.getButtonHeight() / 3;
    private static final int MAX_PLAYERS = 6;
    private static final int NUM_SCENARY = 3;
    private static final int NUM_DICE = 3;
    private static final int FONT = 20;
    private static final int TITLE_FONT = 65;
    private static final double Y_TITLE_TRANSLATE = -Dimension.SCREEN_H / 10; 
    private static final int SINGLE_MODE_PLAYERS = 2;
    private static final double BOARD_SIZE = BasicButton.getButtonHeight() * 1.5;
    private static final Difficulty DEFAULT_DIFFICULTY = Difficulty.BEGINNER;
    private static final int DEFAULT_DIFFICULTY_INDEX = 1;
    private static final TypesOfDice DEFAULT_DICE = TypesOfDice.CLASSIC_DICE;
    private static final int DEFAULT_DICE_INDEX = 1;
    private static final int DEFAULT_DICE_IMAGE_INDEX = 5;

    private static Stage setUpStage;
    private static SetUpGame setUpScene = new SetUpGame();
    private static int numPlayers;
    private static Difficulty boardType;
    private static TypesOfDice diceType;

    private final Label title = new Label(LanguageStringMap.get().getMap().get(TITLE_KEY));
    private final Button single = new BasicButton(LanguageStringMap.get().getMap().get(SINGLE_KEY));
    private final Button multi = new BasicButton(LanguageStringMap.get().getMap().get(MULTI_KEY));
    private final Button back = new BasicButton(LanguageStringMap.get().getMap().get(BACK_KEY));
    private final HBox modes = new HBox(single, multi, back);
    private final List<Button> nPlayer = new ArrayList<>();
    private final Label howMany = new Label(LanguageStringMap.get().getMap().get(HOW_MANY_KEY));
    private final HBox chooseNumber = new HBox(howMany);
    private final List<Image> boardList = new ArrayList<>();
    private final ImageView board = new ImageView();
    private final Label boardDesc = new Label();
    private final Label scenaryLabel = new Label(LanguageStringMap.get().getMap().get(SCENERY_LABEL_KEY));
    private final Button nextBoard = new Button(">");
    private final Button prevBoard = new Button("<");
    private final Button okBoard = new Button(OK);
    private final HBox scenaryChoose = new HBox(this.scenaryLabel, this.prevBoard, this.board, this.nextBoard, 
                                                this.okBoard, this.boardDesc);
    private final List<Image> diceList = new ArrayList<>();
    private final ImageView dice = new ImageView();
    private final Label diceDesc = new Label();
    private final Label diceLabel = new Label(LanguageStringMap.get().getMap().get(DICE_LABEL_KEY));
    private final Button nextDice = new Button(">");
    private final Button prevDice = new Button("<");
    private final Button okDice = new Button(OK);
    private final HBox diceChoose = new HBox(this.diceLabel, this.prevDice, this.dice, this.nextDice, this.okDice, this.diceDesc);
    private final Button start = new BasicButton(LanguageStringMap.get().getMap().get(START_KEY));
    private final VBox box = new VBox(this.title, this.modes, this.chooseNumber, this.scenaryChoose, this.diceChoose, this.start);
    private boolean singleGameMode;
    private int diffCounter = 1;
    private int diceCounter = 1;

    private SetUpGame() {

        this.getDefaultLayout().setCenter(this.box);
        this.box.setAlignment(Pos.CENTER);
        this.box.setSpacing(BOX_SPACING);
        this.title.setAlignment(Pos.CENTER);
        this.title.setTranslateY(Y_TITLE_TRANSLATE);
        this.modes.setAlignment(Pos.CENTER);
        this.chooseNumber.setAlignment(Pos.CENTER);
        this.scenaryChoose.setAlignment(Pos.CENTER);
        this.diceChoose.setAlignment(Pos.CENTER);

        this.scenaryChoose.setSpacing(BOX_SPACING);
        this.diceChoose.setSpacing(BOX_SPACING);
        this.title.setFont(new Font(TITLE_FONT));
        this.howMany.setFont(new Font(FONT));
        this.scenaryLabel.setFont(new Font(FONT));
        this.diceLabel.setFont(new Font(FONT));
        this.boardDesc.setFont(new Font(FONT));
        this.diceDesc.setFont(new Font(FONT));

        this.board.setFitHeight(BOARD_SIZE);
        this.board.setFitWidth(BOARD_SIZE);

        for (int i = 2; i <= MAX_PLAYERS; i++) {
            this.nPlayer.add(new Button(String.valueOf(i)));
        }

        for (final Difficulty d: Difficulty.values()) {
            this.boardList.add(ImageManager.get().readFromFile(GameBoardTypes.get().getBoard(d)));
        }

        for (final TypesOfDice t: TypesOfDice.values()) {
            this.diceList.add(ImageManager.get().readFromFile(DiceTypes.get().getSpecificDiceMap(t).get(DEFAULT_DICE_IMAGE_INDEX)));
        }

        this.single.setOnAction(e -> {
            this.setMode(true);
            setNumPlayers(SINGLE_MODE_PLAYERS);
            this.reset();
            this.single.setDisable(true);
            this.scenaryChoose.setVisible(true);
        });

        this.multi.setOnAction(e -> {
            this.setMode(false);
            this.reset();
            this.multi.setDisable(true);
            this.chooseNumber.setVisible(true);
        });

        for (final Button b: nPlayer) {
            b.setOnAction(e -> {
                for (final Button elem: nPlayer) {
                    elem.setDisable(false);
                }
                b.setDisable(true);
                setNumPlayers(Integer.valueOf(b.getText()));
                this.scenaryChoose.setVisible(true);
            });
            this.chooseNumber.getChildren().add(b);
        }

        this.prevBoard.setOnAction(e -> {
            this.diffCounter--;
            if (this.diffCounter <= 0) {
                this.diffCounter = NUM_SCENARY;
            }
            this.board.setImage(ImageManager.get().readFromFile(GameBoardTypes.get().getBoard(this.calculateDifficulty(this.diffCounter))));
            this.updateBoardDesc(this.diffCounter);
        });

        this.nextBoard.setOnAction(e -> {
            this.diffCounter++;
            if (this.diffCounter > NUM_SCENARY) {
                this.diffCounter = 1;
            }
            this.board.setImage(ImageManager.get().readFromFile(GameBoardTypes.get().getBoard(this.calculateDifficulty(this.diffCounter))));
            this.updateBoardDesc(this.diffCounter);
        });

        this.okBoard.setOnAction(e -> {
            this.prevBoard.setDisable(true);
            this.nextBoard.setDisable(true);
            setScenary(this.diffCounter);
            this.diceChoose.setVisible(true);
        });

        this.prevDice.setOnAction(e -> {
            this.diceCounter--;
            if (this.diceCounter <= 0) {
                this.diceCounter = NUM_DICE;
            }
            this.dice.setImage(ImageManager.get().readFromFile(
                    DiceTypes.get().getSpecificDiceMap(this.calculateDice(this.diceCounter)).get(DEFAULT_DICE_IMAGE_INDEX)));
            this.updateDiceDesc(this.diceCounter);
        });

        this.nextDice.setOnAction(e -> {
            this.diceCounter++;
            if (this.diceCounter > NUM_DICE) {
                this.diceCounter = 1;
            }
            this.dice.setImage(ImageManager.get().readFromFile(
                    DiceTypes.get().getSpecificDiceMap(this.calculateDice(this.diceCounter)).get(DEFAULT_DICE_IMAGE_INDEX)));
            this.updateDiceDesc(this.diceCounter);
        });

        this.okDice.setOnAction(e -> {
            this.prevDice.setDisable(true);
            this.nextDice.setDisable(true);
            setDice(this.diceCounter);
            this.start.setVisible(true);
        });

        this.back.setOnAction(e -> {
            setUpStage.setScene(Menu.getScene(setUpStage));
        });

        this.start.setOnAction(e -> {
            if (singleGameMode) {
                SinglePlayerGame.getScene(setUpStage).setScenary(boardType);
                ViewImpl.setPlayScene(SinglePlayerGame.getScene(setUpStage));
                ViewImpl.getPlayScene().updateLanguage();
                ViewImpl.getObserver().play(numPlayers, boardType, diceType);
                setUpStage.setScene(SinglePlayerGame.getScene(setUpStage));
            } else {
                MultiPlayerScenes.get(setUpStage).insert(numPlayers);
                MultiPlayerScenes.get(setUpStage).getScene(numPlayers).setScenary(boardType);
                ViewImpl.setPlayScene(MultiPlayerScenes.get(setUpStage).getScene(numPlayers));
                ViewImpl.getPlayScene().updateLanguage();
                ViewImpl.getObserver().play(numPlayers, boardType, diceType);
                setUpStage.setScene(MultiPlayerScenes.get(setUpStage).getScene(numPlayers));
            }
        });
        this.reset();
    }

    private Difficulty calculateDifficulty(final int n) {
        switch(n) {
        case 1: return Difficulty.BEGINNER; 
        case 2: return Difficulty.EASY;
        case 3: return Difficulty.MEDIUM;
        default: return Difficulty.BEGINNER;
        }
    }

    private TypesOfDice calculateDice(final int n) {
        switch(n) {
        case 1: return TypesOfDice.CLASSIC_DICE; 
        case 2: return TypesOfDice._5_TO_10_DICE;
        case 3: return TypesOfDice.NEGATIVE_DICE;
        default: return TypesOfDice.CLASSIC_DICE;
        }
    }

    private void updateBoardDesc(final int n) {
        switch(n) {
            case 1: this.boardDesc.setText(LanguageStringMap.get().getMap().get(BEGINNER_KEY)); 
                    break;
            case 2: this.boardDesc.setText(LanguageStringMap.get().getMap().get(EASY_KEY));
                    break;
            case 3: this.boardDesc.setText(LanguageStringMap.get().getMap().get(MEDIUM_KEY));
                    break;
            default:
        }
    }

    private void updateDiceDesc(final int n) {
        switch(n) {
            case 1: this.diceDesc.setText(TypesOfDice.CLASSIC_DICE.name()); 
                    break;
            case 2: this.diceDesc.setText(TypesOfDice._5_TO_10_DICE.name());
                    break;
            case 3: this.diceDesc.setText(TypesOfDice.NEGATIVE_DICE.name());
                    break;
            default:
        }
    }

    private void setMode(final boolean b) {
        this.singleGameMode = b;
    }

    private static void setScenary(final int n) {
        switch(n) {
        case 1: boardType = Difficulty.BEGINNER; 
                break;
        case 2: boardType = Difficulty.EASY;
                break;
        case 3: boardType = Difficulty.MEDIUM;
                break;
        default:
        }
    }

    private static void setNumPlayers(final int n) {
        numPlayers = n;
    }

    private static void setDice(final int n) {
        switch(n) {
        case 1: diceType = TypesOfDice.CLASSIC_DICE;
                break;
        case 2: diceType = TypesOfDice._5_TO_10_DICE;
                break;
        case 3: diceType = TypesOfDice.NEGATIVE_DICE;
                break;
        default:
        }
    }

    /**
     * It updates the language of this scene. 
     */
    public void updateLanguage() {
        this.title.setText(LanguageStringMap.get().getMap().get(TITLE_KEY));
        this.back.setText(LanguageStringMap.get().getMap().get(BACK_KEY));
        this.single.setText(LanguageStringMap.get().getMap().get(SINGLE_KEY));
        this.multi.setText(LanguageStringMap.get().getMap().get(MULTI_KEY));
        this.howMany.setText(LanguageStringMap.get().getMap().get(HOW_MANY_KEY));
        this.scenaryLabel.setText(LanguageStringMap.get().getMap().get(SCENERY_LABEL_KEY));
        this.diceLabel.setText(LanguageStringMap.get().getMap().get(DICE_LABEL_KEY));
        this.start.setText(LanguageStringMap.get().getMap().get(START_KEY));
    }

    /**
     * It resets the settings scene.
     */
    public void reset() {
        this.chooseNumber.setVisible(false);
        this.start.setVisible(false);
        this.single.setDisable(false);
        this.multi.setDisable(false);
        for (final Button b: nPlayer) {
            b.setDisable(false);
        }
        this.scenaryChoose.setVisible(false);
        this.prevBoard.setDisable(false);
        this.nextBoard.setDisable(false);
        this.board.setImage(ImageManager.get().readFromFile(GameBoardTypes.get().getBoard(DEFAULT_DIFFICULTY)));
        this.diffCounter = DEFAULT_DIFFICULTY_INDEX;
        this.updateBoardDesc(this.diffCounter);

        this.diceChoose.setVisible(false);
        this.prevDice.setDisable(false);
        this.nextDice.setDisable(false);
        this.dice.setImage(ImageManager.get().readFromFile(DiceTypes.get().getSpecificDiceMap(DEFAULT_DICE).get(DEFAULT_DICE_IMAGE_INDEX)));
        this.diceCounter = DEFAULT_DICE_INDEX;
        this.updateDiceDesc(this.diceCounter);
    }

    /**
     * Getter of the scene.
     * @param stage
     *     The stage that will be linked to the one of this class
     * @return
     *     The set up scene
     */
    public static SetUpGame getScene(final Stage stage) {
        setUpStage = stage;
        Toolbar.setStage(stage);
        return setUpScene;
    }

    /**
     * getter of the number of players in the game.
     * @return
     *     The number of players in the game
     */
    public static int getPlayers() {
        return numPlayers;
    }

    /**
     * Getter of the board selected for this game.
     * @return
     *     The selected board 
     */
    public static Difficulty getBoardType() {
        return boardType;
    }

    /**
     * Getter of the dice selected for this game.
     * @return
     *     The selected dice 
     */
    public static TypesOfDice getSelectedDice() {
        return diceType;
    }
}
