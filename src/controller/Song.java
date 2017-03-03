package controller;
/**
 * Interface of Song.
 *
 */
public interface Song {

    /**
     * Setter for stop field.
     * @param stop
     *          the value of stop field
     */
    void setStop(boolean stop);

    /**
     * Getter for stop field.
     * @return the stop field;
     */
    boolean isStopped();


    /**
     * Start the thread.
     */
    void start();

}