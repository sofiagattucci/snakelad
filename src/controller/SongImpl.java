package controller;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * Implementation of Song interface.
 *
 */
public class SongImpl implements Runnable, Song {

    private static final String PATH = "./res/Music/Snakelad.wav";
    private static Clip clip;
    private final Thread t;
    private final Thread t1;
    private volatile boolean stopField;
    private volatile boolean controlWait;
    private FloatControl volume;

    /**
     * Constructor.
     */
    public SongImpl() {
        this.t1 = new Thread(this);
        this.t = new Thread(this);
        this.t.setDaemon(true);
    }

    @Override
    public void run() {
        try {
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(PATH).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            this.volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            synchronized (this.t1) {
                this.controlWait = true;
                this.t1.notifyAll();
            }
            clip.loop(1000);
            while (this.stopField) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    this.t.interrupt();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void setStop(final boolean stop) {
        clip.stop();
        clip.setFramePosition(0);
        clip.close();
        this.stopField = stop;
    }

    @Override
    public boolean isStopped() {
        return this.stopField;
    }

    @Override
    public synchronized void start() {
        this.stopField = this.stopField ? false : true;
        this.t.start();
    }

    @Override
    public float getMinimum() {
        synchronized (this.t1) {
            while (!controlWait) {
                try {
                    this.t1.wait();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return this.volume.getMinimum();
    }

    @Override
    public float getMaximum() {
        synchronized (this.t1) {
            while (!controlWait) {
                try {
                    this.t1.wait();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return this.volume.getMaximum();
    }

    @Override
    public float getCurrent() {
        synchronized (this.t1) {
            while (!controlWait) {
                try {
                    this.t1.wait();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return this.volume.getValue();
    }

    @Override
    public void setVolume(final float volume) {
        this.volume.setValue(volume);
    }
}
