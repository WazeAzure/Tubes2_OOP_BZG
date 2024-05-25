package org.ladang;
import org.grid.Grid;
import org.kartu.Kartu;
import org.kartu.harvestable.Harvestable;
import org.kartu.harvestable.hewan.Hewan;
import org.kartu.harvestable.tumbuhan.Tumbuhan;
import org.kartu.product.Product;

import java.util.*;

public class Ladang {
    public static final int Width_Default = 5;
    public static final int Height_Default = 4;
    public static final char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private Grid<Harvestable> kumpulanPetak;
    private int layoutTurn; // -1 berati lagi default
    private int layoutChange; // -1 lagi mengecil +1 lagi membesar

    public Ladang(int Width, int Height){
        kumpulanPetak = new Grid<>(Width, Height);
        layoutTurn = -1;
        layoutChange = 0;
    }
    public Map<String, Harvestable> getLadang(){
        return kumpulanPetak.getGrid();
    }

    // ke samping ABC ke bawah 1,2,3
    public void addRow(){
        kumpulanPetak.addRow();
    }

    public void addCol(){
        kumpulanPetak.addCol();
    }
    public List<Harvestable> removeRow(){
        List<Harvestable> l = new ArrayList<>();
        List<String> ls = kumpulanPetak.removeRow();
        for(String s: ls){
            if(kumpulanPetak.getObj(s) != null){
                l.add(kumpulanPetak.getObj(s));
            }
            kumpulanPetak.remove(s);
        }
        return l;
    }

    public List<Harvestable> removeCol(){
        List<Harvestable> l = new ArrayList<>();
        List<String> ls = kumpulanPetak.removeCol();
        for(String s: ls){
            if(kumpulanPetak.getObj(s) != null){
                l.add(kumpulanPetak.getObj(s));
            }
            kumpulanPetak.remove(s);
        }
        return l;

    }
    public static String parseToKey(int col, int row){
        return Grid.parseToKey(col,row);
    }

    // mulai dari 1 bukan 0
    public static List<Integer> parseFromKey(String key){
        return Grid.parseFromKey(key);
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
        return kumpulanPetak.getObj(coor);
    }

    // ngecek apakah placement kartu valid
    public Boolean validateItemCardPlacement(Kartu card, String coor){
        if(card.getKategori().equals("Item")){
            return getObject(coor) != null;
        } else if (card.getKategori().equals("Karnivora") || card.getKategori().equals("Herbivora") || card.getKategori().equals("Omnivora") || card.getKategori().equals("Tumbuhan")){
            return getObject(coor) == null;
        } else {
            return false;
        }
    }

    public Kartu placeCard(Kartu card, String coor) throws Exception{
        if(card.getKategori().equals("Item")){
           if (getObject(coor) == null){
               throw new Exception("Kosong");
           }else{
               if(card.getNama().equals("DESTROY")){
                   removeObject(coor);
               }else if (card.getNama().equals("INSTANT_HARVEST")){
                   Harvestable h = getObject(coor);
                   h.setValueEfek(h.getValuePanen());
                   return panen(coor);
               }else if(card.getNama().equals("ACCELERATE") || card.getNama().equals("PROTECT") || card.getNama().equals("TRAP") || card.getNama().equals("DELAY") ) {
                   Harvestable h = getObject(coor);
                   h.applyEfek(card.getNama());
               }
           }
        }else if(card.getKategori().equals("Karnivora") || card.getKategori().equals("Herbivora") || card.getKategori().equals("Omnivora") || card.getKategori().equals("Tumbuhan")) {
            if (getObject(coor) != null){
                throw new Exception("Sudah ditempati!");
            }else{
                kumpulanPetak.put2(coor, (Harvestable) card);
            }
        }else if (card.getKategori().equals("Produk Hewan") || card.getKategori().equals("Produk Tanaman")){
            if (getObject(coor) == null){
                throw new Exception("Tidak ada isinya");
            }else{
                if(getObject(coor).getKategori().equals("Karnivora") || getObject(coor).getKategori().equals("Herbivora") || getObject(coor).getKategori().equals("Omnivora")){
                    ((Hewan)getObject(coor)).makan(card);
                } else {
                    throw new Exception("Tumbuhan tidak bisa makan!");
                }
            }
        }
        else{
            throw new Exception("Error: invalid placing");
        }
        return null;
    }
    public List<Harvestable> placeCard(Kartu card, Boolean self) throws Exception{
        List<Harvestable> l = new ArrayList<>();
        if(card.getKategori().equals("Item")){
            if(card.getNama().equals("LAYOUT") && self){
                makeBigger();
                layoutChange = 1;
                layoutTurn = 6;
            } else if (card.getNama().equals("LAYOUT") && !self){} {
                l = makeSmaller();
                layoutChange = -1;
                layoutTurn = 6;
            }
            return l;
        }else{
            throw new Exception("Salah method");
        }
    }

    // panen digunakan untuk memanggil method panen pada Harvestable
    public Product panen(String coor) throws Exception{
        Product product = getObject(coor).panen();
        kumpulanPetak.put2(coor, null);
        return product;
    }

    // growAllPlant untuk menambah umur dari semua tanaman pada ladang
    public void growAllPlant(){
        for(var entry: kumpulanPetak.getGrid().entrySet()){
            if(entry.getValue() != null){
                if(entry.getValue().getKategori().equals("Tumbuhan")){
                    ((Tumbuhan)entry.getValue()).tumbuh();
                }
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
            kumpulanPetak.put2(source, null);
            kumpulanPetak.put2(dest, obj);
        }
    }
    public void removeObject(String coor){
        kumpulanPetak.put2(coor, null);
    }
    public int regionSize(){
        Random random =  new Random();
        int rand;
        rand = random.nextInt(7);
        while(rand == 0 || (rand == 5 && layoutChange == -1)){
            rand = random.nextInt(7);
        }
        return rand;
    }

    public List<String> destroyRegion(){
        Random random =  new Random();
        int size = regionSize();
        List<String> listDestroy = new ArrayList<>();
        List<String> list = new ArrayList<>(kumpulanPetak.getGrid().keySet());
        List<Integer> colrow = new ArrayList<>();
        int randomInt = random.nextInt(list.size());
        String start = list.get(randomInt);
        colrow = parseFromKey(start);
        int col = colrow.get(0);
        int row = colrow.get(1);
//        if(size == 6){
//            System.out.println("Ukuran 6");
//        }
        if(size == 1){
            listDestroy.add(parseToKey(col-1,row-1));
        }else if(size == 2){
           while(col + 1 > kumpulanPetak.getWidth()){
               randomInt = random.nextInt(list.size());
               start = list.get(randomInt);
               colrow = parseFromKey(start);
               col = colrow.get(0);
               row = colrow.get(1);
           }
           listDestroy.add(parseToKey(col-1,row-1));
           col++;
           listDestroy.add(parseToKey(col-1,row-1));
        }else if(size == 3){
            while(col + 2 > kumpulanPetak.getWidth()){
                randomInt = random.nextInt(list.size());
                start = list.get(randomInt);
                colrow = parseFromKey(start);
                col = colrow.get(0);
                row = colrow.get(1);
            }
            listDestroy.add(parseToKey(col-1,row-1));
            col++;
            listDestroy.add(parseToKey(col-1,row-1));
            col++;
            listDestroy.add(parseToKey(col-1,row-1));
        }else if(size == 4){
            while(col + 1 > kumpulanPetak.getWidth() || row + 1 > kumpulanPetak.getHeight()){
                randomInt = random.nextInt(list.size());
                start = list.get(randomInt);
                colrow = parseFromKey(start);
                col = colrow.get(0);
                row = colrow.get(1);
            }
            listDestroy.add(parseToKey(col-1,row-1));
            col++;
            listDestroy.add(parseToKey(col-1,row-1));
            row++;
            listDestroy.add(parseToKey(col-1,row-1));
            col--;
            listDestroy.add(parseToKey(col-1,row-1));
        }else if (size == 5){
            while(col + 4 > kumpulanPetak.getWidth()){
                randomInt = random.nextInt(list.size());
                start = list.get(randomInt);
                colrow = parseFromKey(start);
                col = colrow.get(0);
                row = colrow.get(1);
            }
            listDestroy.add(parseToKey(col-1,row-1));
            col++;
            listDestroy.add(parseToKey(col-1,row-1));
            col++;
            listDestroy.add(parseToKey(col-1,row-1));
            col++;
            listDestroy.add(parseToKey(col-1,row-1));
            col++;
            listDestroy.add(parseToKey(col-1,row-1));
        }else if(size == 6){
            while(col + 2 > kumpulanPetak.getWidth() || row + 1 > kumpulanPetak.getHeight()) {
                randomInt = random.nextInt(list.size());
                start = list.get(randomInt);
                colrow = parseFromKey(start);
                col = colrow.get(0);
                row = colrow.get(1);
            }
            listDestroy.add(parseToKey(col-1,row-1));
            col++;
            listDestroy.add(parseToKey(col-1,row-1));
            col++;
            listDestroy.add(parseToKey(col-1,row-1));
            row++;
            listDestroy.add(parseToKey(col-1,row-1));
            col--;
            listDestroy.add(parseToKey(col-1,row-1));
            col--;
            listDestroy.add(parseToKey(col-1,row-1));

        }
        return listDestroy;
    }
}
