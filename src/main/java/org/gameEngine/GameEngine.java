package org.gameEngine;

import org.config.Config;
import org.config.FileHandling;
import org.kartu.Kartu;
import org.ladang.Ladang;
import org.pemain.Pemain;
import org.toko.Toko;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private Integer turn;
    private List<Pemain> pemain;
    private Config config;
    private Toko toko;
    private Integer gameState;
    private FileHandling fileHandling;

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

        Object self;
        fileHandling = new FileHandling(this);
    }

    Pemain getCurrentPemain() {
        return pemain.get((turn-1) % 2);
    }

    Pemain getCurrentLawan() {
        return pemain.get(turn % 2);
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

    public void dndLadangLadang(int colSource, int rowSource, int colDest, int rowDest) throws Exception {
        getCurrentPemain().getLadang().moveObject(Ladang.parseToKey(colSource, rowSource), Ladang.parseToKey(colDest, rowDest));
    }

    public void dndDeckDeck(int indexSource, int indexDest) throws Exception {
        Kartu sourceCard = getCurrentPemain().getActiveDeck().getListKartu()[indexSource];
        Kartu DestCard = getCurrentPemain().getActiveDeck().getListKartu()[indexDest];
        if (sourceCard == null) {
            return;
        } else {
            getCurrentPemain().getActiveDeck().getListKartu()[indexSource] = DestCard;
            getCurrentPemain().getActiveDeck().getListKartu()[indexDest] = sourceCard;
        }

    }

    public void dndDeckLadang(int indexSource, int rowDest, int colDest) throws Exception{
        Kartu sourceCard = getCurrentPemain().getActiveDeck().getListKartu()[indexSource];
        if (sourceCard == null) {
            throw new Exception("Pemindahan Tidak valid!");
        } else {
            getCurrentPemain().getLadang().placeCard(sourceCard, Ladang.parseToKey(colDest, rowDest));
        }
    }

    public void dndDeckLadangMusuh(int indexSource, int rowDest, int colDest) throws Exception {
        Kartu sourceCard = getCurrentPemain().getActiveDeck().getListKartu()[indexSource];
        if (sourceCard == null || sourceCard.getNama().equals("Protect") || !sourceCard.getKategori().equals("Item")) {
            throw new Exception("Pemindahan Tidak valid!");
        } else {
            getCurrentPemain().getLadang().placeCard(sourceCard, Ladang.parseToKey(colDest, rowDest));
        }
    }

    public void panen(String coor) throws Exception{
        getCurrentPemain().getLadang().panen(coor);
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
        if (getCurrentPemain().getActiveDeck().remainingSlot() >= jumlah) {
            List<Kartu> listKartu = toko.buy(produk, jumlah, uang);
            Pemain currentPemain = getCurrentPemain();
            currentPemain.setUang(currentPemain.getUang() - toko.totalHarga(produk, jumlah));
            getCurrentPemain().getActiveDeck().addCard(listKartu);
        } else {
            throw new Exception("Slot tidak cukup!");
        }
    }

    public void jual(String produk, int indexActiveDeck) throws Exception {
        getCurrentPemain().setUang(getCurrentPemain().getUang() + toko.sell(produk));
    }

    public List<Kartu> shuffleDeck(int jumlah) {
        return getCurrentPemain().getShuffleDeck().getShuffleKartu(jumlah);
    }

    public void addKartuToDeck(List<Kartu> listKartu) {
        getCurrentPemain().getActiveDeck().addCard(listKartu);
    }
}
