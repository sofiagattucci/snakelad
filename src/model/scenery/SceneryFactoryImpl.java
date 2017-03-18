package model.scenery;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implements a factory for the game scenery.
 */
public class SceneryFactoryImpl implements SceneryFactory {

    private static final int SEPARATOR = 0;

    private final Scenery scenery;

    /**
     * SceneryBuilderImpl constructor.
     */
    public SceneryFactoryImpl() {
        this.scenery = SceneryImpl.get();
    }

    private Map<Integer, Integer> fillMap(final List<Integer> list) {
        final Iterator<Integer> iterator = list.iterator();
        final Map<Integer, Integer> map = new HashMap<>();

        while (iterator.hasNext()) {
            final Integer key = iterator.next();
            final Integer value = iterator.next();
            map.put(key, value);
        }

        return map;
    }

    private List<Integer> findSnakesList(final List<Integer> dataList) {
        final List<Integer> list = dataList.stream()
                                           .limit(dataList.indexOf(SEPARATOR))
                                           .collect(Collectors.toList());
        return Collections.unmodifiableList(list);
    }

    private List<Integer> findLaddersList(final List<Integer> dataList) {
        final List<Integer> list = dataList.stream()
                                           .skip(dataList.indexOf(SEPARATOR) + 1)
                                           .collect(Collectors.toList());
        //remove the separator represented by the last element of the list
        list.remove(list.size() - 1);
        return Collections.unmodifiableList(list);
    }

    @Override
    public Scenery createScenery(final List<Integer> data) {

        final List<Integer> dataList = new LinkedList<>(data);

        //get the first number from dataList. It represents the number of boxes in the scenery
        this.scenery.setNumberOfBoxes(dataList.get(0));

        //remove the first two elements in dataList
        dataList.remove(0);
        dataList.remove(0);
        //fill snakesMap with snakes positions
        this.scenery.setSnakesMap(this.fillMap(this.findSnakesList(dataList)));
        //fill laddersMap with ladders positions
        this.scenery.setLaddersMap(this.fillMap(this.findLaddersList(dataList)));

        return this.scenery;
    }

}
