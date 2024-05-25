package org.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Save extends Default{
    private App app;

    public Save(App app) {
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
                g.drawImage(image, 0, 0, 1060, 660, this);
            }
        }

        public void setImage(Image newImage) {
            this.image = newImage;
            repaint();
        }
    }

    private JPanel saveComponent() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode(app.getColor3()));
        panel.setLayout(null);
        panel.setBounds(0, 0, 500, 250);
        panel.setOpaque(false);

        JLabel title = new JLabel("Save");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setBounds(0, 35, 500, 50);

        JLabel label = new JLabel("Folder: ");
        label.setFont(new Font("Serif", Font.PLAIN, 20));
        label.setOpaque(false);

        JTextField folder = new JTextField();
        folder.setPreferredSize(new Dimension(150, 30));
        folder.setFont(new Font("Arial", Font.BOLD, 14));
        folder.setBackground(Color.decode(getColor3()));
        folder.setForeground(Color.BLACK);

        String[] format = {"txt", "xml", "json"};
        JComboBox<String> dropdown = new JComboBox<>(format);
        dropdown.setPreferredSize(new Dimension(150, 30));

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
        entry.setBackground(Color.decode(getColor1()));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBounds(80, 150, 340, 50);
        buttonPanel.setBackground(Color.decode(getColor1()));
        
        JButton backButton = new JButton();
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

        JButton saveButton = new JButton();
        try {
            BufferedImage img = ImageIO.read(new File("src/main/java/org/gui/assets/save.png"));
            Image resizedImage = img.getScaledInstance(120, 40, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImage);

            saveButton.setIcon(icon);
            saveButton.setPreferredSize(new Dimension(120, 40)); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        buttonPanel.add(saveButton);

        buttonPanel.add(backButton);
        buttonPanel.add(saveButton);
        buttonPanel.setBackground(Color.decode(app.getColor3()));
        buttonPanel.setOpaque(false);

        backButton.addActionListener(e -> backB());
        saveButton.addActionListener(e -> {
            String selectedFormat = (String) dropdown.getSelectedItem();
            String path = (String) folder.getText();
            saveB(panel, selectedFormat, path);
        });

        panel.add(title);
        panel.add(entry);
        panel.add(buttonPanel);

        return panel;
    }

    public void saveB(Component panel, String selectedFormat, String path) {
        JOptionPane.showMessageDialog(panel, "Save " + path + selectedFormat);
        // TODO: Add algoritma save
        String sound_track = "src/main/java/org/gui/assets/save.wav";
        Music se = new Music();
        se.setFile(sound_track);
        se.play();

        // add algorithm
        App.gameEngine.saveFile("testfile" + File.separator + path, selectedFormat);
    }

    public void backB() {
        app.main_panel.removeAll();
        app.main_panel.add(App.farm);
        App.farm.render();
        app.main_panel.revalidate();
        app.main_panel.repaint();
        String sound_track = "src/main/java/org/gui/assets/horse.wav";
        Music se = new Music();
        se.setFile(sound_track);
        se.play();
    }

    private JPanel save() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode(app.getColor3()));
        panel.setLayout(null);
        panel.setBounds(280, 205, 500, 250);
        panel.setBackground(Color.BLUE);
        panel.add(saveComponent());
        panel.setOpaque(false);
        return panel;
    }

    public JPanel page_save() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode(app.getColor3()));
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

        String sound_track = "src/main/java/org/gui/assets/save.wav";
        Music se = new Music();
        se.setFile(sound_track);
        se.play();
        panel.add(imagePan);

        JPanel savePanel = save();
        panel.add(savePanel);
        panel.setComponentZOrder(savePanel, 0);

        return panel;
    }
}
