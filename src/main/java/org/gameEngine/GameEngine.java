package org.gameEngine;

import org.config.Config;
import org.kartu.Kartu;
import org.ladang.Ladang;
import org.pemain.Pemain;
import org.toko.Toko;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private int turn;
    private List<Pemain> pemain;
    private Config config;
    private Toko toko;
    private int gameState;

    public GameEngine() {
        // Inisialisasi turn;
        turn = 1;

        // Inisialisasi Pemain
        pemain = new ArrayList<Pemain>();
        pemain.add(new Pemain(1, 0, new Ladang(4, 5)));
        pemain.add(new Pemain(2, 0, new Ladang(4, 5)));

        // Inisialisasi Config
        config = new Config();
        config.loadConfig();

        // Inisialisasi Toko
        toko = new Toko(config);

        // Inisialisasi gameState
        // 0 = Shuffle Kartu, 1 = Serangan Beruang, 2 = Aksi Bebas
        gameState = 0;
    }

    public void dndLadangLadang(){

    }

    public void dndDeckDeck() {

    }

    public void dndDeckLadang() {

    }

    public void dndDeckLadangMusuh() {

    }

    public void panen(){

    }

    public void nextTurn() {
        if (turn != 21) {

        } else {

        }
    }

    public void beli() {

    }

    public void jual() {

    }

    public void shuffleDeck() {

    }

    public void addKartuToDeck(Kartu kartu) {

    }
}
