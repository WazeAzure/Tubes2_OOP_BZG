package org.ladang;

import org.config.Config;
import org.junit.jupiter.api.Test;
import org.kartu.Kartu;
import org.kartu.harvestable.Harvestable;
import org.kartu.harvestable.tumbuhan.Tumbuhan;
import org.kartu.product.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LadangTest {

    @Test
    void ladangConstructor() {
        Ladang l = new Ladang(4, 5);
        List<String> list = new ArrayList<String>(l.getLadang().keySet());
        Collections.sort(list);
        System.out.println(list);

    }
    @Test
    void addRow() {
        Ladang l = new Ladang(4, 5);
        l.addRow();
        l.addRow();
        l.addRow();
        l.addRow();
        l.addRow();
        l.addRow();
        l.addRow();
        l.addRow();
        l.addRow();
        l.addRow();
        List<String> list = new ArrayList<String>(l.getLadang().keySet());
        Collections.sort(list);
        System.out.println(list);
    }

    @Test
    void addCol() {
        Ladang l = new Ladang(4, 5);
        l.addCol();
        List<String> list = new ArrayList<String>(l.getLadang().keySet());
        Collections.sort(list);
        System.out.println(list);
    }

    @Test
    void removeRow() {
        Config c = new Config();
        c.loadConfig();
        Ladang l = new Ladang(4, 5);
        try{
            l.placeCard(Config.buildTumbuhan("BIJI_JAGUNG"),"A05");
            l.placeCard(Config.buildTumbuhan("BIJI_JAGUNG"),"B05");
        }catch(Exception e){
            e.printStackTrace();
        }
        List<Harvestable> l2 = l.removeRow();
        List<String> list = new ArrayList<String>(l.getLadang().keySet());
        Collections.sort(list);
        System.out.println(list);
        System.out.println(l2.get(0).getNama());
        System.out.println(l2.get(1).getNama());
        assert l2.get(0).getNama().equals("BIJI_JAGUNG");
        assert l2.get(1).getNama().equals("BIJI_JAGUNG");

    }

    @Test
    void removeCol() {
        Config c = new Config();
        c.loadConfig();
        Ladang l = new Ladang(4, 5);
        try{
            l.placeCard(Config.buildTumbuhan("BIJI_JAGUNG"),"D05");
            l.placeCard(Config.buildTumbuhan("BIJI_JAGUNG"),"D04");
        }catch(Exception e){
            e.printStackTrace();
        }
        List<Harvestable> l2 = l.removeCol();
        List<String> list = new ArrayList<String>(l.getLadang().keySet());
        Collections.sort(list);
        System.out.println(list);
        System.out.println(l2.get(0).getNama());
        System.out.println(l2.get(1).getNama());
        assert l2.get(0).getNama().equals("BIJI_JAGUNG");
        assert l2.get(1).getNama().equals("BIJI_JAGUNG");
    }

    @Test
    void makeBigger() {
        Ladang l = new Ladang(4, 5);
        l.makeBigger();
        System.out.println(l.getLadang().keySet());
        assert  l.getLadang().keySet().size() == 30;
    }

    @Test
    void makeSmaller() {
        Ladang l = new Ladang(4, 5);
        l.makeSmaller();
        System.out.println(l.getLadang().keySet());
        assert l.getLadang().keySet().size() == 12;
    }

    @Test
    void getObject() {
        Config c = new Config();
        c.loadConfig();
        Ladang l = new Ladang(4, 5);
        try{
            l.placeCard(Config.buildTumbuhan("BIJI_JAGUNG"),"A1");
        }catch(Exception e){

        }
        System.out.println("masuk");
        System.out.println(l.getObject("A1").getNama());
        assert l.getObject("A1").getNama().equals("BIJI_JAGUNG");
    }

    @Test
    void placeCard() throws Exception {
        Ladang l = new Ladang(4, 5);
        Config c = new Config();
        c.loadConfig();
        l.placeCard(new Tumbuhan("BIJI_JAGUNG", "Tumbuhan", null, 4), "A01");
        assert l.getObject("A01").getNama().equals("BIJI_JAGUNG");
    }

    @Test
    void removeObject() throws Exception {
        Ladang l = new Ladang(4, 5);
        Config c = new Config();
        c.loadConfig();
        l.placeCard(new Tumbuhan("BIJI_JAGUNG", "Tumbuhan", null, 4),"A01");
        assert l.getObject("A01").getNama().equals("BIJI_JAGUNG");
        l.removeObject("A01");
        assert l.getObject("A01") == null;
    }

    @Test
    void growAllPlant() throws Exception {
        Ladang l = new Ladang(4, 5);
        Config c = new Config();
        c.loadConfig();
        l.placeCard(new Tumbuhan("BIJI_JAGUNG", "Tumbuhan", null, 4),"A01");
        assert l.getObject("A01").getNama().equals("BIJI_JAGUNG");
        l.placeCard(new Tumbuhan("BIJI_JAGUNG", "Tumbuhan", null, 4),"A02");
        assert l.getObject("A02").getNama().equals("BIJI_JAGUNG");
        l.growAllPlant();
        assert l.getObject("A01").getValue() == 1;
        assert l.getObject("A02").getValue() == 1;

    }

    @Test
    void getInfo() throws Exception {
        Ladang l = new Ladang(5, 4);
        Config c = new Config();
        c.loadConfig();
        l.placeCard(new Tumbuhan("BIJI_JAGUNG", "Tumbuhan", null, 4),"A01");
        assert l.getInfo("A01").get(0).equals("BIJI_JAGUNG");
        assert l.getInfo("A01").get(1).equals("0");
        assert l.getInfo("A01").get(2).equals("0");

    }

    @Test
    void destroyRegion() throws InterruptedException {
        System.out.println("Destroy");
        Ladang l = new Ladang(5, 4);
        while (true) {
            System.out.println("Test");
            for(String s: l.destroyRegion()){
                System.out.println(s);
            }
            Thread.sleep(1000);

        }
    }
}