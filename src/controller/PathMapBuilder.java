package controller;

import java.util.HashMap;
import java.util.Map;

import utilities.enumeration.AudioTrack;
import utilities.enumeration.Difficulty;
import utilities.enumeration.TypesOfItem;

/**
 * Builder for class PathMap.
 *
 */
public class PathMapBuilder {

    private final Map<TypesOfItem, String> clipPath;
    private final Map<Difficulty, String> scenery;
    private final Map<AudioTrack, String> song;

    /**
     * Constructor.
     */
    public PathMapBuilder() {
        this.clipPath = new HashMap<>();
        this.scenery = new HashMap<>();
        this.song = new HashMap<>();
    }

    /**
     * Put in the map of clip, the enumeration TypeOfItem and the associated path.
     * @param item
     *          the item of enumeration TypeOfItem
     * @param path
     *          the associated path
     * @return the Builder
     */
    public PathMapBuilder itemClipMap(final TypesOfItem item, final String path) {
        this.clipPath.put(item, path);
        return this;
    }

    /**
     * Put in the map of scenery, the enumeration Difficulty and the associated path.
     * @param scenery
     *          the item of enumeration Difficulty
     * @param path
     *          the associated path
     * @return the Builder
     */
    public PathMapBuilder sceneryMap(final Difficulty scenery, final String path) {
        this.scenery.put(scenery, path);
        return this;
    }

    /**
     * Put in the map of song, the enumeration AudioTrack and the associated path.
     * @param song
     *          the song of enumeration AudioTrack
     * @param path
     *          the associated path
     * @return the Builder
     */
    public PathMapBuilder songMap(final AudioTrack song, final String path) {
        this.song.put(song, path);
        return this;
    }

    /**
     * Build the PathMap.
     * @return an instance of PathMap
     */
    public PathMap build() {
        return new PathMap(this.clipPath, this.scenery, this.song);
    }

}
