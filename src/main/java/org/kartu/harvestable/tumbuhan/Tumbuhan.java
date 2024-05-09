package org.kartu.harvestable.tumbuhan;

import org.kartu.Kartu;
import org.kartu.harvestable.Harvestable;

public class Tumbuhan extends Harvestable {
    public Tumbuhan(String nama, String kategori, String imageURL, Integer value, Integer valuePanen, String product) {
        super(nama, kategori, imageURL, valuePanen, product);
    }

    public void panen() {

    }

    public void applyEfek(String efek) {
        addItemAktif(efek);
        if (efek.equals("Accelerate")) {
            valueEfek += 2;
        } else if (efek.equals("Delay")) {
            valueEfek -= 2;
        }
    }

    public void tumbuh() {

    }
}
