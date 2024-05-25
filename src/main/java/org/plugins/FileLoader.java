package org.plugins;

import java.util.List;

public interface FileLoader {
    void loadFile(String folderPath) throws Exception;

    void saveFile(String folderPath) throws Exception;

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

    /*
    *  WRITING SECTION
    * */

    /* Method TOKO */
    void setCurrentTurn(int turn);

    void setNTokoItem(int n);

    void setItemAndQty(List<InfoItemShop> shop);

    /* Method Player */
    void setGulden(int pemain, int gulden);

    void setJumlahDeck(int pemain, int jumlahDeck);

    void setKartuDeckAktif(int pemain, List<InfoKartuAktif> kartuDeckAktif);

    void setListKartuLadang(int pemain, List<InfoKartuLadang> kartuLadang);
}
//C:\Users\Asus Tuf Gaming\IdeaProjects\Tubes2_OOP_BZG\src\main\java\org\gui\Save.java