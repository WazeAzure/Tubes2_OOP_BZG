import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.Serializable;

public class CardPanel extends JPanel implements Transferable, Serializable {
    //    private Kartu kartu;
    private String nama;
    private String gambar;
    private static final long serialVersionUID = 1L;

    public CardPanel(String nama, String gambar){
//        this.kartu = kartu;
        this.nama = nama;
        this.gambar = gambar;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel labelGambar = new JLabel(this.gambar);
        this.add(labelGambar, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel labelNama = new JLabel(this.nama);
        this.add(labelNama, gbc);
        this.setMinimumSize(new Dimension(50,50));
        this.setBackground(Color.WHITE);
    }

    public String getNama(){
        return  this.nama;
    }

    public String getGambar(){
        return this.gambar;
    }

    protected static final DataFlavor card =
            new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType +
                    ";class=\""+CardPanel.class.getName() + "\"", "Card");

    protected static final DataFlavor[] supportedFlavors = {
            card
    };
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return supportedFlavors;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(card);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(card)) {
            return card;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}