import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
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
            JSONObject mainPage = new JSONObject();
            mainPage.put("CURRENT_TURN", this.turn);
            mainPage.put("JUMLAH_ITEM_DI_SHOP", this.nShopItem);

            JSONArray listItemJSON = new JSONArray();
            for(int i=0; i<this.listItemShop.size(); i++){
                JSONObject itemJSON = new JSONObject();
                InfoItemShop p = this.listItemShop.get(i);
                itemJSON.put(p.nama, p.qty);
                listItemJSON.add(itemJSON);
            }

            JSONObject item = new JSONObject();
            item.put("ITEM", listItemJSON);
            mainPage.put("SHOPS", item);

            BufferedWriter writer = new BufferedWriter(new FileWriter(gamestate));
            writer.write(mainPage.toJSONString());
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        /* Handle Player 1 */
        try {
            JSONObject mainPage = new JSONObject();
            mainPage.put("JUMLAH_GULDEN", this.gulden[0]);
            mainPage.put("JUMLAH_DECK", this.jumlahDeck[0]);

            JSONArray listDeckAktif = new JSONArray();
            List<InfoKartuAktif> listA = this.listKartuDeck[0];
            mainPage.put("JUMLAH_DECK_AKTIF", listA.size());
            for(int i=0; i<listA.size(); i++){
                JSONObject itemJSON = new JSONObject();
                itemJSON.put("LOKASI", listA.get(i).lokasi);
                itemJSON.put("NAMA", listA.get(i).nama);

                listDeckAktif.add(itemJSON);
            }
            JSONObject deck = new JSONObject();
            deck.put("KARTU", listDeckAktif);
            mainPage.put("DECK", deck);

            JSONArray listLadangAktif = new JSONArray();
            List<InfoKartuLadang> listB = this.listKartuLadang[0];
            mainPage.put("JUMLAH_KARTU_LADANG", listB.size());
            for(int i=0; i<listB.size(); i++){
                JSONObject itemJSON = new JSONObject();
                itemJSON.put("LOKASI", listB.get(i).lokasi);
                itemJSON.put("NAMA", listB.get(i).nama);
                itemJSON.put("UMUR_BERAT", listB.get(i).umurBerat);
                itemJSON.put("JUMLAH_ITEM_AKTIF", listB.get(i).jumlahItemAktif);

                JSONArray jsonItemAktif = new JSONArray();
                for(int j=0; j<listB.get(i).jumlahItemAktif; j++){
                    jsonItemAktif.add(listB.get(i).itemAktif.get(j));
                }
                JSONObject itemAKtif = new JSONObject();
                itemAKtif.put("ITEM", jsonItemAktif);
                itemJSON.put("ITEM_AKTIF", itemAKtif);

                listLadangAktif.add(itemJSON);
            }
            JSONObject kartuLadang = new JSONObject();
            kartuLadang.put("KARTU", listLadangAktif);
            mainPage.put("LADANG", kartuLadang);

            BufferedWriter writer = new BufferedWriter(new FileWriter(player1));
            writer.write(mainPage.toJSONString());
            writer.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        /* Handle Player 2 */
        try {
            JSONObject mainPage = new JSONObject();
            mainPage.put("JUMLAH_GULDEN", this.gulden[1]);
            mainPage.put("JUMLAH_DECK", this.jumlahDeck[1]);

            JSONArray listDeckAktif = new JSONArray();
            List<InfoKartuAktif> listA = this.listKartuDeck[1];
            mainPage.put("JUMLAH_DECK_AKTIF", listA.size());
            for(int i=0; i<listA.size(); i++){
                JSONObject itemJSON = new JSONObject();
                itemJSON.put("LOKASI", listA.get(i).lokasi);
                itemJSON.put("NAMA", listA.get(i).nama);

                listDeckAktif.add(itemJSON);
            }
            JSONObject deck = new JSONObject();
            deck.put("KARTU", listDeckAktif);
            mainPage.put("DECK", deck);

            JSONArray listLadangAktif = new JSONArray();
            List<InfoKartuLadang> listB = this.listKartuLadang[1];
            mainPage.put("JUMLAH_KARTU_LADANG", listB.size());
            for(int i=0; i<listB.size(); i++){
                JSONObject itemJSON = new JSONObject();
                itemJSON.put("LOKASI", listB.get(i).lokasi);
                itemJSON.put("NAMA", listB.get(i).nama);
                itemJSON.put("UMUR_BERAT", listB.get(i).umurBerat);
                itemJSON.put("JUMLAH_ITEM_AKTIF", listB.get(i).jumlahItemAktif);

                JSONArray jsonItemAktif = new JSONArray();
                for(int j=0; j<listB.get(i).jumlahItemAktif; j++){
                    jsonItemAktif.add(listB.get(i).itemAktif.get(j));
                }
                JSONObject itemAKtif = new JSONObject();
                itemAKtif.put("ITEM", jsonItemAktif);
                itemJSON.put("ITEM_AKTIF", itemAKtif);

                listLadangAktif.add(itemJSON);
            }
            JSONObject kartuLadang = new JSONObject();
            kartuLadang.put("KARTU", listLadangAktif);
            mainPage.put("LADANG", kartuLadang);

            BufferedWriter writer = new BufferedWriter(new FileWriter(player2));
            writer.write(mainPage.toJSONString());
            writer.close();

        } catch (Exception e){
            e.printStackTrace();
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

            this.turn = currentTurn;
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
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        /* Handle Player1 */
        try {
            String contentPlayer1 = new String(Files.readAllBytes(Paths.get(player1.getAbsolutePath())));
            JSONObject root = (JSONObject) JSONValue.parse(contentPlayer1);

            this.gulden[0] = (int) (long) root.get("JUMLAH_GULDEN");
            this.jumlahDeck[0] = (int) (long) root.get("JUMLAH_DECK");

            int deckAktif = (int) (long) root.get("JUMLAH_DECK_AKTIF");
            JSONObject Deck = (JSONObject) root.get("DECK");
            JSONArray kartuDeck = (JSONArray) Deck.get("KARTU");
            for(Object kartu: kartuDeck){
                JSONObject itemObject = (JSONObject) kartu;
                String lokasi = (String) itemObject.get("LOKASI");
                String nama = (String) itemObject.get("NAMA");

                this.listKartuDeck[0].add(new InfoKartuAktif(lokasi, nama));
            }

            int nKartuLadang = (int) (long) root.get("JUMLAH_KARTU_LADANG");
            JSONObject Ladang = (JSONObject) root.get("LADANG");
            JSONArray kartuLadang = (JSONArray) Ladang.get("KARTU");
            for(Object kartu: kartuLadang){
                JSONObject itemObject = (JSONObject) kartu;
                String lokasi = (String) itemObject.get("LOKASI");
                String nama = (String) itemObject.get("NAMA");
                int umurBerat = (int) (long) itemObject.get("UMUR_BERAT");
                int jumlahItemAktif = (int) (long) itemObject.get("JUMLAH_ITEM_AKTIF");

                JSONObject itemAktif = (JSONObject) itemObject.get("ITEM_AKTIF");
                JSONArray listItem = (JSONArray) itemAktif.get("ITEM");

                List<String> listItemAktif = new ArrayList<>();
                for(Object s : listItem){
                    listItemAktif.add((String) s);
                }

                InfoKartuLadang p = new InfoKartuLadang(lokasi, nama, umurBerat, jumlahItemAktif, listItemAktif);
                this.listKartuLadang[0].add(p);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        /* Handle Player2 */
        try {
            String contentPlayer2 = new String(Files.readAllBytes(Paths.get(player2.getAbsolutePath())));
            JSONObject root = (JSONObject) JSONValue.parse(contentPlayer2);

            this.gulden[1] = (int) (long) root.get("JUMLAH_GULDEN");
            this.jumlahDeck[1] = (int) (long) root.get("JUMLAH_DECK");

            int deckAktif = (int) (long) root.get("JUMLAH_DECK_AKTIF");
            JSONObject Deck = (JSONObject) root.get("DECK");
            JSONArray kartuDeck = (JSONArray) Deck.get("KARTU");
            for(Object kartu: kartuDeck){
                JSONObject itemObject = (JSONObject) kartu;
                String lokasi = (String) itemObject.get("LOKASI");
                String nama = (String) itemObject.get("NAMA");

                this.listKartuDeck[1].add(new InfoKartuAktif(lokasi, nama));
            }

            int nKartuLadang = (int) (long) root.get("JUMLAH_KARTU_LADANG");
            JSONObject Ladang = (JSONObject) root.get("LADANG");
            JSONArray kartuLadang = (JSONArray) Ladang.get("KARTU");
            for(Object kartu: kartuLadang){
                JSONObject itemObject = (JSONObject) kartu;
                String lokasi = (String) itemObject.get("LOKASI");
                String nama = (String) itemObject.get("NAMA");
                int umurBerat = (int) (long) itemObject.get("UMUR_BERAT");
                int jumlahItemAktif = (int) (long) itemObject.get("JUMLAH_ITEM_AKTIF");

                JSONArray listItem = (JSONArray) itemObject.get("ITEM_AKTIF");

                List<String> listItemAktif = new ArrayList<>();
                int counter = 0;
                for(Object s : listItem){
                    listItemAktif.add((String) s);
                    counter++;
                }

                InfoKartuLadang p = new InfoKartuLadang(lokasi, nama, umurBerat, jumlahItemAktif, listItemAktif);
                this.listKartuLadang[1].add(p);
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
