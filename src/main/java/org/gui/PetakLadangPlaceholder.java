package org.gui;

import javax.swing.*;

public class PetakLadangPlaceholder extends CardPlaceholder {
    private int idx_i;
    private int idx_j;

    public PetakLadangPlaceholder(int x, int y, int w, int h, int i, int j) {
        super(x,y,w,h);
        this.idx_i = i;
        this.idx_j = j;
    }

    public void displayIJ(){
        System.out.println(idx_i);
        System.out.println(idx_j);
    }
}
