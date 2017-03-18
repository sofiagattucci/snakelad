package view;

import java.util.Map;

import controller.ViewObserver;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import utilities.Statistic;
import utilities.enumeration.Language;
import utilities.enumeration.TypesOfItem;
import view.scenes.Menu;
import view.scenes.StatisticsScene;
import view.scenes.game.Game;
import view.scenes.settings.Settings;
import view.scenes.setup.SetUpGame;

/**
 * This is the main class of the view and implements the View Interface.
 */
public class ViewImpl implements View {

    private static final Language DEFAULT_LANGUAGE = Language.EN;
    private static final String STYLESHEET_PATH = "style.css";

    private static Menu menuScene;
    private static Stage appStage;
    private static Game playScene;
    private static SetUpGame setUpScene;
    private static Settings settingsScene;
    private static StatisticsScene statScene;
    private static ViewObserver observer;
    private static String user;

    /**
     * Constructor of this class; It sets up the observer.
     * @param obs
     *     The observer to link to this class
     */
    public ViewImpl(final ViewObserver obs) {
        this.setObserver(obs);
    }

    @Override
    public void start() {
        observer.setLanguage(DEFAULT_LANGUAGE);
        Application.launch(MainFrame.class);
    }

    /**
     * It links the main stage of the application to this class.
     * @param stage
     *     The main stage of the application
     */
    public static void setAppStage(final Stage stage) {
        appStage = stage;
    }

    /**
     * Getter of the application main stage.
     * @return
     *     The main stage of used in the application.
     */
    public static Stage getAppStage() {
        return appStage;
    }

    /**
     * It links a Play scene to this class.
     * @param scene
     *     The scene to link.
     */
    public static void setPlayScene(final Game scene) {
        playScene = scene;
    }

    /**
     * Getter of the StatisticsScene scene used in the application.
     * @return
     *     The StatisticsScene used in the application
     */
    public static StatisticsScene getStatScene() {
        return statScene;
    }

    /**
     * Setter of the statistics scene.
     * @param statistic
     *     The scene to link.
     */
    public static void setStatScene(final StatisticsScene statistic) {
        statScene = statistic;
    }

    /**
     * Setter of the set up scene.
     * @param scene
     *     The scene to link.
     */
    public static void setSetUpScene(final SetUpGame scene) {
        setUpScene = scene;
    }

    /**
     * Setter of the settings scene. It links a settings scene to this class.
     * @param scene
     *    The settings scene used in the application
     */
    public static void setSettingsScene(final Settings scene) {
        settingsScene = scene;
    }

    /**
     * Setter of the menu scene. It links a menu scene to this class.
     * @param scene
     *    The menu scene used in the application
     */
    public static void setMenuScene(final Menu scene) {
        menuScene = scene;
    }

    /**
     * Getter of the observer.
     * @return
     *     The observer linked to this class
     */
    public static ViewObserver getObserver() {
        return observer;
    }

    /**
     * Getter of the play scene.
     * @return
     *     The play scene used in the game
     */
    public static Game getPlayScene() {
        return playScene;
    }

    /**
     * Getter of the settings scene.
     * @return
     *     The settings scene used in the game
     */
    public static Settings getSettingsScene() {
        return settingsScene;
    }

    /**
     * Getter of the menu scene.
     * @return
     *     The menu scene used in the game
     */
    public static Menu getMenuScene() {
        return menuScene;
    }


    /**
     * Getter of the set up scene.
     * @return
     *     The set up scene used in the game
     */
    public static SetUpGame getSetUpScene() {
        return setUpScene;
    }

    /**
     * Getter of the default language of the game.
     * @return
     *     The default language of the game. An element of the enumeration Language
     */
    public static Language getDefaultLanguage() {
        return DEFAULT_LANGUAGE;
    }

    private void setObserver(final ViewObserver obs) {
        observer = obs;
    }

    /**
     * Getter of the the css style sheet used in the application.
     * @return
     *     The css style sheet
     */
    public static String getStylesheet() {
        return STYLESHEET_PATH;
    }

    @Override
    public void firstTurn() {
        playScene.firstTurn();
    }

    @Override
    public void updateInfo(final int newDiceValue, final int finalPosition) {
        playScene.updateInfo(newDiceValue, finalPosition);
    }

    @Override
    public void updateInfo(final int newDiceValue) {
        playScene.updateInfo(newDiceValue);
    }

    @Override
    public void setLanguageMap(final Map<String, String> map) {
        LanguageStringMap.get().setLanguage(map);
    }

    @Override
    public void setBoardSize(final int n) {
        Dimension.setElemHeight(n);
        playScene.getBoard().setSize(n);
        playScene.resizePawns();
    }

    @Override
    public void setMusicVolume(final float min, final float max, final float current) {
        Settings.getScene(appStage).getMusicManger().setSliderValues(min, max, current);
    }

    @Override
    public void putItem(final int pos, final TypesOfItem type) {
        Platform.runLater(() -> playScene.putItem(pos, type));
    }

    @Override
    public void setStatistics(final Statistic statistics) {
        statScene.setStatistics(statistics);
    }

    /**
     * Setter of the user name of the user.
     * @param name
     *     The name used by the player
     */
    public static void setUsername(final String name) {
        user = " " + name;
    }

    /**
     * Getter of the user name.
     * @return
     *     The name used by the player
     */
    public static String getUsername() {
        return user;
    }

} 