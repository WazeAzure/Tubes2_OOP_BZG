package org.gui;

import org.kartu.Kartu;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

public class DialogMenang {
    private App app;


    // Inisialisasi dulu
    public DialogMenang(App app) {
        this.app = app;
    }

    public void render(){
        JDialog dialogPemenang = new JDialog(app.frame,"Congratulation",true);
        dialogPemenang.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialogPemenang.setSize(800,240);
        dialogPemenang.setLocationRelativeTo(app.frame);

        JPanel contentPanel = new JPanel();
        contentPanel.setBounds(0,0,240,800);
        contentPanel.setLayout(null);
        contentPanel.setBackground(Color.decode("#F1E4C3"));

        // Menentukan pemenang
        String namaPemenang;
        if(App.gameEngine.getCurrentPemain().getUang() > App.gameEngine.getCurrentLawan().getUang()){
            namaPemenang = "Selamat, " + "Player " + App.gameEngine.getCurrentPemain().getPlayerNumber() + " menang!";
        }else if(App.gameEngine.getCurrentPemain().getUang() < App.gameEngine.getCurrentLawan().getUang()){
            namaPemenang =  "Selamat, " + "Player " + App.gameEngine.getCurrentLawan().getPlayerNumber() + " menang!";
        }else{
            namaPemenang = "Permainan Seri";
        }

        // Masukin component label deklarasi pemenang
        JLabel labelPemenang = new JLabel(namaPemenang);
        labelPemenang.setFont(new Font("Tahoma", Font.PLAIN, 30));

        JPanel flowPanelLabelPemenang = new JPanel();
        flowPanelLabelPemenang.setLayout(new FlowLayout(FlowLayout.CENTER));
        flowPanelLabelPemenang.setBounds(0,20,800,40);
        flowPanelLabelPemenang.setBackground(Color.decode("#F1E4C3"));
        flowPanelLabelPemenang.add(labelPemenang);
        contentPanel.add(flowPanelLabelPemenang);

        // Masukin component button Restart
        JButton restartButton = new JButton("Restart");
        JPanel restartButtonPanel = new JPanel();
        restartButton.setBackground(Color.decode("#A1DD70"));
        restartButton.setForeground(Color.decode("#ECB176"));
        restartButton.setPreferredSize(new Dimension(300, 25));

        restartButton.setFocusable(false);
        restartButton.addActionListener(e -> {
            // Restart logic
            App.gameEngine.resetGame();
            app.farm.render();
            app.main_panel.revalidate();
            app.main_panel.repaint();
            app.frame.revalidate();
            app.frame.repaint();
            dialogPemenang.setVisible(false);
            ShuffleCardDialog shuffleCardDialog = new ShuffleCardDialog(app);
            shuffleCardDialog.render(app.gameEngine.getCurrentPemain().getActiveDeck().remainingSlot());
            dialogPemenang.dispose();
        });
        restartButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        restartButtonPanel.add(restartButton);
        restartButtonPanel.setBounds(0,20+40+20,800,30);
        restartButtonPanel.setBackground(Color.decode("#F1E4C3"));
        contentPanel.add(restartButtonPanel);

        // Masukin button Exit
        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(Color.decode("#EE4E4E"));
        exitButton.setFocusable(false);
        exitButton.setForeground(Color.decode("#F6EEC9"));
        JPanel exitButtonPanel = new JPanel();
        exitButton.setPreferredSize(new Dimension(300, 25));

        exitButton.setFocusable(false);
        exitButton.addActionListener(e -> {
            // Exit logic
            dialogPemenang.dispose();
            app.frame.dispose();
        });
        exitButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        exitButtonPanel.setBackground(Color.decode("#F1E4C3"));
        exitButtonPanel.add(exitButton);
        exitButtonPanel.setBounds(0,20+40+20+30+20,800,30);
        contentPanel.add(exitButtonPanel);

        dialogPemenang.add(contentPanel);
        dialogPemenang.setVisible(true);
    }
}
