package org.gui;

import org.kartu.Kartu;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class ShuffleCardDialog {
    private int choosedCard;
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
        JButton buttonShuffle = new JButton("Shuffle");
        JPanel buttonShufflePanel = new JPanel();
        buttonShuffle.setFocusable(false);
        buttonShuffle.addActionListener(e -> {
            shuffleDialog.setVisible(false);
            this.render(numCardShuffle);
            shuffleDialog.dispose();
        });
        buttonShufflePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonShufflePanel.add(buttonShuffle);
        buttonShufflePanel.setBounds(0,15+120+15+120+15,230,30);
        contentPanel.add(buttonShufflePanel);

        // Masukin component button OK
        JButton okButton = new JButton("OK");
        JPanel okButtonPanel = new JPanel();
        okButton.setFocusable(false);
        okButton.addActionListener(e -> {
            App.gameEngine.getCurrentPemain().getActiveDeck().addCard(currentListKartu);
            shuffleDialog.dispose();
            app.farm.render();
        });
        okButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        okButtonPanel.add(okButton);
        okButtonPanel.setBounds(0,15+120+15+120+15+30+15,230,30);
        contentPanel.add(okButtonPanel);

        shuffleDialog.add(contentPanel);
        shuffleDialog.setVisible(true);
    }

    private void logicShuffle(){

    }
}
