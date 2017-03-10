package view.scenes.setup;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utilities.enumeration.Difficulty;
import utilities.enumeration.TypesOfDice;
import utilities.enumeration.GameMode;
import view.BasicButton;
import view.Dimension;
import view.LanguageStringMap;
import view.ViewImpl;
import view.scenes.BasicScene;
import view.scenes.Menu;
import view.scenes.game.MultiPlayerGameScenes;
import view.scenes.game.SinglePlayerGame;
import view.scenes.game.Toolbar;

/**
 * It's the scene shown when the user pushes the play button. It manages the settings to use for this game. 
 */
public final class SetUpGame extends BasicScene {

    private static final String SINGLE_KEY = "setUp.single";
    private static final String MULTI_KEY = "setUp.multi";
    private static final String BACK_KEY = "back";
    private static final String START_KEY = "setUp.start";
    private static final String HOW_MANY_KEY = "setUp.selectPlayers";
    private static final String TITLE_KEY = "setUp.title";
    private static final double BOX_SPACING = BasicButton.getButtonHeight() / 3;
    private static final int MAX_PLAYERS = 6;
    private static final int FONT = 20;
    private static final int TITLE_FONT = 65;
    private static final double Y_TITLE_TRANSLATE = -Dimension.SCREEN_H / 20; 
    private static final int SINGLE_MODE_PLAYERS = 2;

    private static Stage setUpStage;
    private static SetUpGame setUpScene = new SetUpGame();
    private static int numPlayers;
    private static Difficulty boardType;
    private static TypesOfDice diceType;

    private final Label title = new Label(LanguageStringMap.get().getMap().get(TITLE_KEY));
    private final Button single = new BasicButton(LanguageStringMap.get().getMap().get(SINGLE_KEY));
    private final Button multi = new BasicButton(LanguageStringMap.get().getMap().get(MULTI_KEY));
    private final Button back = new BasicButton(LanguageStringMap.get().getMap().get(BACK_KEY));
    private final HBox modes = new HBox(this.single, this.multi, this.back);
    private final List<Button> nPlayer = new ArrayList<>();
    private final Label howMany = new Label(LanguageStringMap.get().getMap().get(HOW_MANY_KEY));
    private final HBox chooseNumber = new HBox(this.howMany);
    private final Button start = new BasicButton(LanguageStringMap.get().getMap().get(START_KEY));
    private final DiceCircularList dice = new DiceCircularList(this.start);
    private final HBox diceChoose = new HBox();
    private final BoardCircularList board = new BoardCircularList(this.diceChoose);
    private final HBox scenaryChoose = new HBox();
    private final VBox box = new VBox(this.title, this.modes, this.chooseNumber, this.scenaryChoose, this.diceChoose, this.start);
    private boolean singleGameMode;

    private SetUpGame() {

        this.getDefaultLayout().setCenter(this.box);
        this.box.setAlignment(Pos.CENTER);
        this.box.setSpacing(BOX_SPACING);
        this.modes.setSpacing(BOX_SPACING);
        this.title.setAlignment(Pos.CENTER);
        this.title.setTranslateY(Y_TITLE_TRANSLATE);
        this.modes.setAlignment(Pos.CENTER);
        this.chooseNumber.setAlignment(Pos.CENTER);
        this.chooseNumber.setSpacing(BOX_SPACING / 8);
        this.scenaryChoose.setAlignment(Pos.CENTER);
        this.diceChoose.setAlignment(Pos.CENTER);
        this.getStylesheets().add(ViewImpl.getStylesheet());

        this.scenaryChoose.getChildren().addAll(this.board.getNodes());
        this.diceChoose.getChildren().addAll(this.dice.getNodes());

        this.scenaryChoose.setSpacing(BOX_SPACING);
        this.diceChoose.setSpacing(BOX_SPACING);
        this.title.setFont(new Font(TITLE_FONT));
        this.howMany.setFont(new Font(FONT));

        IntStream.range(2, MAX_PLAYERS + 1)
                 .forEach(i -> this.nPlayer.add(new Button(String.valueOf(i))));

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

        this.back.setOnAction(e -> {
            setUpStage.setScene(Menu.getScene(setUpStage));
        });

        this.start.setOnAction(e -> {
            if (singleGameMode) {
                SinglePlayerGame.getScene(setUpStage).updateScene(this.board.getParameterValue(), this.dice.getParameterValue());
                ViewImpl.setPlayScene(SinglePlayerGame.getScene(setUpStage));
                ViewImpl.getPlayScene().updateLanguage();
                ViewImpl.getObserver().play(numPlayers, this.board.getParameterValue(), diceType, GameMode.SINGLE_PLAYER);
                setUpStage.setScene(SinglePlayerGame.getScene(setUpStage));
            } else {
                MultiPlayerGameScenes.get(setUpStage).insert(numPlayers);
                MultiPlayerGameScenes.get(setUpStage).getScene(numPlayers).updateScene(
                       this.board.getParameterValue(), this.dice.getParameterValue());
                ViewImpl.setPlayScene(MultiPlayerGameScenes.get(setUpStage).getScene(numPlayers));
                ViewImpl.getPlayScene().updateLanguage();
                ViewImpl.getObserver().play(numPlayers, this.board.getParameterValue(), diceType, GameMode.MULTIPLAYER);
                setUpStage.setScene(MultiPlayerGameScenes.get(setUpStage).getScene(numPlayers));
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
     * It updates the language of this scene. 
     */
    public void updateLanguage() {
        this.title.setText(LanguageStringMap.get().getMap().get(TITLE_KEY));
        this.back.setText(LanguageStringMap.get().getMap().get(BACK_KEY));
        this.single.setText(LanguageStringMap.get().getMap().get(SINGLE_KEY));
        this.multi.setText(LanguageStringMap.get().getMap().get(MULTI_KEY));
        this.howMany.setText(LanguageStringMap.get().getMap().get(HOW_MANY_KEY));
        this.board.updateLanguage();
        this.dice.updateLanguage();
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
        this.board.reset();
        this.diceChoose.setVisible(false);
        this.dice.reset();
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
     * Setter of the board selected for this game.
     * @param b
     *     The board to put as the selected one 
     */
    public static void setBoardType(final Difficulty b) {
        boardType = b;
    }

    /**
     * Getter of the dice selected for this game.
     * @return
     *     The selected dice 
     */
    public static TypesOfDice getSelectedDice() {
        return diceType;
    }

    /**
     * Setter of the dice selected for this game.
     * @param d
     *     The dice to put as the selected one 
     */
    public static void setDiceType(final TypesOfDice d) {
        diceType = d;
    }
}
