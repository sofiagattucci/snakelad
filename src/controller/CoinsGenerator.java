package controller;

import java.util.Optional;

import model.Model;
import utilities.enumeration.TypesOfItem;
import view.View;

/**
 * Class to manage coin that appear during the game.
 */
public class CoinsGenerator implements Runnable {

    private static final int WAIT = 500;

    private final Thread t;
    private volatile boolean stop;
    private volatile boolean suspended;
    private final Model model;
    private final View view;
    private static Optional<Optional<Integer>> position;

    /**
     * Constructor.
     * @param controller
     *          the instance of controller passed by Controller class
     */
    public CoinsGenerator(final ViewObserver controller) {
        this.t = new Thread(this);
//        this.t.setDaemon(true);
        this.model = controller.getGame();
        this.view = controller.getView();
    }
    /**
     * Getter for item.
     * @param type the type of item
     * @return the position of item
     */
    private void getItem(final TypesOfItem type) {
        switch(type) {
        case COIN:
             position = Optional.of(this.model.tryGenerateCoin());
             if (position.get().isPresent()) {
                 this.view.putItem(position.get().get(), TypesOfItem.COIN);
             }
             break;
        case DIAMOND:
            position = Optional.of(this.model.tryGenerateDiamond());
            if (position.get().isPresent()) {
                this.view.putItem(position.get().get(), TypesOfItem.DIAMOND);
            }
            break;
        case SKULL:
            position = Optional.of(this.model.tryGenerateSkull());
            if (position.get().isPresent()) {
                this.view.putItem(position.get().get(), TypesOfItem.SKULL);
            }
            break;
            default:
                break;
        }
    }

    @Override
    public void run() {
        while (!this.stop) {
            try {
                Thread.sleep(WAIT);
                synchronized (this) {
                    if (!this.stop) {
                        this.getItem(TypesOfItem.COIN);
                        this.getItem(TypesOfItem.DIAMOND);
                        this.getItem(TypesOfItem.SKULL);
                        while (suspended) {
                            System.out.println("prima wait");
                            wait();
                            System.out.println("dopo wait");
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Set the field stop.
     */
    public synchronized void stopGenerate() {
        this.resume();
        this.stop = true;
    }

    /**
     * Start the thread.
     */
    public synchronized void start() {
        this.t.start();
    }
    /**
     * Suspend the thread.
     */
    public synchronized void suspende() {
        this.suspended = true;
    }
    /**
     * Resume the thread.
     */
    public synchronized void resume() {
        this.suspended = false;
        notifyAll();
    }

    /**
     * Tell if the thread is still alive.
     * @return true if the thread is alive, false otherwise.
     */
    public synchronized boolean isAlive() {
        return this.t.isAlive();
    }

    /**
     * Tell if the thread is interrupt.
     * @return true if the thread is interrupt, false otherwise.
     */
    public synchronized boolean isInterrupt() {
        return this.t.isInterrupted();
    }

    /**
     * The thread name.
     * @return the thread name
     */
    public synchronized String nameThread() {
        return this.t.getName();
    }

}
