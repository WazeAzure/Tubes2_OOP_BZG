package org.gui;

public class DeckAktifPlaceHolder extends CardPlaceholder{
    private final int idx;

    public DeckAktifPlaceHolder(int x, int y, int width, int height, int idx){
        super(x, y, width, height);
        this.idx = idx;
    }
}
