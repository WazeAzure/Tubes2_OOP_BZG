import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class LoaderXML implements FileLoader {
    private String extention;
    private int turn;
    private int nShopItem;
    private List<InfoItemShop> listItemShop;
    private int[] gulden;
    private int[] jumlahDeck;
    private List<InfoKartuAktif>[] listKartuDeck;
    private List<InfoKartuLadang>[] listKartuLadang;
    private int playerCount;

    public LoaderXML() {
        this.extention = "xml";
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
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        File gamestate = new File(folderPath + "\\gamestate." + extention);
        File player1 = new File(folderPath + "\\player1." + extention);
        File player2 = new File(folderPath + "\\player2." + extention);

        /* Handle GameState */
        try {
            Document docGameState = builder.parse(gamestate);
            // Get root element
            Element root = docGameState.getDocumentElement();

            this.turn = Integer.parseInt(root.getElementsByTagName("CURRENT_TURN").item(0).getTextContent());
            this.nShopItem = Integer.parseInt(root.getElementsByTagName("JUMLAH_ITEM_DI_SHOP").item(0).getTextContent());

            NodeList shops = root.getElementsByTagName("SHOPS");
            NodeList itemList = shops.item(0).getChildNodes();
            for(int i=0; i<this.nShopItem; i++){
                Node t = itemList.item(i);
                String name = t.getFirstChild().getTextContent();
                int qty = Integer.parseInt(t.getLastChild().getTextContent());
                this.listItemShop.add(new InfoItemShop(name, qty));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        /* Handle Player 1 */
        try {
            Document docPlayer1 = builder.parse(player1);

            // Get root element
            Element root = docPlayer1.getDocumentElement();

            this.gulden[0] = Integer.parseInt(root.getElementsByTagName("JUMLAH_GULDEN").item(0).getTextContent());
            this.jumlahDeck[0] = Integer.parseInt(root.getElementsByTagName("JUMLAH_DECK").item(0).getTextContent());

            int deck_active = Integer.parseInt(root.getElementsByTagName("JUMLAH_DECK_AKTIF").item(0).getTextContent());
            NodeList shops = root.getElementsByTagName("DECK");
            NodeList itemList = shops.item(0).getChildNodes();
            for(int i=0; i<deck_active; i++){
                Node t = itemList.item(i);
                String lokasi = t.getFirstChild().getTextContent();
                String nama = t.getLastChild().getTextContent();
                this.listKartuDeck[0].add(new InfoKartuAktif(lokasi, nama));
            }

            // set ladang
            int n = Integer.parseInt(root.getElementsByTagName("JUMLAH_KARTU_LADANG").item(0).getTextContent());
            NodeList ladang = root.getElementsByTagName("LADANG");
            NodeList kartu = ladang.item(0).getChildNodes();
            for(int i=0; i<n; i++){
                NodeList t = kartu.item(i).getChildNodes();
                String lokasi = t.item(0).getTextContent();
                String nama = t.item(1).getTextContent();
                int umurBerat = Integer.parseInt(t.item(2).getTextContent());
                int jumlahItemAktif = Integer.parseInt(t.item(3).getTextContent());

                String[] activeItemCard = new String[jumlahItemAktif];
                NodeList itemAktif = t.item(4).getChildNodes();
                for(int j=0; j<jumlahItemAktif; j++){
                    activeItemCard[j] = itemAktif.item(j).getTextContent();
                }

                InfoKartuLadang p = new InfoKartuLadang(lokasi, nama, umurBerat, jumlahItemAktif, activeItemCard);
                this.listKartuLadang[0].add(p);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        /* Handle Player 2 */
        try {
            Document docPlayer2 = builder.parse(player2);

            // Get root element
            Element root = docPlayer2.getDocumentElement();

            this.gulden[1] = Integer.parseInt(root.getElementsByTagName("JUMLAH_GULDEN").item(0).getTextContent());
            this.jumlahDeck[1] = Integer.parseInt(root.getElementsByTagName("JUMLAH_DECK").item(0).getTextContent());

            int deck_active = Integer.parseInt(root.getElementsByTagName("JUMLAH_DECK_AKTIF").item(0).getTextContent());
            NodeList shops = root.getElementsByTagName("DECK");
            NodeList itemList = shops.item(0).getChildNodes();
            for(int i=0; i<deck_active; i++){
                Node t = itemList.item(i);
                String lokasi = t.getFirstChild().getTextContent();
                String nama = t.getLastChild().getTextContent();
                this.listKartuDeck[1].add(new InfoKartuAktif(lokasi, nama));
            }

            // set ladang
            int n = Integer.parseInt(root.getElementsByTagName("JUMLAH_KARTU_LADANG").item(0).getTextContent());
            NodeList ladang = root.getElementsByTagName("LADANG");
            NodeList kartu = ladang.item(0).getChildNodes();
            for(int i=0; i<n; i++){
                NodeList t = kartu.item(i).getChildNodes();
                String lokasi = t.item(0).getTextContent();
                String nama = t.item(1).getTextContent();
                int umurBerat = Integer.parseInt(t.item(2).getTextContent());
                int jumlahItemAktif = Integer.parseInt(t.item(3).getTextContent());

                String[] activeItemCard = new String[jumlahItemAktif];
                NodeList itemAktif = t.item(4).getChildNodes();
                for(int j=0; j<jumlahItemAktif; j++){
                    activeItemCard[j] = itemAktif.item(j).getTextContent();
                }

                InfoKartuLadang p = new InfoKartuLadang(lokasi, nama, umurBerat, jumlahItemAktif, activeItemCard);
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
}
