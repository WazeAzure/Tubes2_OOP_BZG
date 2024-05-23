package org.gui;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

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
}
