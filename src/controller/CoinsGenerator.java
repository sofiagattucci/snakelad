package controller;

import java.util.Optional;

import model.Model;
import view.View;

/**
 * Class to manage coin that appear during the game.
 */
public class CoinsGenerator implements Runnable {

    private static final int WAIT = 100;
    private final Thread t;
    private volatile boolean stop;
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

    @Override
    public void run() {
        while (!this.stop) {
            try {
                Thread.sleep(WAIT);
                synchronized (this) {
                    if (!this.stop) {
                        position = Optional.of(this.model.tryGenerateCoin());
                        if (position.get().isPresent()) {
                            this.view.putCoin(position.get().get());
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
    public synchronized void setStop() {
        this.stop = true;
    }

    /**
     * Start the thread.
     */
    public synchronized void start() {
        this.t.start();
    }

}
