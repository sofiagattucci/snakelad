package controller;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Implementation of Song interface.
 *
 */
public class SongImpl implements Runnable, Song {

    private static final String PATH = "./res/Snakelad.wav";
    private final Thread t;
    private volatile boolean stopField;

    /**
     * Constructor.
     */
    public SongImpl() {
        this.t = new Thread(this);
        this.t.setDaemon(true);
    }

    @Override
    public void run() {
        try {
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(PATH).getAbsoluteFile());
            final Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(1000);
            while (this.stopField) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//            clip.stop();
//            clip.setFramePosition(0);
//            clip.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void setStop(final boolean stop) {
        this.stopField = stop;
    }

    @Override
    public boolean isStopped() {
        return this.stopField;
    }

    @Override
    public void start() {
        this.stopField = this.stopField ? false : true;
        this.t.start();
    }
}
