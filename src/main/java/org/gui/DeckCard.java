package org.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class DeckCard extends Default{
    private String name;
    private String img;
    private int price;
    private int stock;
    private int width;
    private int height;
    
    public DeckCard(String name, String img, int price, int stock, int width, int height){
        this.name = name;
        this.img = img;
        this.price = price;
        this.stock = stock;
        this.width = width;
        this.height = height;
    }

    public JPanel createCard() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);
        panel.setBorder(new LineBorder(Color.BLACK, 1, true)); 
        panel.setPreferredSize(new Dimension(this.width, this.height));

        JLabel nameLabel = new JLabel(this.name);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setBorder(new EmptyBorder(5, 0, 5, 0));

        BufferedImage myImage;

        class ImagePanel extends JComponent {
            private Image image;

            public ImagePanel(Image image) {
                this.image = image;
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    int diameter = Math.min(getWidth(), getHeight());
                    int x = (getWidth() - diameter) / 2;
                    int y = (getHeight() - diameter) / 2;
                    Ellipse2D.Double clip = new Ellipse2D.Double(x, y, diameter, diameter);
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.clip(clip);
                    g2.drawImage(image, x, y, diameter, diameter, this);
                    g2.dispose();
                }
            }
                 
            public void setImage(Image newImage) {
                this.image = newImage;
                repaint();
            }
        }

        ImagePanel imagePan = new ImagePanel(null);
        imagePan.setPreferredSize(new Dimension(100, 100));
        imagePan.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePan.setBorder(new EmptyBorder(0, 0, 10, 0));
        try {
            myImage = ImageIO.read(new File("src/main/java/org/gui/assets/" + img + ".png"));
            imagePan.setImage(myImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel priceLabel = new JLabel("Price: $" + this.price);
        priceLabel.setForeground(Color.BLACK);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        priceLabel.setBorder(new EmptyBorder(0, 0, 0, 0));

        JLabel stockLabel = new JLabel("Stock: " + this.stock);
        stockLabel.setForeground(Color.BLACK);
        stockLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        stockLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        stockLabel.setBorder(new EmptyBorder(0, 0, 0, 0));

        JButton buyButton = new JButton();
        try {
            BufferedImage img = ImageIO.read(new File("src/main/java/org/gui/assets/money.png"));
            Image resizedImage = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImage);

            buyButton.setIcon(icon);
            buyButton.setPreferredSize(new Dimension(25, 25)); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        buyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buyButton.setBackground(Color.decode(getColor1()));
        buyButton.setForeground(Color.decode(getColor1()));
        buyButton.setFont(new Font("Arial", Font.BOLD, 14));
        buyButton.setBorder(new EmptyBorder(10, 20, 10, 20)); 
        
        buyButton.addActionListener(e -> buy(panel));
        
        panel.setBackground(Color.decode(getColor1()));
        panel.add(nameLabel);
        panel.add(imagePan);
        panel.add(priceLabel);
        panel.add(stockLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(buyButton);

        return panel;
    }

    public void buy(Component parentComponent) {
        Object[] options = {"Sure", "Cancel"};
        int choice = JOptionPane.showOptionDialog(
                null, 
                "Are you sure to buy?", 
                this.name, 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                options, 
                options[0]
        );

        if (choice == JOptionPane.YES_OPTION) {
            System.out.println("Sure button clicked");
            String sound_track = "src/main/java/org/gui/assets/buy.wav";
            Music se = new Music();
            se.setFile(sound_track);
            se.play();
        } else if (choice == JOptionPane.NO_OPTION) {
            System.out.println("Cancel button clicked");
        }
    }
}
