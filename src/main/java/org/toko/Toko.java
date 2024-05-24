package org.toko;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.config.Config;
import org.kartu.Kartu;
import org.kartu.product.Product;

public class Toko {
    private Map<String,Kartu> daftar;
    private Map<String, Integer> stok;
    public Toko() {
        daftar = new HashMap<>();
        stok = new HashMap<>();
        for(var p: Config.getListProductAnimal().entrySet()){
            daftar.put(p.getKey(), p.getValue());
            stok.put(p.getKey(), 0);
        }
        for(var p: Config.getListProductPlant().entrySet()){
            daftar.put(p.getKey(), p.getValue());
            stok.put(p.getKey(), 0);
        }
        // isi dari config

    }
//    public List<String> getInfo(int i){
//        return new ArrayList<>();
//    }
    public List<Kartu> getListCard(){
        return new ArrayList<>(daftar.values());
    }
    public Integer getStok(String kartu){
        return stok.get(kartu);
    }
    public int totalHarga(String produk, int jumlah){
        return ((Product)daftar.get(produk)).getHarga() * jumlah;
    }
    public List<Kartu> buy(String produk, int jumlah, int uang) throws Exception{
        List<Kartu> kartu = new ArrayList<>();
        if(uang >= totalHarga(produk, jumlah)){
           for(int i = 0; i < jumlah; i++){
               kartu.add(Config.buildProduct(produk));
           }
           stok.put(produk, stok.get(produk) - jumlah);
           return kartu;
        }
        throw new Exception("Duit lu gak cukup");
    }
    public int sell(String produk){
        stok.put(produk, stok.get(produk)+1);
        return ((Product)daftar.get(produk)).getHarga();
    }
}
