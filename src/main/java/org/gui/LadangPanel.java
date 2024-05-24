package org.gui;

import org.ladang.Ladang;
import org.gameEngine.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.*;

public class LadangPanel extends JPanel {
    private Farm farm;
    private final JFrame rootFrame;

    public final static int widthLadang = 520;
    public final static int heightLadang = 550;

    public static boolean isExpanded;
    public static boolean isShrinked;
    // Normal (4x5) memiliki kode = 0
    public static final int nRowNormal = 4;
    public static final int nColNormal = 5;
    public final static int widthPetakNormal = 90;
    public final static int widthPaddingNormal = Math.floorDiv(widthLadang-(nColNormal*widthPetakNormal),nColNormal+1);
    public static final int heightPetakNormal = 120;
    public final static int heightPaddingNormal = Math.floorDiv(heightLadang-(nRowNormal*heightPetakNormal),nRowNormal+1);

    // Expanded (5x6) memiliki kode = 1
    public static final int nRowExpanded = 5;
    public static final int nColExpanded = 6;
    public static final int widthPetakExpanded = 75;
    public final static int widthPaddingExpanded= Math.floorDiv(widthLadang-(nColExpanded*widthPetakExpanded),nColExpanded+1);
    public static final int heightPetakExpanded = 96;
    public final static int heightPaddingExpanded = Math.floorDiv(heightLadang-(nRowExpanded*heightPetakExpanded),nRowExpanded+1);
    // Shrinked (3x4) memiliki kode = -1
    public static final int nRowShrinked = 3;
    public static final int nColShrinked = 4;
    public static final int widthPetakShrinked= 120;
    public final static int widthPaddingShrinked = Math.floorDiv(widthLadang-(nColShrinked*widthPetakShrinked),nColShrinked+1);
    public static final int heightPetakShrinked= 170;
    public final static int heightPaddingShrinked = Math.floorDiv(heightLadang-(nRowShrinked*heightPetakShrinked),nRowShrinked+1);

//    private final int widthLadang = 515;
//    private final int heightLadang = 545;

    public LadangPanel(Farm farm, JFrame rootFrame) {
        // Inisialisasi awal pasti dalam keadaan normal
        this.farm = farm;
        this.rootFrame = rootFrame;
        this.setLayout(null);
        this.setBounds(0,0,widthLadang,heightLadang);
        this.setBackground(Color.decode("#F1E4C3"));
        normalRender();
//        expandedRender();
//        shrinkedRender();
    }

    public void renderLadang(){
        // Render berdasarkan isi dari list class ladang dan tipe ladang (normal/expanded/shrinked)

        // ladang normal
        normalRender();
        // ladang expanded

        // ladang shrinked

    }

    private void normalRender(){
        this.removeAll();
        Ladang showLadang;
        if (Farm.currentLadang == 0) {
            showLadang = App.gameEngine.getCurrentPemain().getLadang();
        } else {
            showLadang = App.gameEngine.getCurrentLawan().getLadang();
        }
        for(int i=0; i<nRowNormal; i++){
            for(int j=0; j<nColNormal; j++){
                int x = (j+1)*widthPaddingNormal + (j*widthPetakNormal);
                int y = (i+1)*heightPaddingNormal + (i*heightPetakNormal);
                PetakLadangPlaceholder petakLadang = new PetakLadangPlaceholder(x,y,widthPetakNormal,heightPetakNormal,i,j,this.rootFrame);
                this.add(petakLadang);
                new CardDropTargetListener(petakLadang,farm);
                if (showLadang.getObject(Ladang.parseToKey(j, i)) != null) {
                    CardPanel cardPanel = new CardPanel(0,0,petakLadang.getWidth(),petakLadang.getHeight(), showLadang.getObject(Ladang.parseToKey(j, i)));
                    petakLadang.setPanelCard(cardPanel);
                    var ds = new DragSource();
                    ds.createDefaultDragGestureRecognizer(cardPanel, DnDConstants.ACTION_MOVE, farm);
                }
            }
        }
        this.repaint();
        this.revalidate();
    }

    private void expandedRender(){
        this.removeAll();
        Ladang showLadang;
        if (Farm.currentLadang == 0) {
            showLadang = App.gameEngine.getCurrentPemain().getLadang();
        } else {
            showLadang = App.gameEngine.getCurrentLawan().getLadang();
        }
        for(int i=0; i<nRowExpanded; i++){
            for(int j=0; j<nColExpanded; j++){
                int x = (j+1)*widthPaddingExpanded + (j*widthPetakExpanded);
                int y = (i+1)*heightPaddingExpanded + (i*heightPetakExpanded);
                PetakLadangPlaceholder petakLadang = new PetakLadangPlaceholder(x,y,widthPetakExpanded,heightPetakExpanded,i,j,this.rootFrame);
                this.add(petakLadang);
                new CardDropTargetListener(petakLadang,farm);
                if (showLadang.getObject(Ladang.parseToKey(i, j)) != null) {
                    CardPanel cardPanel = new CardPanel(x,y,petakLadang.getWidth(),petakLadang.getHeight(), showLadang.getObject(Ladang.parseToKey(i, j)));
                    petakLadang.setPanelCard(cardPanel);
                    var ds = new DragSource();
                    ds.createDefaultDragGestureRecognizer(cardPanel, DnDConstants.ACTION_MOVE, farm);
                }
            }
        }
        this.repaint();
        this.revalidate();
    }

    private void shrinkedRender(){
        this.removeAll();
        Ladang showLadang;
        if (Farm.currentLadang == 0) {
            showLadang = App.gameEngine.getCurrentPemain().getLadang();
        } else {
            showLadang = App.gameEngine.getCurrentLawan().getLadang();
        }
        for(int i=0; i<nRowShrinked; i++){
            for(int j=0; j<nColShrinked; j++){
                int x = (j+1)*widthPaddingShrinked + (j*widthPetakShrinked);
                int y = (i+1)*heightPaddingShrinked + (i*heightPetakShrinked);
                PetakLadangPlaceholder petakLadang = new PetakLadangPlaceholder(x,y,widthPetakShrinked,heightPetakShrinked,i,j,this.rootFrame);
                this.add(petakLadang);
                new CardDropTargetListener(petakLadang,farm);
                if (showLadang.getObject(Ladang.parseToKey(i, j)) != null) {
                    CardPanel cardPanel = new CardPanel(x,y,petakLadang.getWidth(),petakLadang.getHeight(), showLadang.getObject(Ladang.parseToKey(i, j)));
                    petakLadang.setPanelCard(cardPanel);
                    var ds = new DragSource();
                    ds.createDefaultDragGestureRecognizer(cardPanel, DnDConstants.ACTION_MOVE, farm);
                }
            }
        }
        this.repaint();
        this.revalidate();
    }
}
