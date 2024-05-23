package org.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Plugin {
    private App app;
    private File selectedFile;

    public Plugin(App app) {
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

    private JPanel pluginComponent() {
        JPanel panel = new JPanel();
        panel.setOpaque(false); // Make the panel transparent
        panel.setLayout(null);
        panel.setBounds(0, 0, 500, 250);

        JLabel title = new JLabel("Plugin");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setBounds(0, 20, 500, 50);
        panel.add(title);

        JTextField fileNameField = new JTextField("No file selected");
        fileNameField.setEditable(false);
        fileNameField.setBounds(50, 100, 280, 30);
        panel.add(fileNameField);

        JButton chooseFileButton = new JButton("Choose File");
        chooseFileButton.setBounds(350, 100, 100, 30);
        chooseFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnVal = fileChooser.showOpenDialog(panel);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                fileNameField.setText(selectedFile.getName());
            }
        });
        panel.add(chooseFileButton);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.setOpaque(false); // Make the panel transparent
        buttonPanel.setBounds(20, 150, 460, 40);

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

        JButton uploadButton = new JButton("Load");
        buttonPanel.add(uploadButton);

        backButton.addActionListener(e -> backB());
        uploadButton.addActionListener(e -> {
            if (selectedFile != null) {
                uploadB(panel, selectedFile);
            } else {
                JOptionPane.showMessageDialog(panel, "No file selected!");
            }
        });

        panel.add(buttonPanel);

        return panel;
    }

    public void uploadB(Component panel, File file) {
        if (file != null && file.exists()) {
            JOptionPane.showMessageDialog(panel, "Open file: " + file.getName());
            // TODO: Add plugin logic
        } else {
            JOptionPane.showMessageDialog(panel, "File does not exist.");
        }
    }

    public void backB() {
        app.page = 1;
        app.updateMainPanel();
    }

    private JPanel plugin() {
        JPanel panel = new JPanel();
        panel.setOpaque(false); // Make the panel transparent
        panel.setLayout(null);
        panel.setBounds(280, 205, 500, 250);
        panel.add(pluginComponent());
        return panel;
    }

    public JPanel page_plugin() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 0, 0, 0)); // Transparent background
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

        JPanel pluginPanel = plugin();
        panel.add(pluginPanel);
        panel.setComponentZOrder(pluginPanel, 0);

        return panel;
    }
}
