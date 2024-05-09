//import javax.swing.*;
//import java.awt.*;
//import java.awt.datatransfer.DataFlavor;
//import java.awt.datatransfer.Transferable;
//import java.awt.datatransfer.UnsupportedFlavorException;
//import java.io.IOException;
//
//public class CardPanelLadang extends CardPanel implements Transferable {
//
//    public CardPanelLadang(Kartu kartu, Integer x, Integer y){
//        super(kartu);
//        this.ladangX = x;
//        this.ladangY = y;
//    }
//
//    protected static final DataFlavor card =
//            new DataFlavor(CardPanelLadang.class, "Card");
//
//    protected static final DataFlavor[] supportedFlavors = {
//            card
//    };
//    @Override
//    public DataFlavor[] getTransferDataFlavors() {
//        return supportedFlavors;
//    }
//
//    @Override
//    public boolean isDataFlavorSupported(DataFlavor flavor) {
//        return flavor.equals(card);
//    }
//
//    @Override
//    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
//        if (flavor.equals(card)) {
//            return card;
//        } else {
//            throw new UnsupportedFlavorException(flavor);
//        }
//    }
//}
