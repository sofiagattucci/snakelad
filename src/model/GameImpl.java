package model;

import model.entities.ClassicDice;
import model.entities.Dice;

/**
 * This is the main class of the model in MVC pattern.
 * It represents the game overall, with all his entities and main features.
 */
public class GameImpl implements Game {

    private final Dice dice;

    /**
     * GameImpl constructor.
     */
    public GameImpl() {
        this.dice = new ClassicDice();
    }

    @Override
    public void startGame() {

    }

    @Override
    public int getNumberFromDice() {
        return this.dice.roll();
    }

    @Override
    public void restartGame() {

    }

    @Override
    public void giveUpGame() {

    }

}
