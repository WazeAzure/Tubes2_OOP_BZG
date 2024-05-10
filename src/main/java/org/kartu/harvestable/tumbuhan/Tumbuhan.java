package org.kartu.harvestable.tumbuhan;

import org.config.Config;
import org.kartu.Kartu;
import org.kartu.harvestable.Harvestable;
import org.kartu.product.Product;

public class Tumbuhan extends Harvestable {
    String realNama;
    String realImageURL;

    public Tumbuhan(String nama, String kategori, String imageURL, Integer valuePanen) {
        super(nama, kategori, imageURL, valuePanen);
        this.realNama = nama;
        this.realImageURL = imageURL;
    }

    public Product panen() throws Exception {
        if (getValueEfek() < getValuePanen()) {
            throw new Exception("Tumbuhan belum siap panen!");
        } else {
            return product;
        }
    }

    public void applyEfek(String efek) {
        addItemAktif(efek);
        if (efek.equals("Accelerate")) {
            valueEfek += 2;
            if (valueEfek >= getValuePanen()) {
                 nama = product.getNama();
                 imageURL = product.getImageURL();
            }
        } else if (efek.equals("Delay")) {
            valueEfek -= 2;
            if (valueEfek < 0) {
                valueEfek = 0;
            }
            if (valueEfek < getValuePanen()) {
                nama = realNama;
                imageURL = realImageURL;
            }
        }
    }

    public void tumbuh() {
        value++;
        valueEfek++;
        if (valueEfek >= getValuePanen()) {
            nama = product.getNama();
            imageURL = product.getImageURL();
        }
    }
}
