package tests.controller;


import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import controller.GameSettings;
import controller.GameSettingsBuilder;
import utilities.enumeration.Difficulty;
import utilities.enumeration.TypesOfDice;

/**
 * JUnit test GameSettings class.
 *
 */
public class GameSettingsTest {

    private static final int NUMBER_OF_PLAYER = 6;
    private static Optional<GameSettings> settings = Optional.empty();
    private final List<TypesOfDice> dice = Arrays.asList(TypesOfDice.CLASSIC_DICE, TypesOfDice.NEGATIVE_DICE, TypesOfDice._5_TO_10_DICE);
    private final List <Difficulty> scenery = Arrays.asList(Difficulty.BEGINNER, Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HIGH);

    /**
     * Test for GameSettings.
     */
    @Test
    public void gameSettingsTest() {

        assertEquals(settings, Optional.empty());

       for (final Difficulty s : this.scenery) {
           for (int i = 1; i <= NUMBER_OF_PLAYER; i++) {
               for (final TypesOfDice d : this.dice) {
                   settings = Optional.of(new GameSettingsBuilder().numOfPlayers(i)
                           .sceneryChoose(s)
                           .diceChoose(d)
                           .build());
                   assertEquals(settings.get().getNumberOfPlayer(), i);
                   assertEquals(settings.get().getScenery(), s);
                   assertEquals(settings.get().getDice(), d);
               }
           }
       }
    }

}
