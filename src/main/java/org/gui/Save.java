package org.gui;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.crypto.spec.PBEKeySpec;
import javax.imageio.ImageIO;

public class Save extends Default {

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
        panel.setBackground(Color.decode(getColor3()));
        panel.setLayout(null);
        panel.setBounds(0, 0, 500, 250);
    
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
        entry.setBackground(Color.decode(getColor3()));
    
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBounds(80, 165, 340, 35); 
        JButton cancelButton = new JButton("Cancel");
        JButton saveButton = new JButton("Save");
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);
        buttonPanel.setBackground(Color.decode(getColor3()));

        cancelButton.addActionListener(e -> cancelB(panel));
        saveButton.addActionListener(e -> {
            String selectedFormat = (String) dropdown.getSelectedItem();
            saveB(panel, selectedFormat);
        });
        
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);
    
        panel.add(title);
        panel.add(entry);
        panel.add(buttonPanel); 
    
        return panel;
    }

    public void saveB(Component panel, String selectedFormat){
        JOptionPane.showMessageDialog(panel, "Save " + selectedFormat);
        // TO DO: Add algoritma save 
    }

    public void cancelB(Component panel){
        JOptionPane.showMessageDialog(panel, "Cancel");
        // TO DO: Add algoritma save 
    }
    
    private JPanel save() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode(getColor3()));
        panel.setLayout(null);
        panel.setBounds(280, 205, 500, 250);
        panel.setBackground(Color.BLUE);
        panel.add(saveComponent());
        return panel;
    }

    public JPanel page_save() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode(getColor3()));
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
    
        JPanel savePanel = save();
        panel.add(savePanel);
        panel.setComponentZOrder(savePanel, 0);
    
        return panel;
    }    
}
