package org.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Farm extends Default{

    private JPanel player1(){
        JPanel panel = new JPanel();
        panel.setBounds(0, 20, 100, 80);
        panel.setBackground(Color.GREEN);
        return panel;
    }

    private JPanel player2(){
        JPanel panel = new JPanel();
        panel.setBounds(530, 70, 150, 200);
        panel.setBackground(Color.GREEN);
        return panel;

    }

    private JPanel title(){
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        label.setText("LADANGKU");
        panel.setBounds(150, 0, 690, 105);
        panel.setBackground(Color.BLUE);
        return panel;

    }
    
    private JPanel headerPanel(){
        JPanel panel = new JPanel();
        panel.setBounds(180, 0, 620, 100);
        panel.setBackground(Color.WHITE);
        panel.add(player1());
        panel.add(title());
        panel.add(player2());
        return panel;
    }

    private JPanel field(){
        JPanel panel = new JPanel();
        panel.setBounds(180, 100, 620, 560);
        panel.setBackground(Color.BLACK);
        return panel;
    }
    
    private JPanel menu(){
        JPanel panel = new JPanel();
        panel.setBounds(0, 100, 180, 560);
        panel.setBackground(Color.YELLOW);
        return panel;
    }

    private JPanel deck(){
        JPanel panel = new JPanel();
        panel.setBounds(800, 100, 260, 560);
        panel.setBackground(Color.gray);
        return panel;
    }

    private JPanel nextTurn(){
        JPanel panel = new JPanel();
        panel.setBounds(920, 20, 120, 60);
        panel.setBackground(Color.pink);
        return panel;
    }

    public JPanel page_farm(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1060, 660);
        panel.setBackground(Color.ORANGE);

        panel.add(headerPanel());
        panel.add(menu());
        panel.add(field());
        panel.add(deck());
        panel.add(nextTurn());

        return panel;
    }
}
