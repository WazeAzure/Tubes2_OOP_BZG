package org.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.io.*;

public class CardPanel extends JPanel implements Transferable, Serializable {
    int x;
    int y;
    int width;
    int height;

    private String nama = "NAMA";
    private String gambar = "GAMBAR";
    protected static final DataFlavor card = new DataFlavor(CardPanel.class, "Card");
    protected static final DataFlavor[] supportedFlavors = {card};

    public CardPanel(int x, int y, int width, int height) {
        this.setLayout(null);
        this.nama = nama;
        this.gambar = gambar;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

        JPanel panelGambar = new JPanel();
        panelGambar.setLayout(null);
        panelGambar.setBackground(Color.CYAN);
        panelGambar.setBounds(widthPadding,heightPadding,widthImage,heightImage);
        this.add(panelGambar);

        JPanel panelNama = new JPanel();
        panelNama.setLayout(null);
        panelNama.setBackground(Color.CYAN);
        panelNama.setBounds(widthPadding,2*heightPadding+heightImage,widthImage,heightNama);
        this.add(panelNama);
        this.setBackground(Color.RED);
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
            return new CardPanel(this.x,this.y,this.width,this.height);
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
