package org.gui;

import org.grid.Grid;
import org.kartu.Kartu;
import org.ladang.Ladang;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.util.HashMap;
import java.util.Map;

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
        Map<String, Kartu> listKartu = App.gameEngine.getCurrentPemain().getActiveDeck().getListKartu();
        for(int i=0; i<6; i++){
            DeckAktifPlaceHolder deckAktifPlaceHolder = new DeckAktifPlaceHolder((i+1)*widthPadding + (i*widthCard),widthPadding,widthCard,heightCard,i);
            new CardDropTargetListener(deckAktifPlaceHolder,farm);
            this.add(deckAktifPlaceHolder);
            if (listKartu.get(Grid.parseToKey(i, 0)) != null) {
                CardPanel cardPanel = new CardPanel(0,0, widthCard, heightCard, listKartu.get(Grid.parseToKey(i, 0)));
                deckAktifPlaceHolder.setPanelCard(cardPanel);
                var ds = new DragSource();
                ds.createDefaultDragGestureRecognizer(cardPanel, DnDConstants.ACTION_MOVE, farm);
            }
        }
    }
}
