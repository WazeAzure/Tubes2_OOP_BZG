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
        title.setBounds(0, 20, 500, 50);

        JLabel label = new JLabel("Folder: ");
        label.setFont(new Font("Serif", Font.PLAIN, 20));

        JTextField folder = new JTextField();
        folder.setPreferredSize(new Dimension(150, 30));

        String[] format = {"txt", "yaml", "png"};
        JComboBox<String> dropdown = new JComboBox<>(format);
        dropdown.setPreferredSize(new Dimension(150, 30));

        JPanel entry = new JPanel();
        entry.setLayout(new BoxLayout(entry, BoxLayout.X_AXIS));
        entry.setBounds(80, 100, 340, 30);
        entry.add(label);
        entry.add(Box.createHorizontalStrut(10));
        entry.add(folder);
        entry.add(Box.createHorizontalStrut(10));
        entry.add(dropdown);
        panel.setOpaque(false);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBounds(80, 165, 340, 50);
        
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

        JButton saveButton = new JButton();
        saveButton.setBackground(Color.decode(app.getColor1()));
        try {
            BufferedImage img = ImageIO.read(new File("src/main/java/org/gui/assets/back.png"));
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
    }

    public void backB() {
        Farm farm = new Farm(app);
        app.main_panel.removeAll();
        app.main_panel.add(farm);
        app.main_panel.revalidate();
        app.main_panel.repaint();
        String sound_track = "src\\main\\java\\org\\gui\\assets\\horse.wav";
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
            BufferedImage myImage = ImageIO.read(new File("src\\main\\java\\org\\gui\\assets\\bgv3.png"));
            imagePan.setImage(myImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        panel.add(imagePan);

        JPanel savePanel = save();
        panel.add(savePanel);
        panel.setComponentZOrder(savePanel, 0);

        return panel;
    }
}
