package view.scenes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utilities.Pair;
import utilities.Statistic;
import utilities.StatisticImpl;
import view.BasicButton;
import view.LanguageStringMap;
import view.ViewImpl;

/**
 * This class creates and initializes the statistics scene.
 */
public final class StatisticsScene extends BasicScene {

    private static final String BACK_KEY = "back";
    private static final String SCORES_KEY = "Scores";
    private static final String WIN_KEY = "Win";
    private static final String LOSE_KEY = "Lose";
    private static final String DICE_KEY = "Dice";
    private static final String TITLE_KEY = "Statistics";
    private static final int N_ELEM = 4;
    private static final double BOX_SPACING = BasicButton.getButtonHeight() / 3;
    private static final int TITLE_FONT_SIZE = 60;
    private static final int SMALL_FONT_SIZE = 20;

    private static StatisticsScene statisticScene = new StatisticsScene();
    private static Stage statisticStage;

    private final Button back = new BasicButton(LanguageStringMap.get().getMap().get(BACK_KEY));
    private final List<Pair<Label, Label>> statElem = new ArrayList<>();
    private final GridPane grid = new GridPane();
    private final Label title = new Label(TITLE_KEY);
    private final VBox box = new VBox(this.title, this.grid, this.back);
    private final Font f = new Font(SMALL_FONT_SIZE);

    private StatisticsScene() {

        ViewImpl.setStatScene(this);
        this.getDefaultLayout().setCenter(this.box);
        this.box.setAlignment(Pos.CENTER);
        this.box.setSpacing(BOX_SPACING);
        this.grid.setAlignment(Pos.CENTER);
        this.grid.setVgap(BOX_SPACING);
        this.grid.setHgap(BOX_SPACING);
        this.title.setFont(new Font(TITLE_FONT_SIZE));
        this.back.setOnAction(e -> statisticStage.setScene(Menu.getScene(statisticStage)));
        Stream.generate(() -> new Pair<Label, Label>(new Label(), new Label()))
              .limit(N_ELEM)
              .forEach(this.statElem :: add);
        IntStream.range(0, this.statElem.size())
                 .forEach(i -> {
                     this.statElem.get(i).getFirst().setFont(f);
                     this.statElem.get(i).getSecond().setFont(f);
                     this.grid.addRow(i, this.statElem.get(i).getFirst(), this.statElem.get(i).getSecond());
                 });
        //this.setStatistics(new StatisticImpl.Builder().scores(1).gameLost(2).gameWon(3).numberOfDiceRoll(4).build());
        this.statElem.get(0).getFirst().setText(SCORES_KEY);
        this.statElem.get(1).getFirst().setText(WIN_KEY);
        this.statElem.get(2).getFirst().setText(LOSE_KEY);
        this.statElem.get(3).getFirst().setText(DICE_KEY);
    }

    /**
     * It updates the language of this scene.
     */
    public void updateLanguage() {
        this.back.setText(LanguageStringMap.get().getMap().get(BACK_KEY));
    }

    /**
     * Setter of the statistics shown in the GUI.
     * @param stat
     *     An object Statistic which contains the statistics to be used
     */
    public void setStatistics(final Statistic stat) {
        this.statElem.get(0).getSecond().setText(String.valueOf(stat.getScores()));
        this.statElem.get(1).getSecond().setText(String.valueOf(stat.getGamesWon()));
        this.statElem.get(2).getSecond().setText(String.valueOf(stat.getGamesLost()));
        this.statElem.get(3).getSecond().setText(String.valueOf(stat.getNumberOfDiceRoll()));
    }

    /**
     * Getter of this scene unique instance.
     * @param st
     *     The main stage of the application
     * @return
     *     The Statistic unique instance scene 
     */
    public static StatisticsScene getScene(final Stage st) {
        statisticStage = st;
        return statisticScene;
    }
}
