package org.kartu.product;

import org.kartu.Kartu;

public class Product extends Kartu {
    private Integer harga;
    private Integer berat;

    public Product (String nama, String kategori, String imageURL, Integer harga, Integer berat) {
        super(nama, kategori, imageURL);
        this.harga = harga;
        this.berat = berat;
    }

    public Integer getHarga() {
        return harga;
    }

    public Integer getBerat() {
        return berat;
    }
}
