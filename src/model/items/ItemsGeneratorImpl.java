package model.items;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import utilities.enumeration.TypesOfItem;

/**
 * Represents an items generator. 
 * It encapsulates and decouples the algorithm to generate items from ModelImpl class.
 * It's designed using Strategy Pattern and Singleton Pattern.
 */
public final class ItemsGeneratorImpl implements ItemsGenerator {

    private static final ItemsGeneratorImpl SINGLETON = new ItemsGeneratorImpl();

    private final Random rand = new Random();

    //private constructor
    private ItemsGeneratorImpl() {

    }

    /**
     * Static method which returns the ItemsGeneratorImpl unique instance.
     * @return the ItemsGeneratorImpl unique instance.
     */
    public static ItemsGeneratorImpl get() {
        return ItemsGeneratorImpl.SINGLETON;
    }

    @Override
    public Optional<Integer> tryGenerateItem(final int maxPosition, final List<Integer> occupiedPositionsList,
                                             final TypesOfItem typeOfItem) {

        int itemPosition;
        do {
            itemPosition = rand.nextInt(maxPosition);
        } while (occupiedPositionsList.contains(itemPosition));

        final SpecialItems item = (typeOfItem == TypesOfItem.COIN) ? new Coin(itemPosition) 
                                  : (typeOfItem == TypesOfItem.DIAMOND) ? new Diamond(itemPosition) : new Skull(itemPosition);

        if (!item.isVisibleOnSceneryGrid()) {
            return Optional.empty();
        }

        return Optional.of(itemPosition);
    }

}
