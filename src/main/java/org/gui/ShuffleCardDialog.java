package org.gui;

import org.kartu.Kartu;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

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
            App.gameEngine.getCurrentPemain().getShuffleDeck().removeFromDeck(currentListKartu);
            shuffleDialog.dispose();
            BearAttack.isBearAtack=true;
            app.farm.render();
            new BearAttack(app);
//            JPanel transparentPanel = new JPanel();
//            transparentPanel.setOpaque(false);
//            TitledBorder border = new TitledBorder(BorderFactory.createLineBorder(Color.RED, 7),"TES");
//            int ladang_i_top_left = 0; // ini angka percobaan
//            int ladang_j_top_left = 0; // ini angka percobaan
//            int XbearRegion = ladang_j_top_left * (LadangPanel.widthPaddingNormal+LadangPanel.widthPetakNormal);
//            int YbearRegion = ladang_i_top_left * (LadangPanel.heightPaddingNormal+LadangPanel.heightPetakNormal);
//
//            int ladang_i_bottom_right = 2;
//            int ladang_j_bottom_right = 1;
//            int widthBearPanel = ((ladang_j_bottom_right-ladang_j_top_left+1)*LadangPanel.widthPetakNormal) + ((ladang_j_bottom_right-ladang_j_top_left+2)*LadangPanel.widthPaddingNormal);
//            int heightBearPanel = ((ladang_i_bottom_right-ladang_i_top_left+1)*LadangPanel.heightPetakNormal) + ((ladang_i_bottom_right-ladang_i_top_left+2)*LadangPanel.heightPaddingNormal);
//
//            transparentPanel.setBounds(XbearRegion,YbearRegion,widthBearPanel,heightBearPanel);
//            transparentPanel.setBorder(border);
//            app.farm.add(transparentPanel);
//            app.farm.setComponentZOrder(transparentPanel,0);
//            AtomicReference<Double> timeLeft = new AtomicReference<>(5.0);
//            new Thread(() -> {
//                try {
//                    Thread.sleep(5000); // Simulate bear attack after 5 seconds
////                    bearAttack = true;
//                    SwingUtilities.invokeLater(() -> {
//                        border.setTitle("Bear Attack! Time Left: " + String.format("%.1f", timeLeft)); // Update the title once before the loop
////                        border.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
//                        transparentPanel.repaint();
//                    });Variable used in lambda expression should be final or effectively final
//                    double updatedTimeLeft = 5.0;
//                    // Timer for countdown
//                    while (updatedTimeLeft > 0) {
//                        updatedTimeLeft -= 0.1;
//                        SwingUtilities.invokeLater(() -> {
//                            border.setTitle("Bear Attack! Time Left: " + String.format("%.1f", updatedTimeLeft));
//                            transparentPanel.repaint();
//                        });
//                        Thread.sleep(100); // Update every 0.1 seconds
//                    }
//
////                    if (bearAttack) {
////                        // Bear attack ended (handle game logic here)
////                        SwingUtilities.invokeLater(() -> {
////                            border.setTitle("Bear Defeated!");
////                            border.setBorder(BorderFactory.createEmptyBorder()); // Remove red border
////                        });
////                    }
//                } catch (InterruptedException interrupt) {
//                    interrupt.printStackTrace();
//                }
//            }).start();
        });
        okButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        okButtonPanel.add(okButton);
        okButtonPanel.setBounds(0,15+120+15+120+15+30+15,230,30);
        contentPanel.add(okButtonPanel);

        shuffleDialog.add(contentPanel);
        shuffleDialog.setVisible(true);
    }
}
