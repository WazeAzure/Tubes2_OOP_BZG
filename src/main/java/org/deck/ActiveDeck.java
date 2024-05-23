package org.deck;

import org.kartu.Kartu;

import java.util.ArrayList;
import java.util.List;

public class ActiveDeck{
    private Kartu[] listKartu = new Kartu[6];
    public ActiveDeck(){

    }
    public void addCard(Kartu card, int index){
        listKartu[index] = card;
    }
    public void removeCard(Kartu card, int index){
        listKartu[index] = null;
    }
}
