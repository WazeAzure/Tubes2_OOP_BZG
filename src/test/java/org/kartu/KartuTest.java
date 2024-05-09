package org.kartu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KartuTest {

    @Test
    void getVal() {
        Kartu kartu = new Kartu();
        assertEquals(kartu.getVal(), 2, "card value must be 2");
    }
}