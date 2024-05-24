package org.gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class BearAttack {
    private double timeLeft;
    private App app;
    public static Boolean isBearAtack = false;

    public BearAttack(App app) {
        this.app = app;
        this.timeLeft = 10;
        new Thread(() -> {
            JPanel transparentPanel = new JPanel();
            transparentPanel.setOpaque(false);
            TitledBorder border = new TitledBorder(BorderFactory.createLineBorder(Color.RED, 7),String.format("%.1f", timeLeft));
            int ladang_i_top_left = 0; // ini angka percobaan
            int ladang_j_top_left = 0; // ini angka percobaan
            int XbearRegion = ladang_j_top_left * (LadangPanel.widthPaddingNormal+LadangPanel.widthPetakNormal);
            int YbearRegion = ladang_i_top_left * (LadangPanel.heightPaddingNormal+LadangPanel.heightPetakNormal);

            int ladang_i_bottom_right = 2;
            int ladang_j_bottom_right = 1;
            int widthBearPanel = ((ladang_j_bottom_right-ladang_j_top_left+1)*LadangPanel.widthPetakNormal) + ((ladang_j_bottom_right-ladang_j_top_left+2)*LadangPanel.widthPaddingNormal);
            int heightBearPanel = ((ladang_i_bottom_right-ladang_i_top_left+1)*LadangPanel.heightPetakNormal) + ((ladang_i_bottom_right-ladang_i_top_left+2)*LadangPanel.heightPaddingNormal);

            transparentPanel.setBounds(XbearRegion,YbearRegion,widthBearPanel,heightBearPanel);
            transparentPanel.setBorder(border);
            app.main_panel.add(transparentPanel);
            app.main_panel.setComponentZOrder(transparentPanel,0);
            try {
                BearAttack.isBearAtack=true;
                SwingUtilities.invokeLater(() -> {
                    border.setTitle("Bear Attack! Time Left: " + String.format("%.1f", timeLeft)); // Update the title once before the loop
                    transparentPanel.repaint();
                });
                // Timer for countdown
                while (timeLeft > 0.0) {
                    timeLeft -= 0.1;

                    // Ubah ngeprint waktu tersisa
                    SwingUtilities.invokeLater(() -> {
                        Border lineBorder = BorderFactory.createLineBorder(Color.RED, 7);
                        TitledBorder newBorder = new TitledBorder(lineBorder, String.format("%.1f", timeLeft));
                        transparentPanel.setBorder(newBorder);
                        transparentPanel.revalidate(); // Make sure layout is done
                        transparentPanel.repaint();
                    });

                    Thread.sleep(100); // Update every 0.1 seconds
                }
                BearAttack.isBearAtack=false;
                app.main_panel.remove(transparentPanel);
                app.farm.render();
                app.main_panel.revalidate();
                app.main_panel.repaint();
            } catch (InterruptedException interrupt) {
                interrupt.printStackTrace();
            }
        }).start();
    }

}
