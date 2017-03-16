package tests.controller;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import controller.PathMap;
import controller.PathMapBuilder;
import utilities.enumeration.AudioTrack;
import utilities.enumeration.Difficulty;
import utilities.enumeration.TypesOfItem;

/**
 * JUnit test for class PathMap.
 *
 */
public class PathMapTest {

    /**
     * Test for PathMapClass.
     */
    @Test
    public void pathMapTest() {
        PathMap path;
        final List<TypesOfItem> item = Arrays.asList(TypesOfItem.COIN, TypesOfItem.DIAMOND, TypesOfItem.SKULL);
        final List<String> pathItem = Arrays.asList("./res/soundEffects/coin.wav", "./res/soundEffects/diamond.wav", "./res/soundEffects/skull.wav");
        final List<Difficulty> scenery = Arrays.asList(Difficulty.BEGINNER, Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HIGH);
        final List<String> pathScenery = Arrays.asList("./res/gameBoards/gameBoard1/file.txt", "./res/gameBoards/gameBoard2/file.txt", 
                "./res/gameBoards/gameBoard3/file.txt", "./res/gameBoards/gameBoard4/file.txt");
        final List<AudioTrack> song = Arrays.asList(AudioTrack.SNAKELAD, AudioTrack.CAVE_OF_DRAGONS);
        final List<String> pathSong = Arrays.asList("./res/music/Snakelad.wav", "./res/music/ID.wav");

        //test map of items path
        for (final TypesOfItem i : item) {
            for (final String s : pathItem) {
                path = new PathMapBuilder().itemClipMap(i, s).build();
                assertEquals(path.getClipMap().get(i), s);
            }
        }

        //test map of scenery path
        for (final Difficulty d : scenery) {
            for (final String s : pathScenery) {
                path = new PathMapBuilder().sceneryMap(d, s).build();
                assertEquals(path.getSceneryMap().get(d), s);
            }
        }

        //test map of song path
        for (final AudioTrack a : song) {
            for (final String s : pathSong) {
                path = new PathMapBuilder().songMap(a, s).build();
                assertEquals(path.getSongMap().get(a), s);
            }
        }
    }

}
