package org.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import org.gameEngine.GameEngine;
import org.kartu.Kartu;
import org.toko.*;

public class Shop extends Default {
    private App app;
    private Toko toko;

    public Shop(App app, Toko toko) {
        this.app = app;
        this.toko = toko;
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

        int gulden = App.gameEngine.getCurrentPemain().getUang();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBounds(0, 0, 1060, 50);
        panel.setOpaque(false);

        JLabel labelgulden = new JLabel("Your gulden: " + gulden);
        
        labelgulden.setFont(new Font("Serif", Font.BOLD, 25));
        labelgulden.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        panel.add(labelgulden);

        return panel;
    }

    private JScrollPane items_in_shop() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setBackground(Color.decode(app.getColor2()));

        java.util.List<Kartu> list = toko.getListCard();

        for (Kartu kartu : list) {
            ItemCard card = new ItemCard(kartu.getNama(), kartu.getImageURL(), toko.totalHarga(kartu.getNama(), 1), toko.getStok(kartu.getNama()), app);
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
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBackground(Color.decode(app.getColor2()));
        
        for (Kartu k: App.gameEngine.getCurrentPemain().getActiveDeck().getListKartu().values()) {
            if (k != null) {
                if (k.getKategori().equals("Produk Tanaman") || k.getKategori().equals("Produk Hewan") ){
                    // for (int i = 0; i < 6; i++) {
                    DeckCard card = new DeckCard(k.getNama(), k.getImageURL(), toko.totalHarga(k.getNama(), 1), 160, 225, app);
                    JPanel cardpanel = card.createCard();
                    panel.add(cardpanel);
                    // }  
                }
            }
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

            String sound_track = "src/main/java/org/gui/assets/horse.wav";
            Music se = new Music();
            se.setFile(sound_track);
            se.play();

            app.main_panel.removeAll();
            app.main_panel.add(App.farm);
            App.farm.render();
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

        panel.setComponentZOrder(imagePan, panel.getComponentCount() - 1);
        panel.setComponentZOrder(titleP, 0);
        panel.setComponentZOrder(buyP, 0);
        panel.setComponentZOrder(sellP, 0);
        panel.setComponentZOrder(backP, 0);

        return panel;
    }
}
