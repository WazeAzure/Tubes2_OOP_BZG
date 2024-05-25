package org.plugins;

import org.plugins.FileLoader;
import org.plugins.InfoItemShop;
import org.plugins.InfoKartuAktif;
import org.plugins.InfoKartuLadang;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
            // Create a DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Create a new Document
            Document document = builder.newDocument();

            // Create root element
            Element root = document.createElement("gamestate");
            document.appendChild(root);

            // Create elements and add text content
            Element elmtTurn = document.createElement("CURRENT_TURN");
            elmtTurn.appendChild(document.createTextNode("" + this.turn));

            Element elemtJumlahItemShop = document.createElement("JUMLAH_ITEM_DI_SHOP");
            elemtJumlahItemShop.appendChild(document.createTextNode("" + this.nShopItem));

            Element elmtShops = document.createElement("SHOPS");
            for(InfoItemShop x: this.listItemShop){
                Element itemShop = document.createElement("ITEM");

                Element itemName = document.createElement("ITEM_NAME");
                itemName.appendChild(document.createTextNode(x.nama));

                Element itemQty = document.createElement("ITEM_QTY");
                itemQty.appendChild(document.createTextNode("" + x.qty));

                itemShop.appendChild(itemName);
                itemShop.appendChild(itemQty);

                elmtShops.appendChild(itemShop);
            }

            if(this.listItemShop.size() == 0){
                elmtShops.appendChild(document.createTextNode("\n"));
            }

            if(this.listItemShop.size() == 0){
                elmtShops.appendChild(document.createTextNode("\n"));
            }

            root.appendChild(elmtTurn);
            root.appendChild(elemtJumlahItemShop);
            root.appendChild(elmtShops);

            // Write to XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);

            // Specify your local file path
            StreamResult result = new StreamResult(gamestate);
            transformer.transform(source, result);
        } catch (Exception e){
            e.printStackTrace();
        }

        /* Handle Player 1 */
        try {
            // Create a DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Create a new Document
            Document document = builder.newDocument();

            // Create root element
            Element root = document.createElement("PLAYER");
            document.appendChild(root);

            // Create elements and add text content
            Element elmtGulden = document.createElement("JUMLAH_GULDEN");
            elmtGulden.appendChild(document.createTextNode("" + this.gulden[0]));

            Element elmtJumlahDeck = document.createElement("JUMLAH_DECK");
            elmtJumlahDeck.appendChild(document.createTextNode("" + this.jumlahDeck[0]));

            Element elmtJumlahDeckAktif = document.createElement("JUMLAH_DECK_AKTIF");
            elmtJumlahDeckAktif.appendChild(document.createTextNode("" + this.listKartuDeck[0].size()));

            Element elmtDeck = document.createElement("DECK");
            for(InfoKartuAktif x: this.listKartuDeck[0]){
                Element kartu = document.createElement("KARTU");

                Element lokasiKartu = document.createElement("LOKASI");
                lokasiKartu.appendChild(document.createTextNode(x.lokasi));

                Element namaKartu = document.createElement("NAMA");
                namaKartu.appendChild(document.createTextNode(x.nama));

                kartu.appendChild(lokasiKartu);
                kartu.appendChild(namaKartu);

                elmtDeck.appendChild(kartu);
            }

            if(this.listKartuDeck[0].size() == 0){
                elmtDeck.appendChild(document.createTextNode("\n"));
            }

            Element jumlahKartuLadang = document.createElement("JUMLAH_KARTU_LADANG");
            jumlahKartuLadang.appendChild(document.createTextNode("" + this.listKartuLadang[0].size()));

            Element elmtLadang = document.createElement("LADANG");
            for(InfoKartuLadang x: this.listKartuLadang[0]){
                Element kartuLadang = document.createElement("KARTU");

                Element lokasiKartu = document.createElement("LOKASI");
                lokasiKartu.appendChild(document.createTextNode(x.lokasi));

                Element namaKartu = document.createElement("NAMA");
                namaKartu.appendChild(document.createTextNode(x.nama));

                Element umurBerat = document.createElement("UMUR_BERAT");
                umurBerat.appendChild(document.createTextNode("" + x.umurBerat));

                Element jumlahItemAktif = document.createElement("JUMLAH_ITEM_AKTIF");
                jumlahItemAktif.appendChild(document.createTextNode("" + x.jumlahItemAktif));



                Element itemAKtif = document.createElement("ITEM_AKTIF");


                for(int j=0; j<x.itemAktif.size(); j++){
                    Element item = document.createElement("ITEM");
                    item.appendChild(document.createTextNode(x.itemAktif.get(j)));

                    itemAKtif.appendChild(item);
                }

                if(x.itemAktif.size() == 0){
                    itemAKtif.appendChild(document.createTextNode("\n"));
                }

                kartuLadang.appendChild(lokasiKartu);
                kartuLadang.appendChild(namaKartu);
                kartuLadang.appendChild(umurBerat);
                kartuLadang.appendChild(jumlahItemAktif);
                kartuLadang.appendChild(itemAKtif);

                elmtLadang.appendChild(kartuLadang);
            }

            if(this.listKartuLadang[0].size() == 0){
                elmtLadang.appendChild(document.createTextNode("\n"));
            }

            root.appendChild(elmtGulden);
            root.appendChild(elmtJumlahDeck);
            root.appendChild(elmtDeck);
            root.appendChild(jumlahKartuLadang);
            root.appendChild(elmtLadang);

            // Write to XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);

            // Specify your local file path
            StreamResult result = new StreamResult(player1);
            transformer.transform(source, result);
        } catch (Exception e){
            e.printStackTrace();
        }

        /* Handle Player 2 */
        try {
            // Create a DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Create a new Document
            Document document = builder.newDocument();

            // Create root element
            Element root = document.createElement("PLAYER");
            document.appendChild(root);

            // Create elements and add text content
            Element elmtGulden = document.createElement("JUMLAH_GULDEN");
            elmtGulden.appendChild(document.createTextNode("" + this.gulden[1]));

            Element elmtJumlahDeck = document.createElement("JUMLAH_DECK");
            elmtJumlahDeck.appendChild(document.createTextNode("" + this.jumlahDeck[1]));

            Element elmtJumlahDeckAktif = document.createElement("JUMLAH_DECK_AKTIF");
            elmtJumlahDeckAktif.appendChild(document.createTextNode("" + this.listKartuDeck[1].size()));

            Element elmtDeck = document.createElement("DECK");
            for(InfoKartuAktif x: this.listKartuDeck[1]){
                Element kartu = document.createElement("KARTU");

                Element lokasiKartu = document.createElement("LOKASI");
                lokasiKartu.appendChild(document.createTextNode(x.lokasi));

                Element namaKartu = document.createElement("NAMA");
                namaKartu.appendChild(document.createTextNode(x.nama));

                kartu.appendChild(lokasiKartu);
                kartu.appendChild(namaKartu);

                elmtDeck.appendChild(kartu);
            }

            if(this.listKartuDeck[1].size() == 0){
                elmtDeck.appendChild(document.createTextNode("\n"));
            }

            Element jumlahKartuLadang = document.createElement("JUMLAH_KARTU_LADANG");
            jumlahKartuLadang.appendChild(document.createTextNode("" + this.listKartuLadang[1].size()));

            Element elmtLadang = document.createElement("LADANG");
            for(InfoKartuLadang x: this.listKartuLadang[1]){
                Element kartuLadang = document.createElement("KARTU");

                Element lokasiKartu = document.createElement("LOKASI");
                lokasiKartu.appendChild(document.createTextNode(x.lokasi));

                Element namaKartu = document.createElement("NAMA");
                namaKartu.appendChild(document.createTextNode(x.nama));

                Element umurBerat = document.createElement("UMUR_BERAT");
                umurBerat.appendChild(document.createTextNode("" + x.umurBerat));

                Element jumlahItemAktif = document.createElement("JUMLAH_ITEM_AKTIF");
                jumlahItemAktif.appendChild(document.createTextNode("" + x.jumlahItemAktif));

                Element itemAKtif = document.createElement("ITEM_AKTIF");

                kartuLadang.appendChild(lokasiKartu);
                kartuLadang.appendChild(namaKartu);
                kartuLadang.appendChild(umurBerat);
                kartuLadang.appendChild(jumlahItemAktif);
                kartuLadang.appendChild(itemAKtif);

                for(int j=0; j<x.itemAktif.size(); j++){
                    Element item = document.createElement("ITEM");
                    item.appendChild(document.createTextNode(x.itemAktif.get(j)));

                    itemAKtif.appendChild(item);
                }

                if(x.itemAktif.size() == 0){
                    itemAKtif.appendChild(document.createTextNode("\n"));
                }


                elmtLadang.appendChild(kartuLadang);
            }

            if(this.listKartuLadang[1].size() == 0){
                elmtLadang.appendChild(document.createTextNode("\n"));
            }

            root.appendChild(elmtGulden);
            root.appendChild(elmtJumlahDeck);
            root.appendChild(elmtDeck);
            root.appendChild(jumlahKartuLadang);
            root.appendChild(elmtLadang);

            // Write to XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);

            // Specify your local file path
            StreamResult result = new StreamResult(player2);
            transformer.transform(source, result);
        } catch (Exception e){
            e.printStackTrace();
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

            NodeList nList = root.getElementsByTagName("ITEM");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String itemName = eElement.getElementsByTagName("ITEM_NAME").item(0).getTextContent();
                    int itemQty = Integer.parseInt(eElement.getElementsByTagName("ITEM_QTY").item(0).getTextContent());

                    InfoItemShop item = new InfoItemShop(itemName, itemQty);
                    this.listItemShop.add(item);
                }
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
            NodeList deckNodes = root.getElementsByTagName("DECK").item(0).getChildNodes();
            for(int i=0; i<deckNodes.getLength(); i++){
                Node deckNode = deckNodes.item(i);
                if (deckNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element deckElement = (Element) deckNode;
                    String lokasi = deckElement.getElementsByTagName("LOKASI").item(0).getTextContent();
                    String nama = deckElement.getElementsByTagName("NAMA").item(0).getTextContent();
                    this.listKartuDeck[0].add(new InfoKartuAktif(lokasi, nama));
                }
            }

            // set ladang
            int n = Integer.parseInt(root.getElementsByTagName("JUMLAH_KARTU_LADANG").item(0).getTextContent());
            NodeList ladangNodes = root.getElementsByTagName("LADANG").item(0).getChildNodes();
            for(int i=0; i<ladangNodes.getLength(); i++){
                Node ladangNode = ladangNodes.item(i);
                if (ladangNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element ladangElement = (Element) ladangNode;
                    String lokasi = ladangElement.getElementsByTagName("LOKASI").item(0).getTextContent();
                    String nama = ladangElement.getElementsByTagName("NAMA").item(0).getTextContent();
                    int umurBerat = Integer.parseInt(ladangElement.getElementsByTagName("UMUR_BERAT").item(0).getTextContent());
                    int jumlahItemAktif = Integer.parseInt(ladangElement.getElementsByTagName("JUMLAH_ITEM_AKTIF").item(0).getTextContent());

                    List<String> itemAktif = new ArrayList<>();
                    int counter = 0;
                    NodeList itemAktifNodes = ladangElement.getElementsByTagName("ITEM_AKTIF").item(0).getChildNodes();
                    for (int j = 0; j < itemAktifNodes.getLength(); j++) {
                        Node itemAktifNode = itemAktifNodes.item(j);
                        if (itemAktifNode.getNodeType() == Node.ELEMENT_NODE) {
                            itemAktif.add(itemAktifNode.getTextContent());
                        }
                    }

                    InfoKartuLadang p = new InfoKartuLadang(lokasi, nama, umurBerat, jumlahItemAktif, itemAktif);

                    this.listKartuLadang[0].add(p);
                }
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
            NodeList deckNodes = root.getElementsByTagName("DECK").item(0).getChildNodes();
            for(int i=0; i<deckNodes.getLength(); i++){
                Node deckNode = deckNodes.item(i);
                if (deckNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element deckElement = (Element) deckNode;
                    String lokasi = deckElement.getElementsByTagName("LOKASI").item(0).getTextContent();
                    String nama = deckElement.getElementsByTagName("NAMA").item(0).getTextContent();
                    this.listKartuDeck[1].add(new InfoKartuAktif(lokasi, nama));
                }
            }

            // set ladang
            int n = Integer.parseInt(root.getElementsByTagName("JUMLAH_KARTU_LADANG").item(0).getTextContent());
            NodeList ladangNodes = root.getElementsByTagName("LADANG").item(0).getChildNodes();
            for(int i=0; i<ladangNodes.getLength(); i++){
                Node ladangNode = ladangNodes.item(i);
                if (ladangNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element ladangElement = (Element) ladangNode;
                    String lokasi = ladangElement.getElementsByTagName("LOKASI").item(0).getTextContent();
                    String nama = ladangElement.getElementsByTagName("NAMA").item(0).getTextContent();
                    int umurBerat = Integer.parseInt(ladangElement.getElementsByTagName("UMUR_BERAT").item(0).getTextContent());
                    int jumlahItemAktif = Integer.parseInt(ladangElement.getElementsByTagName("JUMLAH_ITEM_AKTIF").item(0).getTextContent());

                    List<String> itemAktif = new ArrayList<>();
                    NodeList itemAktifNodes = ladangElement.getElementsByTagName("ITEM_AKTIF").item(0).getChildNodes();
                    for (int j = 0; j < itemAktifNodes.getLength(); j++) {
                        Node itemAktifNode = itemAktifNodes.item(j);
                        if (itemAktifNode.getNodeType() == Node.ELEMENT_NODE) {
                            itemAktif.add(itemAktifNode.getTextContent());
                        }
                    }

                    InfoKartuLadang p = new InfoKartuLadang(lokasi, nama, umurBerat, jumlahItemAktif, itemAktif);

                    this.listKartuLadang[1].add(p);
                }
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
