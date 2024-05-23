package org.gui;

import javax.swing.*;
import java.awt.*;

public class ShuffleCardDialog {
    private final JFrame rootFrame;
    private int choosedCard;

    // Inisialisasi dulu
    public ShuffleCardDialog(JFrame rootFrame) {
//        super(rootFrame,"Shuffle Card",ModalityType.APPLICATION_MODAL);
        this.rootFrame = rootFrame;
//        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
//        this.setSize(500,500);
//        this.setLocationRelativeTo(rootFrame);
//        this.setLayout(null);
//        this.setUndecorated(true);
    }

    public void render(int numCardShuffle){
        this.choosedCard=0;
        JDialog shuffleDialog = new JDialog(rootFrame,"Shuffle Card",true);
        shuffleDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        shuffleDialog.setSize(240,410);
        shuffleDialog.setLocationRelativeTo(rootFrame);

        JPanel contentPanel = new JPanel();
        contentPanel.setBounds(0,0,240,410);
        contentPanel.setLayout(null);

        // Masukin componen kandidat kartu
        for(int i=0;i<numCardShuffle;i++){
            if(i==0){
                CardPanel cardPanel1 = new CardPanel(15,15,90,120);
                contentPanel.add(cardPanel1);
            }else if(i==1){
                CardPanel cardPanel2 = new CardPanel(15+90+15,15,90,120);
                contentPanel.add(cardPanel2);
            }else if(i==2){
                CardPanel cardPanel3 = new CardPanel(15,15+120+15,90,120);
                contentPanel.add(cardPanel3);
            }else{
                // i==3
                CardPanel cardPanel4 = new CardPanel(15+90+15,15+120+15,90,120);
                contentPanel.add(cardPanel4);
            }
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
            shuffleDialog.dispose();
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
