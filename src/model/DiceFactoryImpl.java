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
    public Dice create5To10Dice() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Dice createNegativeDice() {
        // TODO Auto-generated method stub
        return null;
    }

}
