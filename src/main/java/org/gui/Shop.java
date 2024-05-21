package org.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Shop extends Default{

    private JPanel titlePanel() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1060, 50);
        panel.setBackground(Color.decode(getColor1()));

        JLabel label = new JLabel("Welcome to Shop!");
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label);
        
        return panel;
    }

    private JScrollPane items_in_shop() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setBackground(Color.decode(getColor2()));
    
        for (int i = 0; i < 10; i++) {
            ItemCard card = new ItemCard("Wibu" + i, "src\\main\\java\\org\\gui\\assets\\image.png", 5000, 2);
            JPanel cardpanel = card.createCard();
            panel.add(cardpanel);
        }
    
        panel.revalidate();
        panel.repaint();
    
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    
        scrollPane.setPreferredSize(new Dimension(1060, 250));  

        scrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(177, 148, 112);
                this.thumbDarkShadowColor = new Color(232, 223, 202);
                this.thumbLightShadowColor = new Color(232, 223, 202);
                this.trackColor = new Color(232, 223, 202);
            }
        });
        return scrollPane;
    }

    private JScrollPane items_in_deck() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setBackground(Color.decode(getColor2()));
    
        for (int i = 0; i < 10; i++) {
            DeckCard card = new DeckCard("Wibu" + i, "src\\main\\java\\org\\gui\\assets\\image.png", 5000, 2);
            JPanel cardpanel = card.createCard();
            panel.add(cardpanel);
        }
    
        panel.revalidate();
        panel.repaint();
    
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    
        scrollPane.setPreferredSize(new Dimension(1060, 250));  

        scrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(177, 148, 112);
                this.thumbDarkShadowColor = new Color(232, 223, 202);
                this.thumbLightShadowColor = new Color(232, 223, 202);
                this.trackColor = new Color(232, 223, 202);
            }
        });
        return scrollPane;
    }
    

    private JPanel buyPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 50, 1060, 280);
        panel.setBackground(Color.decode(getColor1()));

        JLabel label = new JLabel();
        label.setText("Want to Buy?");
        panel.add(label);
        
        JScrollPane items = items_in_shop();
        panel.add(items);

        return panel;
    }

    private JPanel sellPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 330, 1060, 280);
        panel.setBackground(Color.decode(getColor1()));

        JLabel label = new JLabel();
        label.setText("Want to Sell?");
        panel.add(label);

        JScrollPane items = items_in_deck();
        panel.add(items);
        return panel;
    }

    private JPanel backPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 610, 1060, 50);
        panel.setBackground(Color.decode(getColor1()));

        JButton button = new JButton();
        try {
            BufferedImage img = ImageIO.read(new File("src/main/java/org/gui/assets/shovel.png"));
            Image resizedImage = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImage);

            button.setIcon(icon);
            button.setPreferredSize(new Dimension(25, 25)); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(Color.decode(getColor1()));
        button.setForeground(Color.decode(getColor1()));
        panel.add(button);
        return panel;
    }

    public JPanel page_shop() {
        JPanel panel = new JPanel(); 
        panel.setLayout(null);
        panel.setBounds(0, 0, 1060, 660);
        panel.add(titlePanel());
        panel.add(buyPanel());
        panel.add(sellPanel());
        panel.add(backPanel());
        return panel;
    }

}
