package org.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Shop extends Default {
    private App app;

    public Shop(App app) {
        this.app = app;
    }

    private class ImagePanel extends JComponent {
        private Image image;

        public ImagePanel(Image image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        }

        public void setImage(Image newImage) {
            this.image = newImage;
            repaint();
        }
    }

    private JPanel titlePanel() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1060, 50);
        panel.setOpaque(false);

        JLabel label = new JLabel("Welcome to Shop!");
        label.setFont(new Font("Serif", Font.BOLD, 30));
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label);

        return panel;
    }

    private JScrollPane items_in_shop() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setBackground(Color.decode(app.getColor2()));

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
        panel.setBackground(Color.decode(app.getColor2()));

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
        panel.setBackground(Color.decode(app.getColor1()));
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        JLabel label = new JLabel("Want to Buy?");
        label.setFont(new Font("Serif", Font.BOLD, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label, BorderLayout.NORTH);

        JScrollPane items = items_in_shop();
        panel.add(items, BorderLayout.CENTER);

        return panel;
    }

    private JPanel sellPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 330, 1060, 280);
        panel.setBackground(Color.decode(app.getColor1()));
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        JLabel label = new JLabel("Want to Sell?");
        label.setFont(new Font("Serif", Font.BOLD, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label, BorderLayout.NORTH);

        JScrollPane items = items_in_deck();
        panel.add(items, BorderLayout.CENTER);
        return panel;
    }

    private JPanel backPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 610, 1060, 50);
        panel.setBackground(Color.decode(app.getColor1()));
        panel.setOpaque(false);

        JButton button = new JButton();
        try {
            BufferedImage img = ImageIO.read(new File("src/main/java/org/gui/assets/back.png"));
            Image resizedImage = img.getScaledInstance(120, 40, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImage);

            button.setIcon(icon);
            button.setPreferredSize(new Dimension(120, 40));
        } catch (Exception e) {
            e.printStackTrace();
        }
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(Color.decode(app.getColor1()));
        button.setForeground(Color.decode(app.getColor1()));
        button.addActionListener(e -> {
            app.main_panel.removeAll();
            Farm farm = new Farm(app);
            app.main_panel.add(farm);
            app.main_panel.revalidate();
            app.main_panel.repaint();
        });
        panel.add(button);
        return panel;
    }

    public JPanel page_shop() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1060, 660);
        panel.setOpaque(false);

        ImagePanel imagePan = new ImagePanel(null);
        imagePan.setBounds(0, 0, 1060, 660);
        panel.add(imagePan);

        try {
            BufferedImage myImage = ImageIO.read(new File("src/main/java/org/gui/assets/shopbg.jpg"));
            imagePan.setImage(myImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel titleP = titlePanel();
        JPanel buyP = buyPanel();
        JPanel sellP = sellPanel();
        JPanel backP = backPanel();

        panel.add(titleP);
        panel.add(buyP);
        panel.add(sellP);
        panel.add(backP);

        // Set the Z-order to ensure the background is at the back
        panel.setComponentZOrder(imagePan, panel.getComponentCount() - 1);
        panel.setComponentZOrder(titleP, 0);
        panel.setComponentZOrder(buyP, 0);
        panel.setComponentZOrder(sellP, 0);
        panel.setComponentZOrder(backP, 0);

        return panel;
    }
}
