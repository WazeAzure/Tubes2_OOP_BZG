package org.kartu.harvestable.hewan;

public class Omnivora extends Hewan {
    public Omnivora(String nama, String kategori, String imageURL, Integer berat, Integer beratPanen) {
        super(nama, kategori, imageURL, beratPanen);
    }

    @Override
    public void panen() {

    }

    @Override
    public void tumbuh() {

    }
}