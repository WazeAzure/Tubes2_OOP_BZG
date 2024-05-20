package org.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.BorderUIResource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import java.util.*;

public class Gui extends Default{
    JFrame frame = new JFrame();

    private void initialize() {
        frame.setTitle("Shop GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); 
        frame.setResizable(false);

        JPanel main = main_panel();

        JPanel empty1 = new JPanel();
        empty1.setPreferredSize(new Dimension(1100, 20));
        JPanel empty3 = new JPanel();
        empty3.setPreferredSize(new Dimension(1100, 20));
        JPanel empty2 = new JPanel();
        empty2.setPreferredSize(new Dimension(20, 660));
        JPanel empty4 = new JPanel();
        empty4.setPreferredSize(new Dimension(20, 660));

        frame.add(empty1, BorderLayout.NORTH);
        frame.add(empty3, BorderLayout.SOUTH);
        frame.add(main, BorderLayout.CENTER);
        frame.add(empty2, BorderLayout.EAST);
        frame.add(empty4, BorderLayout.WEST);

        frame.setVisible(true);

        frame.pack();
    }

    private JPanel titlePanel() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1060, 50);

        JLabel label = new JLabel("Welcome to Shop!");
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label);
        
        return panel;
    }

    private static JPanel items_in_shop() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout()); 
        for (int i = 0; i < 10; i++) {
            ItemCard card = new ItemCard("Wibu", "src\\main\\java\\org\\gui\\assets\\image.png", 5000, 2);
            JPanel cardpanel = card.createCard();
            contentPanel.add(cardpanel);
        }
        contentPanel.revalidate();
        contentPanel.repaint();

        JScrollPane scroll = new JScrollPane(contentPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    private JPanel buyPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 50, 1060, 280);
        panel.setBackground(Color.PINK);

        JLabel label = new JLabel();
        label.setText("Want to Buy?");

        panel.add(label);
        
        JPanel items = items_in_shop();
        panel.add(items);

        return panel;
    }

    private JPanel sellPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 330, 1060, 280);
        panel.setBackground(Color.YELLOW);

        JLabel label = new JLabel();
        label.setText("Want to Sell?");
        
        panel.add(label);
        return panel;
    }

    private JPanel backPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 610, 1060, 50);
        JButton button = new JButton("Back");
        button.setBackground(Color.GREEN);
        panel.add(button);
        return panel;
    }

    private JPanel page_shop() {
        JPanel panel = new JPanel(); 
        panel.setLayout(null);
        panel.setBounds(0, 0, 1060, 660);
        panel.add(titlePanel());
        panel.add(buyPanel());
        panel.add(sellPanel());
        panel.add(backPanel());
        panel.setBackground(Color.BLACK);
        return panel;
    }

    private JPanel main_panel() {
        JPanel panel = new JPanel(); 
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1060, 660));
        panel.add(page_shop());
        panel.setBackground(Color.CYAN);
        return panel;
    }
    
    public static void main(String[] args) {
        Gui gui = new Gui();
        gui.initialize();
    }
}
