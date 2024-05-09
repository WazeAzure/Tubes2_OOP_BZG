package org.kartu.harvestable.hewan;

import org.kartu.harvestable.Harvestable;

abstract public class Hewan extends Harvestable {
    public Hewan(String nama, String kategori, String imageURL, Integer berat, Integer beratEffect) {
        super(nama, kategori, imageURL, berat, beratEffect);
    }
}
