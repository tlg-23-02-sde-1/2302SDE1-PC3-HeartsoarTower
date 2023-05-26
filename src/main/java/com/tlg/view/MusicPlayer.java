package com.tlg.view;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private Clip clip;
    private FloatControl volumeControl;
    private String lastFilePath;

    public void play(String filePath) {
        stop();
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                setVolume(0.75f);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }


    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
            clip = null;

        }
    }

    public void setVolume(float volume) {
        if (volumeControl != null) {
            float gain = volumeControl.getMinimum() + (volumeControl.getMaximum() - volumeControl.getMinimum()) * volume;
            volumeControl.setValue(gain);
        }
    }
}
