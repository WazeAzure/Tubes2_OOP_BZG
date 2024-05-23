package org.gameEngine;

import org.config.Config;
import org.kartu.Kartu;
import org.ladang.Ladang;
import org.pemain.Pemain;
import org.toko.Toko;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private Integer turn;
    private List<Pemain> pemain;
    private Config config;
    private Toko toko;
    private Integer gameState;

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
        toko = new Toko();

        // Inisialisasi gameState
        // 0 = Shuffle Kartu, 1 = Serangan Beruang, 2 = Aksi Bebas
        gameState = 0;
    }

    Pemain getCurrentPemain() {
        return pemain.get((turn-1) % 2);
    }

    List<Pemain> getListPemain() {
        return pemain;
    }

    Integer getTurn() {
        return turn;
    }

    Integer getGameState() {
        return gameState;
    }

    void setGameState(Integer gameState) {
        this.gameState = gameState;
    }

    Toko getToko() {
        return toko;
    }

    Config getConfig() {
        return config;
    }

    public void dndLadangLadang(){

    }

    public void dndDeckDeck() {

    }

    public void dndDeckLadang() {

    }

    public void dndDeckLadangMusuh() {

    }

    public void panen(String coor) throws Exception{
        try {
            getCurrentPemain().getLadang().panen(coor);
        } catch (Exception e) {
            throw e;
        }
    }

    public void nextTurn() {
        if (turn != 21) {
            turn += 1;
            for (Pemain p : pemain) {
                p.getLadang().growAllPlant();
            }
            gameState = 0;
        } else {

        }
    }

    public void beli(String produk, int jumlah, int uang) throws Exception {
        try{
//            toko.buy(produk, jumlah, uang);
//            Pemain currentPemain = getCurrentPemain();
//            currentPemain.setUang(currentPemain.getUang()-);
        } catch (Exception e) {
            throw e;
        }
    }

    public void jual(String produk, int indexActiveDeck) throws Exception {

    }

    public void shuffleDeck() {

    }

    public void addKartuToDeck(Kartu kartu) {

    }
}
