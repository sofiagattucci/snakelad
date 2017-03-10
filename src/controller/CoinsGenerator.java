package controller;

import java.util.Optional;

import model.Model;
import utilities.TypesOfItem;
import view.View;

/**
 * Class to manage coin that appear during the game.
 */
public class CoinsGenerator implements Runnable {

    private static final int WAIT = 500;

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
                        System.out.println("Dentro if");
                        this.getItem(TypesOfItem.COIN);
                        this.getItem(TypesOfItem.DIAMOND);
                        this.getItem(TypesOfItem.SKULL);
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
