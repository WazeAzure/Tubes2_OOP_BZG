package org.grid;

import org.kartu.harvestable.Harvestable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid<T> {
    private int curWidth;
    private int curHeight;
    public static final char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private  Map<String, T> kumpulanPetak = new HashMap<String,T>();
    public Grid(int Width, int Height){
        curWidth = 1;
        curHeight = 1;
        kumpulanPetak.put("A01", null);
        for(int i = 0; i < Width-1; i++){
            addCol();
        }

        for(int j = 0; j < Height-1; j++){
            addRow();
        }


    }
    public void addRow(){
        for(int i=0; i<this.curWidth; i++){
            String key = "";
            if(curHeight < 9){
                key = Character.toString(chars[i]).toUpperCase() + "0" + (curHeight+1);
            }else{
                key = Character.toString(chars[i]).toUpperCase() + (curHeight+1);
            }
            kumpulanPetak.put(key, null);
        }
        curHeight++;
    }

    public void addCol(){
        for(int i=0; i<this.curHeight; i++) {
            String key = "";
            if(i < 9){
                key = Character.toString(chars[curWidth]).toUpperCase() + "0" + (i+1);
            }else{
                key = Character.toString(chars[curWidth]).toUpperCase() + (i+1);
            }
            kumpulanPetak.put(key, null);
        }
        curWidth++;
    }
    public List<String> removeRow(){
        List<String> l = new ArrayList<>();
        for(int i=0; i<this.curWidth; i++){
            String key = "";
            if(curHeight < 10){
                key = Character.toString(chars[i]).toUpperCase() + "0" + curHeight;
            }else{
                key = Character.toString(chars[i]).toUpperCase() + curHeight;
            }
            l.add(key);
        }
        curHeight--;
        return l;
    }

    public List<String> removeCol(){
        List<String> l = new ArrayList<>();
        for(int i=0; i<this.curHeight; i++) {
            String key = "";
            if(i < 9){
                key = chars[curWidth-1] + "0" + Integer.toString(i+1);
            }else{
                key = chars[curWidth-1] + Integer.toString(i+1);
            }
            key = key.toUpperCase();
            l.add(key);
        }
        curWidth--;
        return l;
    }
    public Map<String, T> getGrid(){
        return kumpulanPetak;
    }
    public  Integer getWidth(){
        return curWidth;
    }
    public Integer getHeight(){
        return curHeight;
    }
    public void IncWidth(){
        curWidth++;
    }
    public void IncHeigth(){
        curHeight++;
    }
    public void DecWidth(){
        curWidth--;
    }
    public void DecHeigth(){
        curHeight--;
    }
    public void put2(String key, T value){
        kumpulanPetak.put(key, value);
    }
    public void remove(String key){
        kumpulanPetak.remove(key);
    }
    public T getObj(String key){
        return kumpulanPetak.getOrDefault(key, null);
    }
    // mulai dari 0
    public static String parseToKey(int col, int row){
        StringBuilder sb = new StringBuilder();
        sb.append(chars[col]);
        if(row < 9){
            sb.append("0");
        }
        sb.append(row+1);
        return sb.toString().toUpperCase();
    }

    // mulai dari 1 bukan 0
    public static List<Integer> parseFromKey(String key){
        List<Integer> l = new ArrayList<>();
        StringBuilder sb = new StringBuilder(key);
        int col = sb.charAt(0) - 'A' + 1;
        StringBuilder numeric = new StringBuilder();
        for(int i=1; i<key.length(); i++){
            numeric.append(sb.charAt(i));
        }
        int row = Integer.parseInt(numeric.toString());
        l.add(col);
        l.add(row);

        return l;
    }
}
