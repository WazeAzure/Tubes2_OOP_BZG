package org.deck;

import org.kartu.Kartu;

public class ActiveDeck extends Deck{
    public ActiveDeck(){}
    public void addCard(Kartu card){
        listKartu.add(card);
    }
    public void removeCard(Kartu card){
        listKartu.remove(card);
    }
}
