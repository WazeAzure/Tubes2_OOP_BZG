package org.ladang;

import org.config.Config;
import org.junit.jupiter.api.Test;
import org.kartu.harvestable.Harvestable;

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
        Ladang l = new Ladang(4, 5);
        List<Harvestable> l2 = l.removeRow();
        List<String> list = new ArrayList<String>(l.getLadang().keySet());
        Collections.sort(list);
        System.out.println(list);
        System.out.println(l2);
    }

    @Test
    void removeCol() {
        Ladang l = new Ladang(4, 5);
        List<Harvestable> l2 = l.removeCol();
        System.out.println(l.getLadang().entrySet());
        List<String> list = new ArrayList<String>(l.getLadang().keySet());
        Collections.sort(list);
        System.out.println(list);
        System.out.println(l2);
    }

    @Test
    void makeBigger() {
        Ladang l = new Ladang(4, 5);
        l.makeBigger();
        System.out.println(l.getLadang().keySet());
    }

    @Test
    void makeSmaller() {
        Ladang l = new Ladang(4, 5);
        l.makeSmaller();
        System.out.println(l.getLadang().keySet());
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
    void validateCardPlacement() {
    }

    @Test
    void placeCard() {
    }

    @Test
    void panen() {
    }

    @Test
    void growAllPlant() {
    }

    @Test
    void getInfo() {
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