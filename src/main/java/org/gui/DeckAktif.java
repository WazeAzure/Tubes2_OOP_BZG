package org.gui;

import javax.swing.*;
import java.awt.*;

public class DeckAktif extends JPanel {
    private Farm farm;

    private final int widthDeckAktif = 520;
    private int heightDeckAktif = 100;

    private final int widthCard = 75;
    private final int heightCard = 100;

    public DeckAktif(Farm farm) {
        this.farm = farm;
        this.setLayout(null);
        this.setBackground(Color.decode("#F1E4C3"));
        int widthPadding = Math.floorDiv(widthDeckAktif-(6*widthCard),7);
        heightDeckAktif+=(2*widthPadding);
        this.setBounds(0, 565, widthDeckAktif, heightDeckAktif);
        for(int i=0; i<6; i++){
            DeckAktifPlaceHolder deckAktifPlaceHolder = new DeckAktifPlaceHolder((i+1)*widthPadding + (i*widthCard),widthPadding,widthCard,heightCard,i);
            new CardDropTargetListener(deckAktifPlaceHolder,farm);
            this.add(deckAktifPlaceHolder);
        }
    }
}
