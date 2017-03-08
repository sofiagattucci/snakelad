package controller;
/**
 * Class to manage coin that appear during the game.
 */
public class CoinsGenerator implements Runnable {

    private static final int WAIT = 20000;
    private final Thread t;
    private boolean stop;

    /**
     * Constructor.
     */
    public CoinsGenerator() {
        this.t = new Thread(this);
        this.t.setDaemon(true);
    }

    @Override
    public void run() {
        while (this.stop) {
            try {
                Thread.sleep(WAIT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Set the field stop.
     */
    public void setStop() {
        this.stop = false;
    }

    /**
     * Start the thread.
     */
    public void start() {
        this.t.start();
    }

}
