/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.progettoesamemap.gui;

import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import com.mycompany.progettoesamemap.mediaPlayer.MediaPlayerThread;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author gabry
 */
public class MusicFrame extends javax.swing.JFrame {

    MediaPlayerThread tPlayer = new MediaPlayerThread();
    boolean isPlaying = false;

    /**
     * Creates new form MusicFrame
     */
    public MusicFrame() {
        initComponents();
        setMusic();

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                if (isPlaying) {
                    int result = JOptionPane.showConfirmDialog(
                            MusicFrame.this,
                            "La musica è ancora in riproduzione. Vuoi fermarla prima di chiudere?",
                            "Conferma chiusura",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (result == JOptionPane.YES_OPTION) {
                        tPlayer.stopMusic();
                        isPlaying = false;
                        dispose();
                    }
                } else {
                    dispose();
                }
            }
        });
    }

    private void setMusic() {
        setTitle("Lettore");
        setSize(400, 500);
        setResizable(false);
        ImageIcon musicImage = new ImageIcon("./images/music.png");
        setIconImage(musicImage.getImage());
        musicImg.setIcon(musicImage);
        musicImg.setText("");

    }

    public static void avviaMusica() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MusicFrame musicFrame = new MusicFrame();
                musicFrame.setLocationRelativeTo(null); 
                musicFrame.setVisible(true);
                musicFrame.setResizable(false);
                
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playButton = new javax.swing.JButton();
        textNameSong = new javax.swing.JLabel();
        musicImg = new javax.swing.JLabel();
        stopButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        playButton.setText("PLAY");
        playButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playButtonMouseClicked(evt);
            }
        });

        textNameSong.setText("Theme song");

        musicImg.setText("jLabel1");

        stopButton.setText("STOP");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("stop music");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textNameSong)
                            .addComponent(jLabel1))
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(stopButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(playButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(musicImg)))
                .addContainerGap(116, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(musicImg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playButton)
                    .addComponent(textNameSong))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stopButton)
                    .addComponent(jLabel1))
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void playButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playButtonMouseClicked
        if (isPlaying == false) {
            tPlayer.run();
            isPlaying = true;
        }
    }//GEN-LAST:event_playButtonMouseClicked

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        if (isPlaying == true) {
            tPlayer.stopMusic();
            isPlaying = false;
        }
    }//GEN-LAST:event_stopButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MusicFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MusicFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MusicFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MusicFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MusicFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel musicImg;
    private javax.swing.JButton playButton;
    private javax.swing.JButton stopButton;
    private javax.swing.JLabel textNameSong;
    // End of variables declaration//GEN-END:variables
}
