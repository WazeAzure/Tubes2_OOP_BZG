package org.kartu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KartuTest {

    @Test
    void getVal() {
        Kartu kartu = new Kartu("TestName", "TestKategori", "TestImage");
        assertEquals(kartu.getNama(), "TestName", "nama harus TestName");
        assertEquals(kartu.getKategori(), "TestKategori", "kategori harus TestKategori");
        assertEquals(kartu.getImageURL(), "TestImage", "nama harus TestImage");
    }
}