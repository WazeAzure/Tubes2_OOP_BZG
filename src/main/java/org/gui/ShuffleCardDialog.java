package org.gui;

import org.kartu.Kartu;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class ShuffleCardDialog {
    private App app;
    

    // Inisialisasi dulu
    public ShuffleCardDialog(App app) {
        this.app = app;
    }

    public void render(int numCardShuffle){
        JDialog shuffleDialog = new JDialog(app.frame,"Shuffle Card",true);
        shuffleDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        shuffleDialog.setSize(240,410);
        shuffleDialog.setLocationRelativeTo(app.frame);

        JPanel contentPanel = new JPanel();
        contentPanel.setBounds(0,0,240,410);
        contentPanel.setLayout(null);
        contentPanel.setBackground(Color.decode("#F1E4C3"));

        // Masukin componen kandidat kartu
        List<Kartu> currentListKartu = App.gameEngine.shuffleDeck();
        int i = 0;
        for(Kartu k: currentListKartu){
            if(i==0){
                CardPanel cardPanel1 = new CardPanel(15,15,90,120, k);
                contentPanel.add(cardPanel1);
            }else if(i==1){
                CardPanel cardPanel2 = new CardPanel(15+90+15,15,90,120, k);
                contentPanel.add(cardPanel2);
            }else if(i==2){
                CardPanel cardPanel3 = new CardPanel(15,15+120+15,90,120, k);
                contentPanel.add(cardPanel3);
            }else{
                // i==3
                CardPanel cardPanel4 = new CardPanel(15+90+15,15+120+15,90,120, k);
                contentPanel.add(cardPanel4);
            }
            i++;
        }

        // Masukin component button shuffle
        JButton buttonShuffle = new JButton();
        try {
            BufferedImage img = ImageIO.read(new File("src/main/java/org/gui/assets/shuffle.png"));
            Image resizedImage = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImage);

            buttonShuffle.setIcon(icon);
            buttonShuffle.setPreferredSize(new Dimension(30, 30)); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        buttonShuffle.setBackground(Color.decode("#F1E4C3"));
        JPanel buttonShufflePanel = new JPanel();
        buttonShuffle.setFocusable(false);
        buttonShuffle.addActionListener(e -> {

            // Add lagu
            String sound_track = "src/main/java/org/gui/assets/shuffle.wav";
            Music se = new Music();
            se.setFile(sound_track);
            se.play();

            shuffleDialog.setVisible(false);
            this.render(numCardShuffle);
            shuffleDialog.dispose();
        });
        buttonShufflePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonShufflePanel.add(buttonShuffle);
        buttonShufflePanel.setBounds(0,15+120+15+120+15,230,40);
        buttonShufflePanel.setBackground(Color.decode("#F1E4C3"));

        contentPanel.add(buttonShufflePanel);

        // Masukin component button OK
        JButton okButton = new JButton("OK");
        JPanel okButtonPanel = new JPanel();
        okButtonPanel.setBackground(Color.decode("#F1E4C3"));
        okButton.setBackground(Color.decode("#C6A969"));
        okButton.setPreferredSize(new Dimension(70, 25));

        okButton.setFocusable(false);
        okButton.addActionListener(e -> {

            String sound_track = "src/main/java/org/gui/assets/collect.wav";
            Music se = new Music();
            se.setFile(sound_track);
            se.play();

            App.gameEngine.getCurrentPemain().getActiveDeck().addCard(currentListKartu);
            App.gameEngine.getCurrentPemain().getShuffleDeck().removeFromDeck(currentListKartu);
            shuffleDialog.dispose();
            new BearAttack(app);
        });
        okButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        okButtonPanel.add(okButton);
        okButtonPanel.setBounds(0,15+120+15+120+15+30+7,230,35);
        contentPanel.add(okButtonPanel);

        shuffleDialog.add(contentPanel);
        shuffleDialog.setVisible(true);
    }
}
