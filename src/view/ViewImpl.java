package view;

import controller.ViewObserver;
import javafx.application.Application;
import utilities.Turn;
import view.scenes.Instructions;
import view.scenes.SinglePlayerPlay;

/**
 * This is the main class of the view and implements the View Interface.
 */
public class ViewImpl implements View {

    private static SinglePlayerPlay playScene;
    private static Instructions instrScene;
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
        Application.launch(MainFrame.class);
    }

    /**
     * It links a Play scene to this class.
     * @param scene
     *     The scene to link.
     */
    public static void setPlayScene(final SinglePlayerPlay scene) {
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
    public static SinglePlayerPlay getPlayScene() {
        return playScene;
    }

    private void setObserver(final ViewObserver obs) {
        observer = obs;
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
    public void updateInfo(final Turn nextTurn, final int newDiceValue, final int finalPosition) {
        playScene.updateInfo(nextTurn, newDiceValue, finalPosition);
    }

    @Override
    public void updateInfo(final Turn nextTurn, final int newDiceValue) {
        playScene.updateInfo(nextTurn, newDiceValue);
    }
} 

