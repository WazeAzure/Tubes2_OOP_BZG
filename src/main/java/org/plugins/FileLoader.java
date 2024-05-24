package org.plugins;

import java.util.List;

public interface FileLoader {
    void loadFile(String folderPath) throws Exception;

    String getSupportedExtension();

    boolean supports(String fileName);

    /* Method TOKO */
    int getCurrentTurn();

    int getNTokoItem();

    List<InfoItemShop> getItemAndQty();

    /* Method Player */
    int getCurrentPlayer();

    int getGulden(int pemain);

    int getJumlahDeck(int pemain);

    List<InfoKartuAktif> getKartuDeckAktif(int pemain);

    List<InfoKartuLadang> getListKartuLadang(int pemain);
}
//C:\Users\Asus Tuf Gaming\IdeaProjects\Tubes2_OOP_BZG\src\main\java\org\gui\Save.java