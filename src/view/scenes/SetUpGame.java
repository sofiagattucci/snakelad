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
    private static final String BOARD1 = "Board1";
    private static final double BOX_SPACING = BasicButton.getButtonHeight() / 3;
    private static final int MAX_PLAYERS = 5;
    private static final int FONT = 20;

    private static final int DICE = 1;

    private static Stage setUpStage;
    private static SetUpGame setUpScene = new SetUpGame();
    private static int numPlayers;
    private static int boardType;

    private final Button single = new BasicButton(SINGLE);
    private final Button multi = new BasicButton(MULTI);
    private final Button back = new BasicButton(BACK);
    private final Button start = new BasicButton(START);
    private final HBox modes = new HBox(single, multi, back);
    private final List<Button> nPlayer = new ArrayList<>();
    private final Label howMany = new Label(HOW_MANY);
    private final HBox chooseNumber = new HBox(howMany);
    private final Label scenaryLabel = new Label(SCENARY_LABEL);
    private final Button board1 = new Button(BOARD1);
    private final HBox scenaryChoose = new HBox(scenaryLabel, board1);
    private final VBox box = new VBox(modes, chooseNumber, scenaryChoose, start);
    private boolean singleGameMode;

    private SetUpGame() {

        this.getDefaultLayout().setCenter(this.box);
        this.box.setAlignment(Pos.CENTER);
        this.box.setSpacing(BOX_SPACING);
        this.modes.setAlignment(Pos.CENTER);
        this.chooseNumber.setAlignment(Pos.CENTER);
        this.scenaryChoose.setAlignment(Pos.CENTER);

        this.howMany.setFont(new Font(FONT));
        this.scenaryLabel.setFont(new Font(FONT));

        for (int i = 2; i <= MAX_PLAYERS; i++) {
            this.nPlayer.add(new Button(String.valueOf(i)));
        }

        for (final Button b: nPlayer) {
            b.setOnAction(e -> {
                for (final Button elem: nPlayer) {
                    elem.setDisable(false);
                }
                b.setDisable(true);
                this.setMode(false);
                setNumPlayers(Integer.valueOf(b.getText()));
                this.scenaryChoose.setVisible(true);
            });
            this.chooseNumber.getChildren().add(b);
        }

        this.single.setOnAction(e -> {
            this.single.setDisable(true);
            this.multi.setDisable(false);
            this.start.setVisible(false);
            for (final Button b: nPlayer) {
                b.setDisable(false);
            }
            this.chooseNumber.setVisible(false);
            this.setMode(true);
            this.scenaryChoose.setVisible(true);
        });

        this.multi.setOnAction(e -> {
            this.multi.setDisable(true);
            this.single.setDisable(false);
            this.scenaryChoose.setVisible(false);
            this.start.setVisible(false);
            this.chooseNumber.setVisible(true);
        });
        this.back.setOnAction(e -> {
            setUpStage.setScene(Menu.getScene(setUpStage));
        });

        this.board1.setOnAction(e -> {
            boardType = 1;
            this.start.setVisible(true);
        });

        this.start.setOnAction(e -> {
            if (singleGameMode) {
                ViewImpl.setPlayScene(SinglePlayerGame.getScene(setUpStage));
                ViewImpl.getObserver().play(numPlayers, boardType, DICE);
                setUpStage.setScene(SinglePlayerGame.getScene(setUpStage));
            } else {
                MultiPlayerScenes.get().insert(numPlayers);
                ViewImpl.setPlayScene(MultiPlayerScenes.get().getScene(numPlayers));
                ViewImpl.getObserver().play(numPlayers, boardType, DICE);
                setUpStage.setScene(MultiPlayerScenes.get().getScene(numPlayers));
            }
        });
        this.reset();
    }

    private void setMode(final boolean b) {
        this.singleGameMode = b;
    }

    private static void setNumPlayers(final int n) {
        numPlayers = n;
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
