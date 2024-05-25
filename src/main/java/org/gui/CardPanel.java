package org.gui;

import org.kartu.Kartu;
import org.kartu.harvestable.Harvestable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;

public class CardPanel extends JPanel implements Transferable, Serializable {
    int x;
    int y;
    int width;
    int height;
    Kartu kartu;

    private String nama = "NAMA";
    private String gambar = "GAMBAR";
    protected static final DataFlavor card = new DataFlavor(CardPanel.class, "Card");
    protected static final DataFlavor[] supportedFlavors = {card};

    public CardPanel(int x, int y, int width, int height, Kartu kartu) {
        this.setLayout(null);
        this.kartu = kartu;
        this.nama = kartu.getNama();
        this.gambar = kartu.getImageURL();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.setBounds(x, y, width, height);
        render();
    }

    public void render() {
        this.width=this.getWidth();
        this.height=this.getHeight();
        this.removeAll();
        this.setBounds(x, y, width, height);
        int widthImage = Math.floorDiv(this.getWidth()*7,9);
        int heightImage = widthImage;
        int heightNama = Math.floorDiv(this.getHeight()*5,24);
        int widthPadding = Math.floorDiv(this.getWidth()-widthImage,2);
        int heightPadding = Math.floorDiv(this.getHeight()-heightImage-heightNama,3);

        ImageIcon icon;
        try {
            BufferedImage img = ImageIO.read(new File("src/main/java/org/gui/assets/" + kartu.getImageURL() + ".png"));
            Image resizedImage = img.getScaledInstance(widthImage-7, heightImage-7, Image.SCALE_SMOOTH);
            icon = new ImageIcon(resizedImage);
        } catch (Exception e) {
            e.printStackTrace();
            icon = new ImageIcon();
        }
        JLabel imageLabel = new JLabel(icon);
        JPanel panelGambar = new JPanel();
        panelGambar.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelGambar.add(imageLabel);
        panelGambar.setBackground(Color.decode("#F1E4C3"));
        panelGambar.setBounds(widthPadding,heightPadding,widthImage,heightImage);
        this.add(panelGambar);

        JLabel labelNama = new JLabel(this.nama);
        labelNama.setFont(new Font("Arial", Font.PLAIN, 7));
        JPanel panelNama = new JPanel();
        panelNama.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelNama.setBackground(Color.decode("#C6A969"));
        panelNama.setBounds(widthPadding,2*heightPadding+heightImage,widthImage,heightNama);
        panelNama.add(labelNama);
        this.add(panelNama);
        this.setBackground(Color.decode("#C6A969"));
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return supportedFlavors;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(card);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(card)) {
            return new CardPanel(this.x, this.y, this.width, this.height, this.kartu);
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
