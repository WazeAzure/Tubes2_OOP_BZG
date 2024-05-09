package org.kartu.harvestable;

import org.kartu.Kartu;

public abstract class Harvestable extends Kartu {
    int berat;
    int beratEffect;

    public Harvestable(String nama, String kategori, String imageURL, Integer berat, Integer beratEffect) {
        super(nama, kategori, imageURL);
        this.berat = berat;
        this.beratEffect = beratEffect;
    }
    public abstract void panen();
    public abstract void tumbuh();
}
