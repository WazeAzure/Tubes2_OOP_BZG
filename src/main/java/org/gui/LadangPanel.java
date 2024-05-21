package org.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.*;

public class LadangPanel extends JPanel {
    private Farm farm;

    private boolean isExpanded;
    private boolean isShrinked;
    // Normal (4x5) memiliki kode = 0
    private final int nRowNormal = 4;
    private final int nColNormal = 5;
    private final int widthPetakNormal = 90;
    private final int heightPetakNormal = 120;
    // Expanded (5x6) memiliki kode = 1
    private final int nRowExpanded = 5;
    private final int nColExpanded = 6;
    private final int widthPetakExpanded = 75;
    private final int heightPetakExpanded = 96;
    // Shrinked (3x4) memiliki kode = -1
    private final int nRowShrinked = 3;
    private final int nColShrinked = 4;
    private final int widthPetakShrinked= 120;
    private final int heightPetakShrinked= 170;

    private final int widthLadang = 520;
    private final int heightLadang = 550;
//    private final int widthLadang = 515;
//    private final int heightLadang = 545;

    public LadangPanel(Farm farm) {
        // Inisialisasi awal pasti dalam keadaan normal
        this.farm = farm;
        this.setLayout(null);
        this.setBounds(0,0,widthLadang,heightLadang);
        this.setBackground(Color.PINK);
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
        for(int i=0; i<nRowNormal; i++){
            for(int j=0; j<nColNormal; j++){
                int widthPadding = Math.floorDiv(widthLadang-(nColNormal*widthPetakNormal),nColNormal+1);
                int heightPadding = Math.floorDiv(heightLadang-(nRowNormal*heightPetakNormal),nRowNormal+1);
                int x = (j+1)*widthPadding + (j*widthPetakNormal);
                int y = (i+1)*heightPadding + (i*heightPetakNormal);
                PetakLadangPlaceholder petakLadang = new PetakLadangPlaceholder(x,y,widthPetakNormal,heightPetakNormal,i,j);
                this.add(petakLadang);
                new CardDropTargetListener(petakLadang,farm);
                if(i==0 && j==0){
                    CardPanel cardPanel = new CardPanel(0,0,petakLadang.getWidth(),petakLadang.getHeight());
                    petakLadang.setPanelCard(cardPanel);
                    var ds = new DragSource();
                    ds.createDefaultDragGestureRecognizer(cardPanel, DnDConstants.ACTION_MOVE, farm);
                }
                if(i==0 && j==1){
                    CardPanel cardPanel = new CardPanel(0,0,petakLadang.getWidth(),petakLadang.getHeight());
                    petakLadang.setPanelCard(cardPanel);
                    var ds = new DragSource();
                    ds.createDefaultDragGestureRecognizer(cardPanel, DnDConstants.ACTION_MOVE, farm);
                } if(i==0 && j==2){
                    CardPanel cardPanel = new CardPanel(0,0,petakLadang.getWidth(),petakLadang.getHeight());
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
        for(int i=0; i<nRowExpanded; i++){
            for(int j=0; j<nColExpanded; j++){
                int widthPadding = Math.floorDiv(widthLadang-(nColExpanded*widthPetakExpanded),nColExpanded+1);
                int heightPadding = Math.floorDiv(heightLadang-(nRowExpanded*heightPetakExpanded),nRowExpanded+1);
                int x = (j+1)*widthPadding + (j*widthPetakExpanded);
                int y = (i+1)*heightPadding + (i*heightPetakExpanded);
                PetakLadangPlaceholder petakLadang = new PetakLadangPlaceholder(x,y,widthPetakExpanded,heightPetakExpanded,i,j);
                this.add(petakLadang);
                new CardDropTargetListener(petakLadang,farm);
                if(i==0 && j==0){
                    CardPanel cardPanel = new CardPanel(0,0,petakLadang.getWidth(),petakLadang.getHeight());
                    petakLadang.setPanelCard(cardPanel);
                    var ds = new DragSource();
                    ds.createDefaultDragGestureRecognizer(cardPanel, DnDConstants.ACTION_MOVE, farm);
                }
                if(i==0 && j==1){
                    CardPanel cardPanel = new CardPanel(0,0,petakLadang.getWidth(),petakLadang.getHeight());
                    petakLadang.setPanelCard(cardPanel);
                    var ds = new DragSource();
                    ds.createDefaultDragGestureRecognizer(cardPanel, DnDConstants.ACTION_MOVE, farm);
                } if(i==0 && j==2){
                    CardPanel cardPanel = new CardPanel(0,0,petakLadang.getWidth(),petakLadang.getHeight());
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
        for(int i=0; i<nRowShrinked; i++){
            for(int j=0; j<nColShrinked; j++){
                int widthPadding = Math.floorDiv(widthLadang-(nColShrinked*widthPetakShrinked),nColShrinked+1);
                int heightPadding = Math.floorDiv(heightLadang-(nRowShrinked*heightPetakShrinked),nRowShrinked+1);
                int x = (j+1)*widthPadding + (j*widthPetakShrinked);
                int y = (i+1)*heightPadding + (i*heightPetakShrinked);
                PetakLadangPlaceholder petakLadang = new PetakLadangPlaceholder(x,y,widthPetakShrinked,heightPetakShrinked,i,j);
                this.add(petakLadang);
                new CardDropTargetListener(petakLadang,farm);
                if(i==0 && j==0){
                    CardPanel cardPanel = new CardPanel(0,0,petakLadang.getWidth(),petakLadang.getHeight());
                    petakLadang.setPanelCard(cardPanel);
                    var ds = new DragSource();
                    ds.createDefaultDragGestureRecognizer(cardPanel, DnDConstants.ACTION_MOVE, farm);
                }
                if(i==0 && j==1){
                    CardPanel cardPanel = new CardPanel(0,0,petakLadang.getWidth(),petakLadang.getHeight());
                    petakLadang.setPanelCard(cardPanel);
                    var ds = new DragSource();
                    ds.createDefaultDragGestureRecognizer(cardPanel, DnDConstants.ACTION_MOVE, farm);
                } if(i==0 && j==2){
                    CardPanel cardPanel = new CardPanel(0,0,petakLadang.getWidth(),petakLadang.getHeight());
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
