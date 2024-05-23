package org.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;

public class PetakLadangPlaceholder extends CardPlaceholder {
    private int idx_i;
    private int idx_j;
    private final JFrame rootFrame;

    public PetakLadangPlaceholder(int x, int y, int w, int h, int i, int j, JFrame rootFrame) {
        super(x,y,w,h);
        this.idx_i = i;
        this.idx_j = j;
        this.rootFrame = rootFrame;
    }

    public void displayIJ(){
        System.out.println(idx_i);
        System.out.println(idx_j);
    }

    public void setPanelCard(CardPanel panel){
        this.panelCard = panel;
        this.panelCard.setBounds(0,0,this.width,this.height);
        this.panelCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // This block will execute ONLY when the panel itself is clicked
                System.out.println("JPanel was clicked!");
                JDialog infoDialog = new JDialog(rootFrame, "Information Details", true);
                infoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                infoDialog.setSize(400,500);
                infoDialog.setLocationRelativeTo(rootFrame);

                // Tambahin component
                JPanel contentPanel = new JPanel();
                 contentPanel.setLayout(null);

                // Component gambar
                // Nanti ganti image
                int imageHeight = 130;
                JLabel image = new JLabel("Image");
                JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                imagePanel.add(image);
                imagePanel.setPreferredSize(new Dimension(400,30));
                imagePanel.setBackground(Color.CYAN);
                imagePanel.setBounds(0,10,400,imageHeight);
                contentPanel.add(imagePanel);

                // Component nama kartu
                int namaY = 15 + imageHeight;
                JLabel nama = new JLabel("Nama");
                Font namaFont = new Font("Arial", Font.PLAIN, 20);
                nama.setFont(namaFont);
                JPanel namaPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                namaPanel.add(nama);
                namaPanel.setBounds(0,namaY,400,30);
                contentPanel.add(namaPanel);

                // Component Berat / Umur (angka agar siap untuk panen)
                // Nanti tergantung kartu hewan/tumbuhan
                int beratUmurY = 30 + namaY;
                // Berat (hewan)
                JLabel berat = new JLabel("Berat : xx (zz)");
                berat.setBounds(20,beratUmurY,500,30);
                contentPanel.add(berat);
                // Umur (tumbuhan)
                JLabel umur = new JLabel("Umur : xx (zz)");
                umur.setBounds(20,beratUmurY,500,30);
                contentPanel.add(umur);

                // Component item aktif (jml)
                int itemAktifY = 30 + beratUmurY;
                JLabel itemAktif = new JLabel("Item aktif : ");
                itemAktif.setBounds(20,itemAktifY,500,30);
                contentPanel.add(itemAktif);

                // Masing" component aktif, buat kalau memang ada item aktifnya

                // Inisiasi contoh list
                // Disclaimer: CUMAN CONTOH BUAT NGEPRINT AJA,
                // FORMAT ASLINYA NANTI HARUS:
                // <nama_item> (jml_item_tsb)
                // jadi tiap total item harus ditotalin dulu
                List<String> contohList = new ArrayList<>();
                contohList.add("Accelerate (x)");
                contohList.add("Delay (x)");
                contohList.add("Instant Harvest (x)");
                contohList.add("Destroy (x)");
                contohList.add("Protect (x)");
                contohList.add("Trap (x)");

                // Loop tiap jenis item yang ada di list
                // Jadi kl pke map buat ngemap nama_item : jml_item
                int eachItemY = 30 + itemAktifY;
                for(String tesDoang : contohList){
                    JLabel eachItem = new JLabel(tesDoang);
                    eachItem.setBounds(40,eachItemY,500,30);
                    contentPanel.add(eachItem);
                    eachItemY += 30;
                }

                // Component button Panen
                int buttonY = 20 + 6*30 + itemAktifY;
                JButton panenButton = new JButton("Panen");
                panenButton.setFocusable(false);
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                buttonPanel.add(panenButton);
                buttonPanel.setBounds(0,buttonY,400,30);
                contentPanel.add(buttonPanel);
                contentPanel.add(buttonPanel);

                infoDialog.add(contentPanel);
                infoDialog.setVisible(true);
            }
        });
        this.panelCard.render();
        this.add(this.panelCard);
        this.revalidate();
        this.repaint();
    }
}
