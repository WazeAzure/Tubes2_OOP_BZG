package org.ladang;

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
        System.out.println();
        System.out.println(l2);
    }

    @Test
    void makeBigger() {
    }

    @Test
    void makeSmaller() {
    }

    @Test
    void getObject() {
        Ladang l = new Ladang(4, 5);
        l.placeCard(null,"A1");
        l.getObject("A1");
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
}