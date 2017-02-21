package view.scenes;

import java.util.HashMap;
import java.util.Map;

/**
 * It handles the different types of player versus player scenes depending on the number of players.
 */
public final class MultiPlayerScenes {

    private static final MultiPlayerScenes INSTANCE = new MultiPlayerScenes();
    private final Map<Integer, Game> scenesMap = new HashMap<>();

    private MultiPlayerScenes() {

    }

    /**
     * Getter of this class unique instance.
     * @return
     *     This class unique instance
     */
    public static MultiPlayerScenes get() {
        return INSTANCE;
    }
 
    /**
     * It puts in the map the scene with the selected number of players.
     * @param n
     *     The number if players in the game
     */
    public void insert(final int n) {
        this.scenesMap.put(n, new MultiPlayerGame(n));
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
