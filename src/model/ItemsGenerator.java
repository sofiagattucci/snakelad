package model;

import java.util.List;
import java.util.Optional;

import utilities.TypesOfItem;

/**
 * Interface which represents an items generator. It encapsulates and decouples the 
 * algorithm to generate items from ModelImpl class.
 * It's designed using Strategy Pattern. 
 */
public interface ItemsGenerator {

    /**
     * Tries to generate a game's item, returning the Optional which describes the item's position 
     * on the scenery's grid.
     * @param maxPosition
     *                  The maximum position in which generate an item.
     * @param occupiedPositionsList
     *                  A list that indicates the positions which are already occupied by another
     *                  item in the scenery's grid.
     * @param typeOfItem
     *                  A type of item to be generated.
     * @return an Optional<Integer> if the item has decided to appear on the scenery's grid and the 
     * Integer represents the item's position, an Optional<Empty> if the item has decided not to appear.
     */
    Optional<Integer> tryGenerateItem(int maxPosition, List<Integer> occupiedPositionsList, TypesOfItem typeOfItem);

}
