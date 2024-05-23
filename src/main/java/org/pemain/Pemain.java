package org.pemain;

import org.deck.ActiveDeck;
import org.deck.ShuffleDeck;
import org.ladang.Ladang;

public class Pemain {
    private Integer playerNumber;
    Integer uang;
    private Ladang ladang;
    private ShuffleDeck deck;
    private ActiveDeck activeDeck;

    public Pemain(Integer playerNumber) {
        ladang = new Ladang(5, 4);
        uang = 0;
        this.playerNumber = playerNumber;
    }

    public Pemain(Integer playerNumber, Integer uang, Ladang ladang) {
        this.playerNumber = playerNumber;
        this.uang = uang;
        this.ladang = ladang;
    }

    public Integer getPlayerNumber() {
        return playerNumber;
    }

    public Integer getUang() {
        return uang;
    }

    public Ladang getLadang() {
        return ladang;
    }

    public ShuffleDeck getShuffleDeck() {
        return deck;
    }

    public ActiveDeck getActiveDeck() {
        return activeDeck;
    }

    public void setUang(Integer uang) {
        this.uang = uang;
    }
}
