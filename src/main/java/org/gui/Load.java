package org.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Load {
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
                g.drawImage(image, 0, 0, 1060, 660, this);
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
        panel.setBackground(Color.decode(app.getColor3()));

        JLabel title = new JLabel("Load");
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
        entry.setBackground(Color.decode(app.getColor3()));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBounds(80, 165, 340, 35);
        JButton backButton = new JButton("Back");
        JButton loadButton = new JButton("Load");
        buttonPanel.add(backButton);
        buttonPanel.add(loadButton);

        backButton.addActionListener(e -> backB());
        loadButton.addActionListener(e -> {
            String selectedFormat = (String) dropdown.getSelectedItem();
            loadB(panel, selectedFormat);
        });

        buttonPanel.add(backButton);
        buttonPanel.add(loadButton);
        buttonPanel.setBackground(Color.decode(app.getColor3()));

        panel.add(title);
        panel.add(entry);
        panel.add(buttonPanel);

        return panel;
    }

    public void loadB(Component panel, String selectedFormat) {
        JOptionPane.showMessageDialog(panel, "Load " + selectedFormat);
        // TODO: Add algoritma load
    }

    public void backB() {
        app.page = 1;
        app.updateMainPanel();
    }

    private JPanel load() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(280, 205, 500, 250);
        panel.setBackground(Color.BLUE);
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
            BufferedImage myImage = ImageIO.read(new File("src\\main\\java\\org\\gui\\assets\\bg.png"));
            imagePan.setImage(myImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        panel.add(imagePan);

        JPanel loadPanel = load();
        panel.add(loadPanel);
        panel.setComponentZOrder(loadPanel, 0);

        return panel;
    }
}
