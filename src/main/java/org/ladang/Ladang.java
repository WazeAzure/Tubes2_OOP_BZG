package org.ladang;
import org.kartu.Kartu;
import org.kartu.harvestable.Harvestable;
import org.kartu.harvestable.tumbuhan.Tumbuhan;
import org.kartu.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ladang {
    public static final int Width_Default = 5;
    public static final int Height_Default = 4;
    public static final char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private int curWidth;
    private int curHeight;
    private Map<String, Harvestable> kumpulanPetak = new HashMap<>();

    public Ladang(int Width, int Height){
        curWidth = 1;
        curHeight = 1;
        kumpulanPetak.put("A1", null);
        for(int i = 0; i < Width-1; i++){
            addCol();
        }

        for(int j = 0; j < Height-1; j++){
            addRow();
        }
    }
    public Map<String, Harvestable> getLadang(){
        return kumpulanPetak;
    }
    public void addRow(){
        for(int i=0; i<this.curWidth; i++){
            String key = Character.toString(chars[i]).toUpperCase()+ (curHeight+1);
            kumpulanPetak.put(key, null);
        }
        curHeight++;
    }

    public void addCol(){
        for(int i=0; i<this.curHeight; i++) {
            String key = Character.toString(chars[curWidth]).toUpperCase() + (i+1);
            kumpulanPetak.put(key, null);
        }
        curWidth++;
    }
    public List<Harvestable> removeRow(){
        List<Harvestable> l = new ArrayList<>();
        Harvestable h;
        for(int i=0; i<this.curWidth; i++){
            String key = Character.toString(chars[i]).toUpperCase() + curHeight;
            h = kumpulanPetak.get(key);
            kumpulanPetak.remove(key);
            if(h != null){
                l.add(h);
            }
        }
        curHeight--;
        return l;
    }

    public List<Harvestable> removeCol(){
        List<Harvestable> l = new ArrayList<>();
        Harvestable h;
        for(int i=0; i<this.curHeight; i++) {
            curWidth--;
            h = kumpulanPetak.get(chars[curWidth] + Integer.toString(i));
            if(h != null){
                l.add(h);
            }
        }
        return l;
    }

    public void makeBigger(){
        addRow();
        addCol();
    }
    public List<Harvestable> makeSmaller(){
        List<Harvestable> l = new ArrayList<>();
        l.addAll(removeRow());
        l.addAll(removeCol());
        return l;
    }
//    public Boolean isCoordinateValid(String coor){
//        return kumpulanPetak.containsKey(coor);
//    }
    public Harvestable getObject(String coor){
        return kumpulanPetak.getOrDefault(coor, null);
    }


    // ngecek apakah placement kartu valid
    public Boolean validateItemCardPlacement(Kartu card, String coor){
        if(card.getKategori().equals("Item")){
            return getObject(coor) != null;
        }else{
            return getObject(coor) == null;
        }
    }

    public void placeCard(Kartu h, String coor){
        if(validateItemCardPlacement(h,coor)){
            kumpulanPetak.put(coor,(Harvestable) h);
        }
    }


    // panen digunakan untuk memanggil method panen pada Harvestable
    public Product panen(String coor) throws Exception{
        Product product = getObject(coor).panen();
        kumpulanPetak.put(coor, null);
        return product;
    }

    // growAllPlant untuk menambah umur dari semua tanaman pada ladang
    public void growAllPlant(){
        for(var entry: kumpulanPetak.entrySet()){
            if(entry.getValue().getKategori().equals("Tumbuhan")){
                ((Tumbuhan)entry.getValue()).tumbuh();
            }
        }
    }

    // getInfo untuk mendapatkan semua info dari sebuah petak yang di klik
    public List<String> getInfo(String coor){
        List<String> message = new ArrayList<>();
        Harvestable obj = getObject(coor);
        message.add(obj.getNama());
        message.add(Integer.toString(obj.getValue()));
        message.add(Integer.toString(obj.getValueEfek()));
        for(var item : obj.getItemAktif().entrySet()) {
            message.add(item.getKey() + ": " + item.getValue());
        }
        return message;
    }
    public void moveObject(String source, String dest) throws Exception{
        Harvestable obj = getObject(source);
        Harvestable obj2 = getObject(dest);
        if(obj == null || obj2 != null){
            throw new Exception("Ga bisa kocak");
        }else{
            kumpulanPetak.put(source, null);
            kumpulanPetak.put(dest, obj);
        }
    }


}
