package org.kartu.harvestable.hewan;

import org.kartu.Kartu;
import org.kartu.harvestable.Harvestable;
import org.kartu.item.Item;
import org.kartu.product.Product;

abstract public class Hewan extends Harvestable {
    public Hewan(String nama, String kategori, String imageURL, Integer valuePanen) {
        super(nama, kategori, imageURL, valuePanen);
    }

    public void applyEfek(String efek) {
        addItemAktif(efek);
        if (efek.equals("ACCELERATE")) {
            valueEfek += 8;
        } else if (efek.equals("DELAY")) {
            valueEfek -= 5;
            if (valueEfek < 0) {
                valueEfek = 0;
            }
        }
    }

    public Product panen() throws Exception {
        if (getValueEfek() < getValuePanen()) {
            throw new Exception("Hewan belum siap panen!");
        } else {
            return product;
        }
    }

    public abstract void makan(Kartu kartu) throws Exception;
}
