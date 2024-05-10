package org.toko;
import org.config.Config;
import org.kartu.Kartu;
import org.kartu.product.Product;

import java.util.*;

public class Toko {
    private Map<String,Kartu> daftar;
    private Map<String, Integer> stok;
    private Config config;
    public Toko(Config config) {
        daftar = new HashMap<>();
        this.config = config;
        for(var p: Config.b){
            daftar.put(p.getKey(), p.getValue());
            stok.put(p.getKey(), 0);
        }
        // isi dari config

    }
    public List<String> getInfo(int i){
        return new ArrayList<>();
    }
    public List<Kartu> getListCard(){
        return new ArrayList<>(daftar.values());
    }
    public int totalHarga(String produk, int jumlah){
        return ((Product)daftar.get(produk)).getHarga() * jumlah;
    }
    public Kartu buy(String produk, int jumlah, int uang) throws Exception{
        if(uang >= totalHarga(produk, jumlah)){
            return new config.buildProduct(produk);
        }
        throw new Exception("Duit lu gak cukup");
    }
    public int sell(String produk){
        stok.put(produk, stok.get(produk)+1);
        return (Product)daftar.get(produk).getHarga();
    }
}
