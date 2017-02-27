package view;

import java.util.Map;
import controller.ViewObserver;
import javafx.application.Application;
import utilities.Language;
import view.scenes.game.Game;
import view.scenes.settings.Settings;
import view.scenes.setup.SetUpGame;

/**
 * This is the main class of the view and implements the View Interface.
 */
public class ViewImpl implements View {

    private static final Language DEFAULT_LANGUAGE = Language.EN;

    private static Game playScene;
    private static SetUpGame setUpScene;
    private static Settings settingsScene;
    private static ViewObserver observer;

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
     * It links a Play scene to this class.
     * @param scene
     *     The scene to link.
     */
    public static void setPlayScene(final Game scene) {
        playScene = scene;
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

    }
} 

