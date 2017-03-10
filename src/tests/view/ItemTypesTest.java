package tests.view;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import utilities.TypesOfItem;
import view.item.ItemTypes;

/**
 * JUnit test for the class ItemTypes.
 */
public class ItemTypesTest {

    private static final String STD_PATH = "./res/icons/";
    private static final String COIN_PATH = STD_PATH + "coin.gif";
    private static final String DIAMOND_PATH = STD_PATH + "diamond.gif";
    private static final String SKULL_PATH = STD_PATH + "skull.png";

    /**
     * Starting JUnit Tests.
     */
    @Test
    public void test() {

        assertEquals(ItemTypes.get().getClass(), ItemTypes.class);
        assertEquals(ItemTypes.get().getItem(TypesOfItem.COIN), COIN_PATH);
        assertEquals(ItemTypes.get().getItem(TypesOfItem.DIAMOND), DIAMOND_PATH);
        assertEquals(ItemTypes.get().getItem(TypesOfItem.SKULL), SKULL_PATH);
    }
}
