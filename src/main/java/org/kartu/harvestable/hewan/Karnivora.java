package org.kartu.harvestable.hewan;

import org.kartu.Kartu;
import org.kartu.product.Product;

public class Karnivora extends Hewan {
    public Karnivora(String nama, String kategori, String imageURL, Integer valuePanen) {
        super(nama, kategori, imageURL, valuePanen);
    }

    public void makan(Kartu kartu) throws Exception {
        if (kartu.getKategori().equals("Produk Hewan") || kartu.getKategori().equals("Produk Tumbuhan")) {
            Product p = (Product) kartu;
            value += p.getBerat();
            valueEfek += p.getBerat();
        } else {
            throw new Exception("Kartu itu tidak bisa dimaakn!");
        }
    }
}