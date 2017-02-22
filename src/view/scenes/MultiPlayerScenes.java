package view.scenes;

import java.util.HashMap;
import java.util.Map;

import javafx.stage.Stage;

/**
 * It handles the different types of player versus player scenes depending on the number of players.
 */
public final class MultiPlayerScenes {

    private static Stage stage;

    private static final MultiPlayerScenes INSTANCE = new MultiPlayerScenes();
    private final Map<Integer, MultiPlayerGame> scenesMap = new HashMap<>();

    private MultiPlayerScenes() {

    }

    /**
     * Getter of this class unique instance.
     * @param st
     *     The stage where the scenes hold by this class will be placed
     * @return
     *     This class unique instance
     */
    public static MultiPlayerScenes get(final Stage st) {
        stage = st;
        return INSTANCE;
    }
 
    /**
     * It puts in the map the scene with the selected number of players.
     * @param n
     *     The number if players in the game
     */
    public void insert(final int n) {
        this.scenesMap.put(n, new MultiPlayerGame(n));
        this.scenesMap.get(n).setStage(stage);
    }

    /**
     * Getter of the scene with the selected number of players.
     * @param n
     *     The number of players
     * @return
     *     The scene selected
     */
    public Game getScene(final int n) {
        return this.scenesMap.get(n);
    }
}
