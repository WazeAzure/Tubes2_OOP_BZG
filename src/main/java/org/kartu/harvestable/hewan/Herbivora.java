package org.kartu.harvestable.hewan;

public class Herbivora extends Hewan {
    public Herbivora(String nama, String kategori, String imageURL, Integer berat, Integer beratEffect) {
        super(nama, kategori, imageURL, berat, beratEffect);
    }

    @Override
    public void panen() {

    }

    @Override
    public void tumbuh() {

    }
}
