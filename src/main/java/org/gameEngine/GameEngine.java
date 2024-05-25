package org.gameEngine;

import org.config.Config;
import org.config.FileHandling;
import org.deck.ShuffleDeck;
import org.grid.Grid;
import org.kartu.Kartu;
import org.kartu.harvestable.Harvestable;
import org.ladang.Ladang;
import org.pemain.Pemain;
import org.toko.Toko;

import java.io.File;
import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameEngine {
    private Integer turn;
    private List<Pemain> pemain;
    private Config config;
    private Toko toko;
    private Integer gameState;
    private Map<String, Class> pluginMap;
    private FileHandling fileHandling;

    public GameEngine() {
        // Inisialisasi Config
        config = new Config();
        config.loadConfig();

        // Inisialisasi turn;
        turn = 1;

        // Inisialisasi Pemain
        pemain = new ArrayList<Pemain>();
        pemain.add(new Pemain(1));
        pemain.add(new Pemain(2));


        // Inisialisasi Toko
        toko = new Toko();

        // Inisialisasi gameState
        // 0 = Shuffle Kartu, 1 = Serangan Beruang, 2 = Aksi Bebas
        gameState = 0;

        Object self;
        fileHandling = new FileHandling(this);
    }

    public Pemain getCurrentPemain() {
        return pemain.get((turn-1) % 2);
    }

    public Pemain getCurrentLawan() {
        return pemain.get(turn % 2);
    }

    public List<Pemain> getListPemain() {
        return pemain;
    }

    public Integer getTurn() {
        return turn;
    }

    public Integer getGameState() {
        return gameState;
    }

    public void setGameState(Integer gameState) {
        this.gameState = gameState;
    }

    public Toko getToko() {
        return toko;
    }

    public Config getConfig() {
        return config;
    }

    public void dndLadangLadang(int colSource, int rowSource, int colDest, int rowDest) throws Exception {
        getCurrentPemain().getLadang().moveObject(Ladang.parseToKey(colSource, rowSource), Ladang.parseToKey(colDest, rowDest));
    }

    public void dndDeckDeck(int indexSource, int indexDest) throws Exception {
        Kartu sourceCard = getCurrentPemain().getActiveDeck().getListKartu().get(Grid.parseToKey(indexSource, 0));
        Kartu DestCard = getCurrentPemain().getActiveDeck().getListKartu().get(Grid.parseToKey(indexDest, 0));
        if (sourceCard == null) {
            return;
        } else {
            getCurrentPemain().getActiveDeck().getListKartu().put(Grid.parseToKey(indexSource, 0), DestCard);
            getCurrentPemain().getActiveDeck().getListKartu().put(Grid.parseToKey(indexDest, 0), sourceCard);
        }
    }

    public void dndDeckLadang(int indexSource, int rowDest, int colDest) throws Exception{
        Kartu sourceCard = getCurrentPemain().getActiveDeck().getListKartu().get(Grid.parseToKey(indexSource, 0));
        if (sourceCard == null) {
            throw new Exception("Pemindahan Tidak valid!");
        } else if (sourceCard.getNama().equals("DESTROY") || sourceCard.getNama().equals("DELAY")) {
            throw new Exception("Item/Kartu Tidak valid!");
        } else if (sourceCard.getNama().equals("LAYOUT")) {
            getCurrentPemain().getLadang().placeCard(sourceCard, true);
        }{
            List<Kartu> temp = new ArrayList<>();
            temp.add(getCurrentPemain().getLadang().placeCard(sourceCard, Ladang.parseToKey(colDest, rowDest)));
            getCurrentPemain().getActiveDeck().removeCard(Grid.parseToKey(indexSource, 0));
            if (temp != null) {
                getCurrentPemain().getActiveDeck().addCard(temp);
            }
        }
    }

    public void dndDeckLadangMusuh(int indexSource, int rowDest, int colDest) throws Exception {
        Kartu sourceCard = getCurrentPemain().getActiveDeck().getListKartu().get(Grid.parseToKey(indexSource, 0));
        if (sourceCard == null) {
            throw new Exception("Pemindahan Tidak valid!");
        } else if (!(sourceCard.getNama().equals("DESTROY") || sourceCard.getNama().equals("DELAY"))) {
            throw new Exception("Item/Kartu Tidak valid!");
        } else if (sourceCard.getNama().equals("LAYOUT")) {
            int size = getCurrentLawan().getLadang().placeCard(sourceCard, false).size();
            if (size != 0) {
                getCurrentPemain().setShuffleDeck(new ShuffleDeck(getCurrentPemain().getShuffleDeck().getRemainingCard() + size));
            }
        }else {
            getCurrentLawan().getLadang().placeCard(sourceCard, Ladang.parseToKey(colDest, rowDest));
            getCurrentPemain().getActiveDeck().removeCard(Grid.parseToKey(indexSource, 0));
        }
    }

    public void panen(int row, int col) throws Exception{
        List<Kartu> temp = new ArrayList<>();
        temp.add(getCurrentPemain().getLadang().panen(Ladang.parseToKey(col, row)));
        getCurrentPemain().getActiveDeck().addCard(temp);
    }

    public void nextTurn() {
        if (turn != 21) {
            turn += 1;
            for (Pemain p : pemain) {
                p.getLadang().growAllPlant();
                if (p.getLadang().getLayoutChange() != 0) {
                    p.getLadang().decLayoutTurn();
                    if (p.getLadang().getLayoutTurn() == 0) {
                        p.getLadang().makeNormal();
                    }
                }
            }
            setGameState(0);
        }
    }

    public void beli(String produk, int jumlah) throws Exception {
        if (getCurrentPemain().getActiveDeck().remainingSlot() >= jumlah) {
            List<Kartu> listKartu = toko.buy(produk, jumlah, getCurrentPemain().getUang());
            Pemain currentPemain = getCurrentPemain();
            currentPemain.setUang(currentPemain.getUang() - toko.totalHarga(produk, jumlah));
            getCurrentPemain().getActiveDeck().addCard(listKartu);
        } else {
            throw new Exception("Slot tidak cukup!");
        }
    }

    public void jual(String produk) throws Exception {
        getCurrentPemain().setUang(getCurrentPemain().getUang() + toko.sell(produk));
        for (var entry : getCurrentPemain().getActiveDeck().getListKartu().entrySet()) {
            if (entry != null) {
                if (entry.getValue().getNama().equals(produk)) {
                    getCurrentPemain().getActiveDeck().removeCard(entry.getKey());
                    break;
                }
            }
        }
    }

    public List<Kartu> shuffleDeck() {
        int jumlahKartu = getCurrentPemain().getActiveDeck().remainingSlot();
        if (jumlahKartu > 4) {
            jumlahKartu = 4;
        }
        return getCurrentPemain().getShuffleDeck().getShuffleKartu(jumlahKartu);
    }

    public void addKartuToDeck(List<Kartu> listKartu) {
        getCurrentPemain().getActiveDeck().addCard(listKartu);
    }

    public void addPlugin(String filePath) {
        // TODO: bikin fungsi untuk load plugin
        fileHandling.loadPlugin(filePath);
    }

    public void resetGame(int gameState) {
        // Inisialisasi turn;
        turn = 1;

        // Inisialisasi Pemain
        pemain = new ArrayList<Pemain>();
        pemain.add(new Pemain(1));
        pemain.add(new Pemain(2));


        // Inisialisasi Toko
        toko = new Toko();

        // Inisialisasi gameState
        // 0 = Shuffle Kartu, 1 = Serangan Beruang, 2 = Aksi Bebas
        this.gameState = gameState;

        Object self;
        fileHandling = new FileHandling(this);
    }

    public void setTurn(int turn){
        this.turn = turn;
    }

    public void setItemInToko(String name, int qty) {
        this.toko.setItemStock(name, qty);
    }

    public void setPemainGulden(int pemain, int gulden){
        this.pemain.get(pemain).setUang(gulden);
    }

    public void setPemainJumlahDeck(int pemain, int jumlahDeck){
        this.getListPemain().get(pemain).setShuffleDeck(new ShuffleDeck(jumlahDeck));
    }

    public void setKartuDeckAktif(int pemain, String koordinat, String kartu){
        this.getListPemain().get(pemain).getActiveDeck().getListKartu().put(koordinat, Config.buildKartu(kartu));
    }

    public void setKartuLadang(int pemain, String lokasi, String nama, int umurBerat, List<String> itemAktif){
        // TODO: bikin fungsi set suatu kartu di ladang
        Harvestable h = (Harvestable) Config.buildKartu(nama);
        if (h != null) {
            h.setValue(umurBerat);
            h.setValueEfek(umurBerat);
            for (String s : itemAktif) {
                h.applyEfek(s);
            }
            this.getListPemain().get(pemain).getLadang().getLadang().put(lokasi, h);
        }
    }

    public void loadSaveFile(String filepath, String extension) {
        fileHandling.load(filepath, extension);
    }

    public void saveFile(String filePath, String extention){
        fileHandling.save(filePath, extention);
    }
}
