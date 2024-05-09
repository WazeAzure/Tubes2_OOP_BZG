import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class Kartu{
    private String nama;
    private String urlGambar;
    private Integer nilaiGulden;

    public Kartu() {
        this.nama = "Anjay Nama";
        this.urlGambar = "Anjay Gambar";
        this.nilaiGulden = 100;
    }

    public String getNama(){
        return this.nama;
    }

    public String getUrlGambar(){
        return this.urlGambar;
    }

    public Integer getNilaiGulden(){
        return this.nilaiGulden;
    };
}
