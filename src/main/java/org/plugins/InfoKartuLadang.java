package org.plugins;

import java.util.ArrayList;
import java.util.List;

public class InfoKartuLadang {
    String lokasi;
    String nama;
    int umurBerat;
    int jumlahItemAktif;
    List<String> itemAktif;

    public InfoKartuLadang(String lokasi, String nama, int umurBerat, int jumlahItemAktif, String[] itemAktif){
        this.lokasi = lokasi;
        this.nama = nama;
        this.umurBerat = umurBerat;
        this.jumlahItemAktif = jumlahItemAktif;
        this.itemAktif = new ArrayList<>();

        for(int i=0; i<jumlahItemAktif; i++){
            this.itemAktif.add(itemAktif[i]);
        }
    }
}
