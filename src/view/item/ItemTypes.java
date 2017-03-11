package view.item;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import utilities.Pair;
import utilities.enumeration.TypesOfItem;

/**
 * It manages the different types of items available in the game.
 */
public final class ItemTypes {

    private static final int N_ITEMS = TypesOfItem.values().length;
    private static final String STD_PATH = "./res/icons/";
    private static final String COIN_PATH = STD_PATH + "coin.gif";
    private static final String DIAMOND_PATH = STD_PATH + "diamond.gif";
    private static final String SKULL_PATH = STD_PATH + "skull.png";

    private static final ItemTypes INSTANCE = new ItemTypes();
    private final Map<TypesOfItem, String> itemsMap;

    private ItemTypes() {

        this.itemsMap = IntStream.range(1, N_ITEMS + 1)
                .boxed()
                .collect(Collectors.toMap(i -> this.calculateEntry(i).getFirst(), i -> this.calculateEntry(i).getSecond()));
    }

    private Pair<TypesOfItem, String> calculateEntry(final int i) {
        switch(i) {
            case 1: return new Pair<>(TypesOfItem.COIN, COIN_PATH);
            case 2: return new Pair<>(TypesOfItem.DIAMOND, DIAMOND_PATH);
            case 3: return new Pair<>(TypesOfItem.SKULL, SKULL_PATH);
            default: return new Pair<>(TypesOfItem.COIN, COIN_PATH);
        }
    }

    /**
     * It select the item image to use.
     * @param t
     *     The item to use (enumeration element)
     * @return
     *     The path to the item image 
     */
    public String getItem(final TypesOfItem t) {
        return this.itemsMap.get(t);
    }


    /**
     * Getter of this class unique instance.
     * @return
     *     This class unique instance
     */
    public static ItemTypes get() {
        return INSTANCE;
    }
}
