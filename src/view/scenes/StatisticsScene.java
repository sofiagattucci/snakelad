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
import view.BasicButton;
import view.LanguageStringMap;
import view.ViewImpl;

/**
 * This class creates and initializes the statistics scene.
 */
public final class StatisticsScene extends BasicScene {

    private static final String BACK_KEY = "back";
    private static final String SCORES_KEY = "statistics.score";
    private static final String WIN_KEY = "statistics.win";
    private static final String LOSE_KEY = "statistics.lose";
    private static final String DICE_KEY = "statistics.dice";
    private static final String TITLE_KEY = "statistics.title";
    private static final int N_ELEM = 4;
    private static final double BOX_SPACING = BasicButton.getButtonHeight() / 3;
    private static final int TITLE_FONT_SIZE = 60;
    private static final int SMALL_FONT_SIZE = 20;

    private static StatisticsScene statisticScene = new StatisticsScene();
    private static Stage statisticStage;

    private final Button back = new BasicButton(LanguageStringMap.get().getMap().get(BACK_KEY));
    private final List<Pair<Label, Label>> statElem = new ArrayList<>();
    private final GridPane grid = new GridPane();
    private final Label title = new Label(LanguageStringMap.get().getMap().get(TITLE_KEY));
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
        this.setLabelText(0, SCORES_KEY);
        this.setLabelText(1, WIN_KEY);
        this.setLabelText(2, LOSE_KEY);
        this.setLabelText(3, DICE_KEY);
    }

    private void setLabelText(final int index, final String key) {
        this.statElem.get(index).getFirst().setText(LanguageStringMap.get().getMap().get(key));
    }

    /**
     * It updates the language of the elements of this scene.
     */
    public void updateLanguage() {
        this.back.setText(LanguageStringMap.get().getMap().get(BACK_KEY));
        this.title.setText(LanguageStringMap.get().getMap().get(TITLE_KEY));
        this.setLabelText(0, SCORES_KEY);
        this.setLabelText(1, WIN_KEY);
        this.setLabelText(2, LOSE_KEY);
        this.setLabelText(3, DICE_KEY);
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
