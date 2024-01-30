/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progettoesamemap.mediaPlayer;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author gabry
 */
public class MediaPlayerThread implements Runnable {
    
    private static final String musicpath = "./music/BosunBill.wav";
    
    private Clip clip;

    /**
     * metodo per la gestione concorrente della musica in gioco.
     */
    @Override
    public void run() {

        try {
            File musicPath = new File(musicpath);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.print("Errore nella lettura del file");
            }
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.out.println("Errore nell'avvio della musica");
        }
    }

    
    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }

}
