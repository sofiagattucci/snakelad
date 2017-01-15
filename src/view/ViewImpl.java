package view;

import controller.ViewObserver;
import javafx.application.Application;

/**
 * This is the main class of the view and implements the View Interface.
 */
public class ViewImpl implements View {

    private static Play playScene;
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
    public void setDiceValue(final int value) {
        playScene.updateDiceValue(value);
    }

    @Override
    public void start() {
        Application.launch(MainFrame.class);
    }

    /*Package visibility*/
    /**
     * It links a Play scene to this class.
     * @param scene
     *     The scene to link.
     */
    protected static void setPlayScene(final Play scene) {
        playScene = scene;
    }

    /*Package visibility*/
    /**
     * Getter of the observer.
     * @return
     *     The observer linked to this class
     */
    protected static ViewObserver getObserver() {
        return observer;
    }

    private static void setObserver(final ViewObserver obs) {
        observer = obs;
    }
    @Override
    public void showTurn(final String turn) {
        playScene.setTurn(turn);
    }
} 

