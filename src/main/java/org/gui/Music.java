package org.gui;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Music {
    Clip clip;
    AudioInputStream sound;

    public void setFile(String soundFileName) {
        try {
            File file = new File(soundFileName);
            sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (Exception e) {
            System.out.println("Error setting file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.start();
            System.out.println("music start");
        } else {
            System.out.println("Clip is not initialized");
        }
    }

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            System.out.println("music loop start");
        } else {
            System.out.println("Clip is not initialized");
        }
    }

    public void stop() throws IOException {
        if (sound != null) {
            sound.close();
        }
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }

    public void setVolume(float volume) {
        if (clip != null) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float min = gainControl.getMinimum();
            float max = gainControl.getMaximum();
            float dB = min + (max - min) * volume; // Convert volume from 0.0-1.0 to dB range
            gainControl.setValue(dB);
        } else {
            System.out.println("Clip is not initialized");
        }
    }

    public float getVolume() {
        if (clip != null) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            return gainControl.getValue();
        } else {
            System.out.println("Clip is not initialized");
            return 0.0f;
        }
    }
}
