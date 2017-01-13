package model;

import model.entities.ClassicDice;
import model.entities.Dice;

/**
 * This is the main class of the model and implements the Model Interface.
 */
public class GameImpl implements Game {

    private final Dice dice;

    /**
     * GameImpl contructor.
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
