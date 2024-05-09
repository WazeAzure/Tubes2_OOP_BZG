package org.kartu.product;

import org.kartu.Kartu;

public class Product extends Kartu {
    private Integer harga;

    public Product (String nama, String kategori, String imageURL, Integer harga) {
        super(nama, kategori, imageURL);
        this.harga = harga;
    }
}
