package org.kartu.harvestable;
import org.config.Config;
import org.kartu.Kartu;
import org.kartu.item.Item;
import org.kartu.product.Product;

import java.util.HashMap;

public abstract class Harvestable extends Kartu {
    protected Integer value;
    protected Integer valueEfek;
    protected Integer valuePanen;
    protected Integer valueToHarvest;
    protected Product product;
    HashMap<String, Integer> itemAktif = new HashMap<>();

    public Harvestable(String nama, String kategori, String imageURL, Integer valuePanen) {
        super(nama, kategori, imageURL);
        this.valuePanen = valuePanen;
        this.value = 0;
        this.valueEfek = 0;
        if (nama.equals("Hiu Darat")) {
            this.product = Config.buildProduct("Sirip Hiu");
        } else if (nama.equals("Sapi")) {
            this.product = Config.buildProduct("Susu");
        } else if (nama.equals("Domba")) {
            this.product = Config.buildProduct("Daging Domba");
        } else if (nama.equals("Kuda")) {
            this.product = Config.buildProduct("Daging Kuda");
        } else if (nama.equals("Ayam")) {
            this.product = Config.buildProduct("Telur");
        } else if (nama.equals("Beruang")) {
            this.product = Config.buildProduct("Daging Beruang");
        } else if (nama.equals("Biji Jagung")) {
            this.product = Config.buildProduct("Jagung");
        } else if (nama.equals("Biji Labu")) {
            this.product = Config.buildProduct("Labu");
        } else if (nama.equals("Biji Stroberi")) {
            this.product = Config.buildProduct("Stroberi");
        }
    }

    public int getValue() {
        return value;
    }

    public int getValueEfek() {
        return valueEfek;
    }

    public int getValuePanen() {
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

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setValueEfek(Integer valueEfek) {
        this.valueEfek = valueEfek;
    }

    public Boolean isSiapPanen() {
        return valueEfek >= valuePanen;
    }

    public abstract void applyEfek(String efek);

    public abstract Product panen() throws Exception;

}
