package controller;

import java.util.HashMap;
import java.util.Map;

import utilities.enumeration.AudioTrack;
import utilities.enumeration.Difficulty;
import utilities.enumeration.TypesOfItem;

/**
 * Class for build the Map of path.
 *
 */
public class PathMap {

    private final Map<TypesOfItem, String> clipPath;
    private final Map<Difficulty, String> scenery;
    private final Map<AudioTrack, String> song;

    /**
     * Constructor.
     * @param clipPath
     *          the map contain the clip path
     * @param scenery
     *          the map contain the scenery path
     * @param song
     *          the map contain the song path
     */
    public PathMap(final Map<TypesOfItem, String> clipPath, final Map<Difficulty, String> scenery, final Map<AudioTrack, String> song) {
        this.clipPath = new HashMap<>();
        this.clipPath.putAll(clipPath);
        this.scenery = new HashMap<>();
        this.scenery.putAll(scenery);
        this.song = new HashMap<>();
        this.song.putAll(song);
    }

    /**
     * Getter for clip map.
     * @return the map of clip path
     */
    public Map<TypesOfItem, String> getClipMap() {
        return this.clipPath;
    }

    /**
     * Getter for scenery map.
     * @return the map of scenery path
     */
    public Map<Difficulty, String> getSceneryMap() {
        return this.scenery;
    }

    /**
     * Getter for song map.
     * @return the map of song path
     */
    public Map<AudioTrack, String> getSongMap() {
        return this.song;
    }

}
