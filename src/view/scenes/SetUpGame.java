package view.scenes;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import view.BasicButton;
import view.Dimension;
import view.Toolbar;
import view.ViewImpl;

/**
 * It's the scene shown when the user pushes the play button. It manages the settings to use for this game. 
 */
public final class SetUpGame extends BasicScene {

    private static final String SINGLE = "Single player";
    private static final String MULTI = "Multiplayer";
    private static final String BACK = "Back";
    private static final String START = "Start";
    private static final String HOW_MANY = "How many players: ";
    private static final String SCENARY_LABEL = "Select the game board to use: ";
    private static final String DICE_LABEL = "Select the dice to use: ";
    private static final String TITLE = "Custom your game:";
    private static final double BOX_SPACING = BasicButton.getButtonHeight() / 3;
    private static final int MAX_PLAYERS = 6;
    private static final int NUM_SCENARY = 1;
    private static final int NUM_DICE = 1;
    private static final int FONT = 20;
    private static final int TITLE_FONT = 65;
    private static final double Y_TITLE_TRANSLATE = -Dimension.SCREEN_H / 10; 

    private static Stage setUpStage;
    private static SetUpGame setUpScene = new SetUpGame();
    private static int numPlayers;
    private static int boardType;
    private static int diceType;

    private final Label title = new Label(TITLE);
    private final Button single = new BasicButton(SINGLE);
    private final Button multi = new BasicButton(MULTI);
    private final Button back = new BasicButton(BACK);
    private final HBox modes = new HBox(single, multi, back);
    private final List<Button> nPlayer = new ArrayList<>();
    private final Label howMany = new Label(HOW_MANY);
    private final HBox chooseNumber = new HBox(howMany);
    private final List<Button> boardList = new ArrayList<>();
    private final Label scenaryLabel = new Label(SCENARY_LABEL);
    private final HBox scenaryChoose = new HBox(scenaryLabel);
    private final List<Button> diceList = new ArrayList<>();
    private final Label diceLabel = new Label(DICE_LABEL);
    private final HBox diceChoose = new HBox(diceLabel);
    private final Button start = new BasicButton(START);
    private final VBox box = new VBox(title, modes, chooseNumber, scenaryChoose, diceChoose, start);
    private boolean singleGameMode;

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

        this.title.setFont(new Font(TITLE_FONT));
        this.howMany.setFont(new Font(FONT));
        this.scenaryLabel.setFont(new Font(FONT));
        this.diceLabel.setFont(new Font(FONT));

        for (int i = 2; i <= MAX_PLAYERS; i++) {
            this.nPlayer.add(new Button(String.valueOf(i)));
        }

        for (int i = 1; i <= NUM_SCENARY; i++) {
            this.boardList.add(new Button(String.valueOf(i)));
        }

        for (int i = 1; i <= NUM_DICE; i++) {
            this.diceList.add(new Button(String.valueOf(i)));
        }

        this.single.setOnAction(e -> {
            this.setMode(true);
            this.single.setDisable(true);
            this.multi.setDisable(false);
            for (final Button b: boardList) {
                b.setDisable(false);
            }
            this.chooseNumber.setVisible(false);
            this.scenaryChoose.setVisible(true);
            this.diceChoose.setVisible(false);
            this.start.setVisible(false);
        });

        this.multi.setOnAction(e -> {
            this.setMode(false);
            this.multi.setDisable(true);
            this.single.setDisable(false);
            for (final Button b: nPlayer) {
                b.setDisable(false);
            }
            this.chooseNumber.setVisible(true);
            this.scenaryChoose.setVisible(false);
            this.diceChoose.setVisible(false);
            this.start.setVisible(false);
        });

        for (final Button b: nPlayer) {
            b.setOnAction(e -> {
                for (final Button elem: nPlayer) {
                    elem.setDisable(false);
                }
                b.setDisable(true);
                setNumPlayers(Integer.valueOf(b.getText()));
                for (final Button elem: boardList) {
                    elem.setDisable(false);
                }
                this.scenaryChoose.setVisible(true);
            });
            this.chooseNumber.getChildren().add(b);
        }

        for (final Button b: boardList) {
            b.setOnAction(e -> {
                for (final Button elem: boardList) {
                    elem.setDisable(false);
                }
                b.setDisable(true);
                setScenary(Integer.valueOf(b.getText()));
                for (final Button elem: diceList) {
                    elem.setDisable(false);
                }
                this.diceChoose.setVisible(true);
            });
            this.scenaryChoose.getChildren().add(b);
        }

        for (final Button b: diceList) {
            b.setOnAction(e -> {
                for (final Button elem: diceList) {
                    elem.setDisable(false);
                }
                b.setDisable(true);
                setDice(Integer.valueOf(b.getText()));
                this.start.setVisible(true);
            });
            this.diceChoose.getChildren().add(b);
        }

        this.back.setOnAction(e -> {
            setUpStage.setScene(Menu.getScene(setUpStage));
        });

        this.start.setOnAction(e -> {
            if (singleGameMode) {
                ViewImpl.setPlayScene(SinglePlayerGame.getScene(setUpStage));
                ViewImpl.getObserver().play(numPlayers, boardType, diceType);
                setUpStage.setScene(SinglePlayerGame.getScene(setUpStage));
            } else {
                MultiPlayerScenes.get().insert(numPlayers);
                ViewImpl.setPlayScene(MultiPlayerScenes.get().getScene(numPlayers));
                ViewImpl.getObserver().play(numPlayers, boardType, diceType);
                setUpStage.setScene(MultiPlayerScenes.get().getScene(numPlayers));
            }
        });
        this.reset();
    }

    private void setMode(final boolean b) {
        this.singleGameMode = b;
    }

    private static void setScenary(final int n) {
        boardType = n;
    }

    private static void setNumPlayers(final int n) {
        numPlayers = n;
    }

    private static void setDice(final int n) {
        diceType = n;
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
        for (final Button b: boardList) {
            b.setDisable(false);
        }
        this.diceChoose.setVisible(false);
        for (final Button b: diceList) {
            b.setDisable(false);
        }
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
    public static int getBoardType() {
        return boardType;
    }
}
