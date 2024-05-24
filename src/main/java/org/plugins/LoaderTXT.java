package org.plugins;

import org.gui.Load;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

                String[] activeItemCard = new String[activeCard];
                for(int j=0; j<activeCard; j++){
                    activeItemCard[j] = pair[4+j];
                }

                InfoKartuLadang p = new InfoKartuLadang(coor, name, weightOrAge, activeCard, activeItemCard);
                this.listKartuLadang[0].add(p);
                // TODO: fungsi untuk update kondisi ladang (diskusi besok)
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

                String[] activeItemCard = new String[activeCard];
                for(int j=0; j<activeCard; j++){
                    activeItemCard[j] = pair[4+j];
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
}
