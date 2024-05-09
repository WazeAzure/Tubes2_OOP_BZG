import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class CardTransferable implements Transferable {
    private final CardPanel card;
    public static final DataFlavor cardFlavor =
            new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + ";class=com.zetcode.CardPanel", "Card");

    // Array of supported flavors (only cardFlavor in this case)
    protected static final DataFlavor[] supportedFlavors = { cardFlavor };

    public CardTransferable(CardPanel card) {
        this.card = card;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return supportedFlavors;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(cardFlavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(cardFlavor)) {
            return cardFlavor;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
