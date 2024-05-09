package org.kartu.harvestable.hewan;

public class Herbivora extends Hewan {
    public Herbivora(String nama, String kategori, String imageURL, Integer valuePanen, String product) {
        super(nama, kategori, imageURL, valuePanen, product);
    }

    @Override
    public void panen() {

    }
}
