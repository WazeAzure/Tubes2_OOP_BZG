package org.kartu;

import java.io.Serializable;

public class Kartu implements Serializable {
    protected String nama;
    protected String kategori;
    protected String imageURL;


    public Kartu(String nama, String kategori, String imageURL) {
        this.nama = nama;
        this.kategori = kategori;
        this.imageURL = imageURL;
    }
    /**
     * Class to get val
     */
    // Methods

    public String getNama() {
        return nama;
    }

    public String getKategori() {
        return kategori;
    }

    public String getImageURL() {
        return imageURL;
    }
}
