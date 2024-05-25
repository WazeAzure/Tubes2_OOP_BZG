package org.plugins;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LoaderTXT implements FileLoader {
    private String extention;
    private int turn;
    private int nShopItem;
    private List<InfoItemShop> listItemShop;
    private int[] gulden;
    private int[] jumlahDeck;
    private List<InfoKartuAktif>[] listKartuDeck;
    private List<InfoKartuLadang>[] listKartuLadang;
    private int playerCount;

    public LoaderTXT() {
        this.extention = "txt";
        this.playerCount = 2;
        this.listItemShop = new ArrayList<InfoItemShop>();

        this.gulden = new int[this.playerCount];
        this.jumlahDeck = new int[this.playerCount];

        this.listKartuDeck = (List<InfoKartuAktif>[]) new List[this.playerCount];
        for(int i=0; i<this.playerCount; i++){
            this.listKartuDeck[i] = new ArrayList<>();
        }

        this.listKartuLadang = (List<InfoKartuLadang>[]) new List[this.playerCount];
        for(int i=0; i<this.playerCount; i++){
            this.listKartuLadang[i] = new ArrayList<>();
        }

    }

    @Override
    public void saveFile(String folderPath) throws Exception {
        // folder checking
        Path path = Paths.get(folderPath);
        if(Files.notExists(path)){
            // create folder
            try {
                Files.createDirectories(path);
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        // handle and check each file
        File gamestate = new File(folderPath, "gamestate." + extention);
        File player1 = new File(folderPath, "player1." + extention);
        File player2 = new File(folderPath, "player2." + extention);

//        if(gamestate.exists() || player1.exists() || player2.exists()){
//            throw new IOException("File exist!");
//        }

        // start handling each file

        /* Handle Game State */
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(gamestate));
            writer.write(this.turn + "\n");
            writer.write(this.nShopItem + "\n");
            for(int i=0; i<this.listItemShop.size(); i++){
                writer.write(this.listItemShop.get(i).nama + " " + this.listItemShop.get(i).qty + "\n");
            }
            writer.close();
        } catch(Exception e){
            e.printStackTrace();
        }

        /* Handle Player 1 */
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(player1));
            writer.write(this.gulden[0] + "\n");
            writer.write(this.jumlahDeck[0] + "\n");
            writer.write(this.listKartuDeck[0].size() + "\n");
            for(int i=0; i<this.listKartuDeck[0].size(); i++){
                writer.write(this.listKartuDeck[0].get(i).lokasi + " " + this.listKartuDeck[0].get(i).nama + "\n");
            }

            writer.write(this.listKartuLadang[0].size() + "\n");
            for(int i=0; i<this.listKartuLadang[0].size(); i++){
                InfoKartuLadang t = this.listKartuLadang[0].get(i);
                writer.write(t.lokasi + " " + t.nama + " " + t.umurBerat + " " + t.jumlahItemAktif);
                for(int j=0; j < t.jumlahItemAktif; j++){
                    writer.write(" " + t.itemAktif.get(j));
                }
                writer.write("\n");
            }
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        /* Handle Player 2 */
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(player2));
            writer.write(this.gulden[1] + "\n");
            writer.write(this.jumlahDeck[1] + "\n");
            writer.write(this.listKartuDeck[1].size() + "\n");
            for(int i=0; i<this.listKartuDeck[1].size(); i++){
                writer.write(this.listKartuDeck[1].get(i).lokasi + " " + this.listKartuDeck[1].get(i).nama + "\n");
            }

            writer.write(this.listKartuLadang[1].size() + "\n");
            for(int i=0; i<this.listKartuLadang[1].size(); i++){
                InfoKartuLadang t = this.listKartuLadang[1].get(i);
                writer.write(t.lokasi + " " + t.nama + " " + t.umurBerat + " " + t.jumlahItemAktif);
                for(int j=0; j < t.jumlahItemAktif; j++){
                    writer.write(" " + t.itemAktif.get(j));
                }
                writer.write("\n");
            }
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void loadFile(String folderPath) throws Exception {
        File gamestate = new File(folderPath, "gamestate." + extention);
        File player1 = new File(folderPath, "player1." + extention);
        File player2 = new File(folderPath, "player2." + extention);

        /* Handle GameState */
        try {
            BufferedReader brGamestate = new BufferedReader(new FileReader(gamestate));
            // read current turn
            this.turn = Integer.parseInt(brGamestate.readLine());

            // set shop items
            this.nShopItem = Integer.parseInt(brGamestate.readLine());

            for(int i=0; i<this.nShopItem; i++){
                String[] pair = brGamestate.readLine().trim().split(" ");
                this.listItemShop.add(new InfoItemShop(pair[0], Integer.parseInt(pair[1])));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        /* Handle Player 1 */
        try {
            BufferedReader brPlayer1 = new BufferedReader(new FileReader(player1));
            // read gold
            int gold = Integer.parseInt(brPlayer1.readLine());
            this.gulden[0] = gold;

            // jumlah kartu di deck
            int deck = Integer.parseInt(brPlayer1.readLine());
            this.jumlahDeck[0] = deck;

            // jumlah kartu di dek aktif
            int deck_active = Integer.parseInt(brPlayer1.readLine());
            for(int i=0; i<deck_active; i++){
                // TODO: bikin fungsi untuk set kartu (diskusi besok)
                String[] s = brPlayer1.readLine().trim().split(" ");
                InfoKartuAktif p = new InfoKartuAktif(s[0], s[1]);

                this.listKartuDeck[0].add(p);
            }

            // set ladang
            int n = Integer.parseInt(brPlayer1.readLine());
            for(int i=0; i<n; i++){
                String[] pair = brPlayer1.readLine().trim().split(" ");
                String coor = pair[0];
                String name = pair[1];
                int weightOrAge = Integer.parseInt(pair[2]);

                int activeCard = Integer.parseInt(pair[3]);

                List<String> activeItemCard = new ArrayList<>();
                for(int j=0; j<activeCard; j++){
                    activeItemCard.add(pair[4+j]);
                }

                InfoKartuLadang p = new InfoKartuLadang(coor, name, weightOrAge, activeCard, activeItemCard);
                this.listKartuLadang[0].add(p);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        /* Handle Player 2 */
        try {
            BufferedReader brPlayer2 = new BufferedReader(new FileReader(player2));
            // read gold
            int gold = Integer.parseInt(brPlayer2.readLine());
            this.gulden[1] = gold;

            // jumlah kartu di deck
            int deck = Integer.parseInt(brPlayer2.readLine());
            this.jumlahDeck[1] = deck;

            // jumlah kartu di dek aktif
            int deck_active = Integer.parseInt(brPlayer2.readLine());
            for(int i=0; i<deck_active; i++){
                // TODO: bikin fungsi untuk set kartu (diskusi besok)
                String[] s = brPlayer2.readLine().trim().split(" ");
                InfoKartuAktif p = new InfoKartuAktif(s[0], s[1]);

                this.listKartuDeck[1].add(p);
            }

            // set ladang
            int n = Integer.parseInt(brPlayer2.readLine());
            for(int i=0; i<n; i++){
                String[] pair = brPlayer2.readLine().trim().split(" ");
                String coor = pair[0];
                String name = pair[1];
                int weightOrAge = Integer.parseInt(pair[2]);

                int activeCard = Integer.parseInt(pair[3]);

                List<String> activeItemCard = new ArrayList<>();
                for(int j=0; j<activeCard; j++){
                    activeItemCard.add(pair[4+j]);
                }

                InfoKartuLadang p = new InfoKartuLadang(coor, name, weightOrAge, activeCard, activeItemCard);
                this.listKartuLadang[1].add(p);
                // TODO: fungsi untuk update kondisi ladang (diskusi besok)
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String getSupportedExtension() {
        return this.extention;
    }

    @Override
    public boolean supports(String fileName) {
        if(fileName.endsWith("."+this.extention)){
            return true;
        }
        return false;
    }

    @Override
    public int getCurrentTurn() {
        return this.turn;
    }

    @Override
    public int getNTokoItem() {
        return this.nShopItem;
    }

    @Override
    public List<InfoItemShop> getItemAndQty() {
        return this.listItemShop;
    }

    @Override
    public int getCurrentPlayer() {
        return this.turn;
    }

    @Override
    public int getGulden(int pemain) {
        return this.gulden[pemain];
    }

    @Override
    public int getJumlahDeck(int pemain) {
        return this.jumlahDeck[pemain];
    }

    @Override
    public List<InfoKartuAktif> getKartuDeckAktif(int pemain) {
        return this.listKartuDeck[pemain];
    }

    @Override
    public List<InfoKartuLadang> getListKartuLadang(int pemain) {
        return this.listKartuLadang[pemain];
    }

    @Override
    public void setCurrentTurn(int turn) {
        this.turn = turn;
    }

    @Override
    public void setNTokoItem(int n) {
        this.nShopItem = n;
    }

    @Override
    public void setItemAndQty(List<InfoItemShop> shop) {
        this.listItemShop = shop;
    }

    @Override
    public void setGulden(int pemain, int gulden) {
        this.gulden[pemain] = gulden;
    }

    @Override
    public void setJumlahDeck(int pemain, int jumlahDeck) {
        this.jumlahDeck[pemain] = jumlahDeck;
    }

    @Override
    public void setKartuDeckAktif(int pemain, List<InfoKartuAktif> kartuDeckAktif) {
        this.listKartuDeck[pemain] = kartuDeckAktif;
    }

    @Override
    public void setListKartuLadang(int pemain, List<InfoKartuLadang> kartuLadang) {
        this.listKartuLadang[pemain] = kartuLadang;
    }
}
