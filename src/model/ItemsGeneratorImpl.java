package model;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import utilities.TypesOfItem;

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

        int itemPosition = rand.nextInt(maxPosition);

        while (occupiedPositionsList.contains(itemPosition)) {
            itemPosition = rand.nextInt(maxPosition);
        }

        final SpecialItems item = (typeOfItem == TypesOfItem.COIN) ? new Coin(itemPosition) : new Diamond(itemPosition);

        if (!item.isVisibleOnSceneryGrid()) {
            return Optional.empty();
        }

        return Optional.of(itemPosition);
    }

}
