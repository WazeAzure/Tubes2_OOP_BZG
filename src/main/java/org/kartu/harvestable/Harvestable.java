package org.kartu.harvestable;

import org.kartu.Kartu;
import org.kartu.item.Item;

import java.util.ArrayList;
import java.util.List;

public abstract class Harvestable extends Kartu {
    Integer berat;
    Integer beratEfek;
    Integer beratPanen;
    List<Item> itemAktif = new ArrayList<>();

    public Harvestable(String nama, String kategori, String imageURL, Integer beratPanen) {
        super(nama, kategori, imageURL);
        this.beratPanen = beratPanen;
        this.berat = 0;
        this.beratEfek = 0;
    }

    public int getBerat() {
        return berat;
    }

    public int getBeratEfek() {
        return beratEfek;
    }

    public int getBeratPanen() {
        return beratPanen;
    }

    public List<Item> getItemAktif() {
        return itemAktif;
    }

    public abstract void panen();
    public abstract void tumbuh();
}
