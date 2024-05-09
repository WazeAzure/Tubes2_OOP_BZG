package org.kartu.harvestable;
import org.kartu.Kartu;
import org.kartu.item.Item;

import java.util.HashMap;

public abstract class Harvestable extends Kartu {
    protected Integer value;
    protected Integer valueEfek;
    protected Integer valuePanen;
    protected Integer valueToHarvest;
    protected String product;
    HashMap<String, Integer> itemAktif = new HashMap<>();

    public Harvestable(String nama, String kategori, String imageURL, Integer valuePanen, String product) {
        super(nama, kategori, imageURL);
        this.valuePanen = valuePanen;
        this.value = 0;
        this.valueEfek = 0;
        this.product = product;
    }

    public int getBerat() {
        return value;
    }

    public int getBeratEfek() {
        return valueEfek;
    }

    public int getBeratPanen() {
        return valuePanen;
    }

    public HashMap<String, Integer> getItemAktif() {
        return itemAktif;
    }

    public void addItemAktif(String item) {
        if(itemAktif.containsKey(item)) {
            itemAktif.put(item, itemAktif.get(item) + 1);
        } else {
            itemAktif.put(item, 1);
        }
    }

    public void setBerat(Integer value) {
        this.value = value;
    }

    public void setBeratEfek(Integer valueEfek) {
        this.valueEfek = valueEfek;
    }

    public abstract void applyEfek(String efek);

    public abstract void panen();

}
