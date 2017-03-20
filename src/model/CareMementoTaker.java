package model;

/**
 * It's responsible for the ModelMemento's safekeeping. Never operates on or examines the contents of the memento.
 * It's designed using Singleton pattern.
 */
public final class CareMementoTaker {

    private static final CareMementoTaker SINGLETON = new CareMementoTaker();

    private ModelMemento memento;

    //private constructor
    private CareMementoTaker() {

    }

    /**
     * Static method which returns the CareMementoTaker unique instance.
     * @return the CareMementoTaker unique instance.
     */
    public static CareMementoTaker get() {
        return CareMementoTaker.SINGLETON;
    }

    /**
     * Stores a Model's subclass internal state inside a memento.
     * @param memento
     *          The memento which contains a Model's subclass snapshot.
     */
    public void addMemento(final ModelMemento memento) {
        this.memento = memento;
    }

    /**
     * Returns the ModelMemento which contains a Model's subclass internal state.
     * @return the ModelMemento which contains a Model's subclass internal state.
     */
    public ModelMemento getMemento() {
        return this.memento;
    }

}
