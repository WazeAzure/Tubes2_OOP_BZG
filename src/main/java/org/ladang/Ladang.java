package org.ladang;
import org.kartu.Kartu;
import org.kartu.harvestable.Harvestable;
import org.kartu.harvestable.tumbuhan.Tumbuhan;
import org.kartu.product.Product;

import java.util.*;

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

    // ke samping ABC ke bawah 1,2,3
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
            String key = chars[curWidth-1] + Integer.toString(i+1);
            key = key.toUpperCase();
            h = kumpulanPetak.get(key.toUpperCase());
            kumpulanPetak.remove(key.toUpperCase());
            if(h != null){
                l.add(h);
            }
        }
        curWidth--;
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
        } else if (card.getKategori().equals("Karnivora") || card.getKategori().equals("Herbivora") || card.getKategori().equals("Omnivora") || card.getKategori().equals("Tumbuhan")){
            return getObject(coor) == null;
        } else {
            return false;
        }
    }

    public void placeCard(Kartu card, String coor) throws Exception{
        if(card.getKategori().equals("Item")){
           if(card.getNama().equals("Destroy")){
               removeObject(coor);
           }else if (card.getNama().equals("Instant Harvest")){
               Harvestable h = getObject(coor);
               h.setValueEfek(h.getValuePanen());
               panen(coor);
           }else if(card.getNama().equals("Accelerate") || card.getNama().equals("Protect") || card.getNama().equals("Trap") ) {
               Harvestable h = getObject(coor);
               h.applyEfek(card.getNama());
           }
        }else if(card.getKategori().equals("Karnivora") || card.getKategori().equals("Herbivora") || card.getKategori().equals("Omnivora") || card.getKategori().equals("Tumbuhan")) {
            kumpulanPetak.put(coor, (Harvestable) card);
        }
        else{
            throw new Exception("Ga bisa kocak");
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
    public void removeObject(String coor){
        kumpulanPetak.put(coor, null);
    }
    public int regionSize(){
        Random random =  new Random();
        int rand = random.nextInt(6);
        while(rand == 0){
            rand = random.nextInt(6);
        }
        return rand;
    }
    public void destroyRegion(){
        Random random =  new Random();
        int size = regionSize();
        List<String> listDestroy = new ArrayList<>();
        List<String> list = new ArrayList<>(kumpulanPetak.keySet());

        int randomInt = random.nextInt(list.size());
        String start = list.get(randomInt);
        StringBuilder sb = new StringBuilder(start);
        int col = sb.charAt(0) - 'A' + 1;
        StringBuilder numeric = new StringBuilder();
        for(int i=1; i<start.length(); i++){
            numeric.append(sb.charAt(i));
        }
        int row = Integer.parseInt(numeric.toString());
        if(size == 1){
            listDestroy.add(Character.toString('A' + col-1)  + row);
        }else if(size == 2){
           while(col + 1 > curWidth){
               randomInt = random.nextInt(list.size());
               start = list.get(randomInt);
               sb = new StringBuilder(start);
               col = sb.charAt(0) - 'A' + 1;
               numeric = new StringBuilder();
               for(int i=1; i<start.length(); i++){
                   numeric.append(sb.charAt(i));
               }
               row = Integer.parseInt(numeric.toString());
           }
           listDestroy.add(Character.toString('A' + col-1)  + row);
           col++;
           listDestroy.add(Character.toString('A' + col-1)  + row);
        }else if(size == 3){
            while(col + 2 > curWidth){
                randomInt = random.nextInt(list.size());
                start = list.get(randomInt);
                sb = new StringBuilder(start);
                col = sb.charAt(0) - 'A' + 1;
                numeric = new StringBuilder();
                for(int i=1; i<start.length(); i++){
                    numeric.append(sb.charAt(i));
                }
                row = Integer.parseInt(numeric.toString());
            }
            listDestroy.add(Character.toString('A' + col-1)  + row);
            col++;
            listDestroy.add(Character.toString('A' + col-1)  + row);
            col++;
            listDestroy.add(Character.toString('A' + col-1)  + row);
        }else if(size == 4){
            while(col + 1 > curWidth || row + 1 > curHeight){
                randomInt = random.nextInt(list.size());
                start = list.get(randomInt);
                sb = new StringBuilder(start);
                col = sb.charAt(0) - 'A' + 1;
                numeric = new StringBuilder();
                for(int i=1; i<start.length(); i++){
                    numeric.append(sb.charAt(i));
                }
                row = Integer.parseInt(numeric.toString());
            }
            listDestroy.add(Character.toString('A' + col-1)  + row);
            col++;
            listDestroy.add(Character.toString('A' + col-1)  + row);
            row++;
            listDestroy.add(Character.toString('A' + col-1)  + row);
            col--;
            listDestroy.add(Character.toString('A' + col-1)  + row);
        }else if (size == 5){
            while(col + 4 > curHeight){
                randomInt = random.nextInt(list.size());
                start = list.get(randomInt);
                sb = new StringBuilder(start);
                col = sb.charAt(0) - 'A' + 1;
                numeric = new StringBuilder();
                for(int i=1; i<start.length(); i++){
                    numeric.append(sb.charAt(i));
                }
                row = Integer.parseInt(numeric.toString());
            }
            listDestroy.add(Character.toString('A' + col-1)  + row);
            row++;
            listDestroy.add(Character.toString('A' + col-1)  + row);
            row++;
            listDestroy.add(Character.toString('A' + col-1)  + row);
            row++;
            listDestroy.add(Character.toString('A' + col-1)  + row);
            row++;
            listDestroy.add(Character.toString('A' + col-1)  + row);
        }else if(size == 6){
            while(col + 2 > curWidth || row + 1 > curHeight) {
                randomInt = random.nextInt(list.size());
                start = list.get(randomInt);
                sb = new StringBuilder(start);
                col = sb.charAt(0) - 'A' + 1;
                numeric = new StringBuilder();
                for (int i = 1; i < start.length(); i++) {
                    numeric.append(sb.charAt(i));
                }
                row = Integer.parseInt(numeric.toString());
            }
            listDestroy.add(Character.toString('A' + col-1)  + row);
            col++;
            listDestroy.add(Character.toString('A' + col-1)  + row);
            col++;
            listDestroy.add(Character.toString('A' + col-1)  + row);
            row++;
            listDestroy.add(Character.toString('A' + col-1)  + row);
            col--;
            listDestroy.add(Character.toString('A' + col-1)  + row);
            col--;
            listDestroy.add(Character.toString('A' + col-1)  + row);

        }
        for(String s: listDestroy){
            System.out.println(size);
            System.out.println(s);
            removeObject(s);
        }
//        int possibility = random.nextInt(4);
//        switch (possibility) {
//            case 0: // 1 x 1
//                removeObject(start);
//                break;
//            case 1: // 1 x 2
//                if (row == curHeight) {
//                    row -= 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                } else if (col == curWidth) {
//                    col -= 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                } else if (row == 1) {
//                    row += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                } else if (col == 1) {
//                    col += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                } else {
//                    col += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                }
//                break;
//            case 2: // 2x2
//                if (col == curWidth && row == curHeight) {
//                    row -= 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    col -= 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    row += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                }else if (row == curHeight) {
//                    row -= 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    col += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    row += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                } else if (col == curWidth) {
//                    row += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    col -= 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    row -= 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                } else if (row == 1 && col == 1) {
//                    row += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    col += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    row -= 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                } else if (row == 1) {
//                    row += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    col -= 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    row -= 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                } else if (col == 1){
//                    row += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    col += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    row -= 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                }else{
//                    row += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    col += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    row -= 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                }
//                break;
//            case 3: // 2 x 3
//                if (col == curWidth && row == curHeight) {
//                    row -= 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    col -= 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    col -= 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    row += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    row += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//
//                }else if (row == curHeight) {
//                    row -= 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    col += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    row += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                } else if (col == curWidth) {
//                    row += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    col -= 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    row -= 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                } else if (row == 1 && col == 1) {
//                    row += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    col += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    row -= 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                } else if (row == 1) {
//                    row += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    col -= 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    row -= 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                } else if (col == 1){
//                    row += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    col += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    row -= 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                }else{
//                    row += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    col += 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                    row -= 1;
//                    listDestroy.add(Integer.toString(col) + row);
//                }
//                break;
//        }






    }
}
