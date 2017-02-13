package view;

import controller.ViewObserver;
import javafx.application.Application;
import view.scenes.Instructions;
import view.scenes.Play;

/**
 * This is the main class of the view and implements the View Interface.
 */
public class ViewImpl implements View {

    private static Play playScene;
    private static Instructions instrScene;
    private static ViewObserver observer;

    /**
     * Constructor of this class; It sets up the observer.
     * @param obs
     *     The observer to link to this class
     */
    public ViewImpl(final ViewObserver obs) {
        setObserver(obs);
    }

    @Override
    public void start() {
        Application.launch(MainFrame.class);
    }

    /**
     * It links a Play scene to this class.
     * @param scene
     *     The scene to link.
     */
    public static void setPlayScene(final Play scene) {
        playScene = scene;
    }

    /**
     * It links a Play scene to this class.
     * @param scene
     *     The scene to link.
     */
    public static void setInstrScene(final Instructions scene) {
        instrScene = scene;
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
    public static Play getPlayScene() {
        return playScene;
    }

    private static void setObserver(final ViewObserver obs) {
        observer = obs;
    }

    @Override
    public void showTurn(final String turn) {
        //playScene.setTurn(turn);
    }

    @Override
    public void setInstructions(final String text) {
        instrScene.setInstructions(text);
    }

    @Override
    public void firstTurn() {
        playScene.firstTurn();
    }

    @Override
    public void updateInfo(final String turn, final int newDiceValue, final int finalPosition) {
        playScene.updateInfo(turn, newDiceValue, finalPosition);
    }

    @Override
    public void updateInfo(final String turn, final int newDiceValue) {
        playScene.updateInfo(turn, newDiceValue);
    }
} 

