package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Defines a game's scenery which is composed of boxes, snakes and ladders maps.
 * It's designed using Singleton pattern.
 */
public final class SceneryImpl implements Scenery {

    private static final Supplier<RuntimeException> ILLEGAL_ARG_EXCEPTION_SUPPLIER = () -> new IllegalArgumentException();
    private static final Supplier<RuntimeException> ILLEGAL_STATE_EXCEPTION_SUPPLIER = () -> new IllegalStateException();
    private static final SceneryImpl SINGLETON = new SceneryImpl();

    //The number of boxes in the game board.
    private int numberOfBoxes;
    //Map which contains positions of all snakes in the scerery.
    private final Map<Integer, Integer> snakesMap = new HashMap<>();
    //Map which contains positions of all ladders in the scenery.
    private final Map<Integer, Integer> laddersMap = new HashMap<>();

    //private constructor
    private SceneryImpl() {
        this.numberOfBoxes = 0;
    }

    /**
     * Static method which returns the SceneryImpl unique instance.
     * @return the SceneryImpl unique instance.
     */
    public static Scenery get() {
        return SceneryImpl.SINGLETON;
    }

    @Override
    public void setNumberOfBoxes(final int numberOfBoxes) throws IllegalArgumentException {
        if (numberOfBoxes <= 0) {
            throw ILLEGAL_ARG_EXCEPTION_SUPPLIER.get();
        }

        this.numberOfBoxes = numberOfBoxes;
    }

    @Override
    public void setSnakesMap(final Map<Integer, Integer> snakesMap) throws IllegalArgumentException {
        if (snakesMap.isEmpty()) {
            throw ILLEGAL_ARG_EXCEPTION_SUPPLIER.get();
        }

        this.snakesMap.clear();
        this.snakesMap.putAll(snakesMap);
    }

    @Override
    public void setLaddersMap(final Map<Integer, Integer> laddersMap) throws IllegalArgumentException {
        if (laddersMap.isEmpty()) {
            throw ILLEGAL_ARG_EXCEPTION_SUPPLIER.get();
        }

        this.laddersMap.clear();
        this.laddersMap.putAll(laddersMap);
    }

    @Override
    public int getNumberOfBoxes() throws IllegalStateException {
        if (this.numberOfBoxes == 0) {
            throw ILLEGAL_STATE_EXCEPTION_SUPPLIER.get();
        }

        return this.numberOfBoxes;
    }

    @Override
    public Map<Integer, Integer> getSnakesMap() throws IllegalStateException {
        if (this.snakesMap.isEmpty()) {
            throw ILLEGAL_STATE_EXCEPTION_SUPPLIER.get();
        }

        return Collections.unmodifiableMap(this.snakesMap);
    }

    @Override
    public Map<Integer, Integer> getLaddersMap() throws IllegalStateException {
        if (this.laddersMap.isEmpty()) {
            throw ILLEGAL_STATE_EXCEPTION_SUPPLIER.get();
        }

        return Collections.unmodifiableMap(this.laddersMap);
    }

    @Override
    public int getSideSize() {
        final Double doubleValue = new Double(Math.sqrt(this.getNumberOfBoxes() + 1));
        return doubleValue.intValue();
    }

    @Override
    public void clearMaps() {
        this.snakesMap.clear();
        this.laddersMap.clear();
    }

}
