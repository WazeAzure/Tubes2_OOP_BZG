package org.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.io.*;

public class CardPanel extends JPanel implements Transferable, Serializable {
    private String nama;
    private String gambar;
    protected static final DataFlavor card = new DataFlavor(CardPanel.class, "Card");
    protected static final DataFlavor[] supportedFlavors = {card};

    public CardPanel() {
        this.setLayout(null);
        this.nama = nama;
        this.gambar = gambar;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel labelGambar = new JLabel(this.gambar);
        labelGambar.setBounds(0,0,30,30);
        this.add(labelGambar, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel labelNama = new JLabel(this.nama);
        labelNama.setBounds(0,80,30,30);
        this.add(labelNama, gbc);
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
            return this;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
