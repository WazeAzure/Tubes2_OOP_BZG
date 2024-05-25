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
        if (nama.equals("HIU_DARAT")) {
            this.product = Config.buildProduct("SIRIP_HIU");
        } else if (nama.equals("SAPI")) {
            this.product = Config.buildProduct("SUSU");
        } else if (nama.equals("DOMBA")) {
            this.product = Config.buildProduct("DAGING_DOMBA");
        } else if (nama.equals("KUDA")) {
            this.product = Config.buildProduct("DAGING_KUDA");
        } else if (nama.equals("AYAM")) {
            this.product = Config.buildProduct("TELUR");
        } else if (nama.equals("BERUANG")) {
            this.product = Config.buildProduct("DAGING_BERUANG");
        } else if (nama.equals("BIJI_JAGUNG")) {
            this.product = Config.buildProduct("JAGUNG");
        } else if (nama.equals("BIJI_LABU")) {
            this.product = Config.buildProduct("LABU");
        } else if (nama.equals("BIJI_STROBERI")) {
            this.product = Config.buildProduct("STROBERI");
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
