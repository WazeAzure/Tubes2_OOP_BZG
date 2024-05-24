package org.plugins;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoaderJSON implements FileLoader {
    private String extention;
    private int turn;
    private int nShopItem;
    private List<InfoItemShop> listItemShop;
    private int[] gulden;
    private int[] jumlahDeck;
    private List<InfoKartuAktif>[] listKartuDeck;
    private List<InfoKartuLadang>[] listKartuLadang;
    private int playerCount;

    public LoaderJSON(){
        this.extention = "json";
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
        File gamestate = new File(folderPath + "\\gamestate." + extention);
        File player1 = new File(folderPath + "\\player1." + extention);
        File player2 = new File(folderPath + "\\player2." + extention);

        /* Handle GameState */
        try {
            String contentGameState = new String(Files.readAllBytes(Paths.get(gamestate.getAbsolutePath())));
            JSONObject root = (JSONObject) JSONValue.parse(contentGameState);
            // Get values from the JSON object
            int currentTurn = (int) (long) root.get("CURRENT_TURN");
            int jumlahItemDiShop = (int) (long) root.get("JUMLAH_ITEM_DI_SHOP");

            System.out.println("CURRENT_TURN: " + currentTurn);
            this.turn = currentTurn;
            System.out.println("JUMLAH_ITEM_DI_SHOP: " + jumlahItemDiShop);
            this.nShopItem = jumlahItemDiShop;

            // Get the "SHOPS" object
            JSONObject shops = (JSONObject) root.get("SHOPS");
            JSONArray items = (JSONArray) shops.get("ITEM");

            // Iterate over the items
            for (Object item : items) {
                JSONObject itemObject = (JSONObject) item;
                String itemName = (String) itemObject.get("ITEM_NAME");
                int itemQty = (int) (long) itemObject.get("ITEM_QTY");

                this.listItemShop.add(new InfoItemShop(itemName, itemQty));

                System.out.println("ITEM_NAME: " + itemName + ", ITEM_QTY: " + itemQty);
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
