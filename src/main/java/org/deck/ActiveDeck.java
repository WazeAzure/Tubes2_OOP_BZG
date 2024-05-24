package org.deck;

import org.grid.Grid;
import org.kartu.Kartu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ActiveDeck{
    private Grid<Kartu> listKartu;
    public ActiveDeck(){
        listKartu = new Grid<>(6,1);
    }
    public void addCard(List<Kartu> card){
        int i=0;
        List<String> l = new ArrayList<>(listKartu.getGrid().keySet());
        for (Kartu kartu : card) {
            for (String s : l) {
                if (listKartu.getObj(s) == null) {
                    listKartu.put2(s, kartu);
                    break;
                }

            }
        }
    }
    public void removeCard(String key){
        listKartu.remove(key);
    }
    public int remainingSlot(){
        int count = 0;
        for(var entry: listKartu.getGrid().entrySet()){
            if(entry.getValue() == null){
                count++;
            }
        }
        return count;
    }

    public Map<String, Kartu> getListKartu() {
        return listKartu.getGrid();
    }
}
