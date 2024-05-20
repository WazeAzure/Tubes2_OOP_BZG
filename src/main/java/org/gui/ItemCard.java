package org.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class ItemCard {
    private String name;
    private String img;
    private int price;
    private int stock;

    public ItemCard(String name, String img, int price, int stock){
        this.name = name;
        this.img = img;
        this.price = price;
        this.stock = stock;
    }

    public JPanel createCard() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new LineBorder(Color.BLACK, 1, true)); // Rounded border
        panel.setPreferredSize(new Dimension(150, 230));

        JLabel nameLabel = new JLabel(this.name);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        try {
            myImage = ImageIO.read(new File(img));
            imagePan.setImage(myImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel priceLabel = new JLabel("Price: $" + this.price);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        priceLabel.setBorder(new EmptyBorder(0, 0, 0, 0));

        JLabel stockLabel = new JLabel("Stock: " + this.stock);
        stockLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        stockLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        stockLabel.setBorder(new EmptyBorder(0, 0, 0, 0));

        JButton buyButton = new JButton("Buy Now!");
        buyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buyButton.setBackground(Color.GREEN);
        buyButton.setForeground(Color.WHITE);
        buyButton.setFont(new Font("Arial", Font.BOLD, 14));
        buyButton.setBorder(new EmptyBorder(10, 20, 10, 20));         
        
        panel.add(nameLabel);
        panel.add(imagePan);
        panel.add(priceLabel);
        panel.add(stockLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(buyButton);

        return panel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new FlowLayout());

        for (int i = 0; i < 5; i++) {
            ItemCard card = new ItemCard("Item " + (i + 1), "src\\main\\java\\org\\gui\\assets\\image.png", 5000, 2);
            JPanel cardPanel = card.createCard();
            frame.add(cardPanel);
        }

        frame.setVisible(true);
    }
}
