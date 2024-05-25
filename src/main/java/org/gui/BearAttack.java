package org.gui;

import org.config.Config;
import org.grid.Grid;
import org.kartu.Kartu;
import org.kartu.harvestable.Harvestable;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BearAttack {
    private double timeLeft;
    private App app;
    public BearAttack(App app) {
        this.app = app;
        new Thread(() -> {
            app.gameEngine.setGameState(1);
            App.farm.render();
            this.timeLeft = 5;
            JPanel transparentPanel = new JPanel();
            transparentPanel.setOpaque(false);
            TitledBorder border = new TitledBorder(BorderFactory.createLineBorder(Color.RED, 7),String.format("%.1f", timeLeft));
            int jmin = 100;
            int imin = 100;
            int imax = -1;
            int jmax = -1;
            List<String> temp = App.gameEngine.getCurrentPemain().getLadang().destroyRegion();
            for (String s : temp) {
                List<Integer> list = Grid.parseFromKey(s);
                for (int k = 0; k < 2; k++) {
                    if (list.get(0) > imax) {
                        imax = list.get(0);
                    } else if (list.get(0) < imin) {
                        imin = list.get(0);
                    }
                    if (list.get(1) > jmax) {
                        jmax = list.get(1);
                    } else if (list.get(1) < jmin) {
                        jmin = list.get(1);
                    }
                }
            }
            int ladang_i_top_left = jmin-1; // ini angka percobaan
            int ladang_j_top_left = imin-1; // ini angka percobaan
            int XbearRegion = ladang_j_top_left * (LadangPanel.widthPaddingNormal+LadangPanel.widthPetakNormal);
            int YbearRegion = ladang_i_top_left * (LadangPanel.heightPaddingNormal+LadangPanel.heightPetakNormal);

            int ladang_i_bottom_right = jmax-1;
            int ladang_j_bottom_right = imax-1;
            int widthBearPanel = ((ladang_j_bottom_right-ladang_j_top_left+1)*LadangPanel.widthPetakNormal) + ((ladang_j_bottom_right-ladang_j_top_left+2)*LadangPanel.widthPaddingNormal);
            int heightBearPanel = ((ladang_i_bottom_right-ladang_i_top_left+1)*LadangPanel.heightPetakNormal) + ((ladang_i_bottom_right-ladang_i_top_left+2)*LadangPanel.heightPaddingNormal);

            transparentPanel.setBounds(XbearRegion,YbearRegion,widthBearPanel,heightBearPanel);
            transparentPanel.setBorder(border);
            app.main_panel.add(transparentPanel);
            app.main_panel.setComponentZOrder(transparentPanel,0);
            try {
                SwingUtilities.invokeLater(() -> {
                    border.setTitle("Bear Attack! Time Left: " + String.format("%.1f", timeLeft)); // Update the title once before the loop
                    transparentPanel.repaint();
                });
                // COuntdown
                while (timeLeft > 0) {
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
                Boolean isTrap = false;
                for (String s : temp) {
                    Harvestable h = App.gameEngine.getCurrentPemain().getLadang().getObject(s);
                    if (h != null ) {
                        if (h.getItemAktif().containsKey("TRAP")) {
                            isTrap = true;
                            break;
                        }
                    }
                }
                if (isTrap) {
                    List<Kartu> l = new ArrayList<>();
                    l.add(Config.buildOmnivora("BERUANG"));
                    App.gameEngine.getCurrentPemain().getActiveDeck().addCard(l);
                } else {
                    for (String s : temp) {
                        Harvestable h = App.gameEngine.getCurrentPemain().getLadang().getObject(s);
                        if (h != null ) {
                            if (!h.getItemAktif().containsKey("PROTECT")) {
                                App.gameEngine.getCurrentPemain().getLadang().removeObject(s);
                            }
                        }
                    }
                }
            } catch (InterruptedException interrupt) {
                interrupt.printStackTrace();
            }
            App.gameEngine.setGameState(2);
            app.main_panel.remove(transparentPanel);
            app.farm.render();
            app.main_panel.revalidate();
            app.main_panel.repaint();
        }).start();
    }
}
