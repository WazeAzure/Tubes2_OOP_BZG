package org.gui;

import org.gameEngine.GameEngine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Load extends Default{
    private App app;

    public Load(App app) {
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

    private JPanel loadComponent() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 500, 250);
        panel.setOpaque(false);

        JLabel title = new JLabel("Load");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setBounds(0, 35, 500, 50);
        panel.add(title);

        JLabel label = new JLabel("Folder: ");
        label.setFont(new Font("Serif", Font.PLAIN, 20));

        JTextField folder = new JTextField();
        folder.setPreferredSize(new Dimension(150, 30));
        folder.setFont(new Font("Arial", Font.BOLD, 14));
        folder.setBackground(Color.decode(getColor3()));
        folder.setForeground(Color.BLACK);

        String[] format = {"txt", "xml", "json"};
                JComboBox<String> dropdown = new JComboBox<>(format);

        dropdown.setPreferredSize(new Dimension(150, 30));
        dropdown.setFont(new Font("Arial", Font.BOLD, 14));
        dropdown.setBackground(Color.decode(getColor3()));
        dropdown.setForeground(Color.BLACK);
        
        JPanel entry = new JPanel();
        entry.setLayout(new BoxLayout(entry, BoxLayout.X_AXIS));
        entry.setBounds(80, 100, 340, 30);
        entry.add(label);
        entry.add(Box.createHorizontalStrut(10));
        entry.add(folder);
        entry.add(Box.createHorizontalStrut(10));
        entry.add(dropdown);
        entry.setOpaque(false);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBounds(80, 160, 340, 50);
        buttonPanel.setOpaque(false); 

        JButton backButton = new JButton();
        backButton.setBackground(Color.decode(app.getColor1()));
        try {
            BufferedImage img = ImageIO.read(new File("src/main/java/org/gui/assets/back.png"));
            Image resizedImage = img.getScaledInstance(120, 40, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImage);

            backButton.setIcon(icon);
            backButton.setPreferredSize(new Dimension(120, 40)); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        buttonPanel.add(backButton);

        JButton loadButton = new JButton();
        loadButton.setBackground(Color.decode(app.getColor1()));
        try {
            BufferedImage img = ImageIO.read(new File("src/main/java/org/gui/assets/upload.png"));
            Image resizedImage = img.getScaledInstance(120, 40, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImage);

            loadButton.setIcon(icon);
            loadButton.setPreferredSize(new Dimension(120, 40)); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        buttonPanel.add(loadButton);

        backButton.addActionListener(e -> backB());
        loadButton.addActionListener(e -> {
            String selectedFormat = (String) dropdown.getSelectedItem();
            String folderPath = folder.getText();
            loadB(panel, selectedFormat, folderPath);
        });

        panel.add(entry);
        panel.add(buttonPanel);
        panel.add(title);

        return panel;
    }

    public void loadB(Component panel, String selectedFormat, String path) {
        JOptionPane.showMessageDialog(panel, "Load " + path + selectedFormat );
        // TODO: Add load logic
        String sound_track = "src/main/java/org/gui/assets/save.wav";
        Music se = new Music();
        se.setFile(sound_track);
        se.play();

        App.gameEngine.loadSaveFile("testfile" + File.separator + path, selectedFormat);

    }

    public void backB() {
        Farm farm = new Farm(app);
        app.main_panel.removeAll();
        app.main_panel.add(farm);
        app.main_panel.revalidate();
        app.main_panel.repaint();
        String sound_track = "src/main/java/org/gui/assets/horse.wav";
        Music se = new Music();
        se.setFile(sound_track);
        se.play();
    }

    private JPanel load() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(280, 205, 500, 250);
        panel.setOpaque(false);
        panel.add(loadComponent());
        return panel;
    }

    public JPanel page_load() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1060, 660);

        ImagePanel imagePan = new ImagePanel(null);
        imagePan.setBounds(0, 0, 1060, 660);

        try {
            BufferedImage myImage = ImageIO.read(new File("src/main/java/org/gui/assets/bgv3.png"));
            imagePan.setImage(myImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        panel.add(imagePan);
        String sound_track = "src/main/java/org/gui/assets/save.wav";
        Music se = new Music();
        se.setFile(sound_track);
        se.play();

        JPanel loadPanel = load();
        panel.add(loadPanel);
        panel.setComponentZOrder(loadPanel, 0); 

        return panel;
    }
}
