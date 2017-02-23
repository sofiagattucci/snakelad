package model;

/**
 * Implemets a factory for various kind of dice.
 * It's designed using Factory Method pattern.
 */
public class DiceFactoryImpl implements DiceFactory {

    @Override
    public Dice createClassicDice() {
        return ClassicDice.get();
    }

    @Override
    public Dice create5To10Dice(final Dice dice) {
        return new Dice5To10(dice);
    }

    @Override
    public Dice createNegativeDice(final Dice dice) {
        return new NegativeDice(dice);
    }

}
