package controller;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 * Class to manage the background music.
 *
 */
public class BackgroundMusic extends AbstractSong {

    private Clip clip;
    private FloatControl volume;
    private boolean control;

    /**
     * Constructor. 
     */
    public BackgroundMusic() {
        try {
            clip = AudioSystem.getClip();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        if (this.control) {
            if (this.clip.isRunning()) {
                clip.close();
            }
        } else {
            throw new IllegalStateException();
        }
    }


    /**
     * Start the music.
     * @param path
     *          the path of song to play.
     */
    public void start(final String path) {
        try {
            if (!this.clip.isRunning()) {
                try {
                    clip.open(AudioSystem.getAudioInputStream(new File(path)));
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                    e.printStackTrace();
                }
                this.volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            } else {
                if (this.getCurrent() != this.getMute()) {
                    this.stop();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.control = true;
        clip.loop(1000);
    }


    @Override
    public float getCurrent() {
        if (this.control) {
            return this.volume.getValue();
        } else {
            throw new IllegalStateException();
        }
    }


    @Override
    public void setVolume(final float volume) {
        if (this.control) {
            if (volume == this.getMute()) {
                this.stop();
            }
            this.volume.setValue(volume);
        } else {
            throw new IllegalStateException();
        }
    }
}
