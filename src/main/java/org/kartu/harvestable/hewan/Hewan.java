package org.kartu.harvestable.hewan;

import org.kartu.harvestable.Harvestable;

abstract public class Hewan extends Harvestable {
    public Hewan(String nama, String kategori, String imageURL, Integer beratPanen) {
        super(nama, kategori, imageURL, beratPanen);
    }
}
