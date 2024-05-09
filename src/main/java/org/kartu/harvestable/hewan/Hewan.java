package org.kartu.harvestable.hewan;

import org.kartu.harvestable.Harvestable;
import org.kartu.item.Item;

abstract public class Hewan extends Harvestable {
    public Hewan(String nama, String kategori, String imageURL, Integer valuePanen, String product) {
        super(nama, kategori, imageURL, valuePanen, product);
    }

    public void applyEfek(String efek) {
        addItemAktif(efek);
        if (efek.equals("Accelerate")) {
            valueEfek += 2;
        } else if (efek.equals("Delay")) {
            valueEfek -= 2;
        }
    }

    public void tumbuh(Item i) {

    }
}
