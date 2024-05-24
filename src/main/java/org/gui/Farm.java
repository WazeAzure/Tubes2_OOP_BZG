package org.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.dnd.*;
import java.awt.image.BufferedImage;
import java.io.File;

import org.toko.*;

public class Farm extends JPanel implements DragGestureListener {
    private CardPlaceholder sourceDragPanel;
    private App app;
    public static Integer currentLadang;

    public Farm(App app) {
        currentLadang = 0;
        this.app = app;
        this.setLayout(null);
        this.setBounds(0, 0, 1060, 700);
        
        
        this.setBackground(Color.decode("#F1E4C3"));
        this.render();
    }

    public void render() {
        this.removeAll();
        // KOLOM KIRI
        LadangPanel ladangPanel = new LadangPanel(this, app.frame);
        this.add(ladangPanel);

        DeckAktif deckAktif = new DeckAktif(this);
        this.add(deckAktif);

        // KOLOM TENGAH
        // (optional) gambar character (520+45,0,150,355)

        // label nama dari currentplayer
        JLabel namaCurrPlayer = new JLabel("Player 1");
        namaCurrPlayer.setFont(new Font("Serif", Font.BOLD, 20));
        JPanel namaCurrPlayerPanel = new JPanel();
        namaCurrPlayerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        namaCurrPlayerPanel.add(namaCurrPlayer);
        namaCurrPlayerPanel.setBounds(562, 355, 150, 30);
        this.add(namaCurrPlayerPanel);

        // Button next
        JButton nextButton = new JButton("Next");
        nextButton.setFocusable(false);
        nextButton.setPreferredSize(new Dimension(130, 40));
        nextButton.addActionListener(e -> {
            // logic ganti player terus render player selanjutnya
        });
        JPanel nextButtonPanel = new JPanel();
        nextButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        nextButtonPanel.add(nextButton);
        nextButtonPanel.setBounds(565, 405, 150, 50);
        this.add(nextButtonPanel);

        // info deck
        JLabel deck = new JLabel("xx/40");
        deck.setFont(new Font("Serif", Font.BOLD, 30));
        JPanel flowPanel = new JPanel();
        flowPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        flowPanel.setBounds(0, 70, 150, 40);
        flowPanel.add(deck);
        JPanel deckPanel = new JPanel();
        deckPanel.setLayout(null);
        deckPanel.add(flowPanel);
        deckPanel.setBounds(565, 495, 150, 200);
        this.add(deckPanel);

        // KOLOM KANAN
        // label jumlah turn
        JLabel turnLabel = new JLabel("Turn : XX");
        JPanel turnLabelPanel = new JPanel();
        turnLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        turnLabelPanel.add(turnLabel);
        turnLabelPanel.setBounds(760, 0, 300, 40);
        this.add(turnLabelPanel);

        // label gulden Player 1
        JLabel infoPlayer1 = new JLabel("Player 1 : xx Gulden");
        JPanel infoPlayer1Panel = new JPanel();
        infoPlayer1Panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        infoPlayer1Panel.add(infoPlayer1);
        infoPlayer1Panel.setBounds(760, 60, 300, 40);
        this.add(infoPlayer1Panel);

        // label gulden Player 2
        JLabel infoPlayer2 = new JLabel("Player 2 : xx Gulden");
        JPanel infoPlayer2Panel = new JPanel();
        infoPlayer2Panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        infoPlayer2Panel.add(infoPlayer2);
        infoPlayer2Panel.setBounds(760, 110, 300, 40);
        this.add(infoPlayer2Panel);

        // Button ladang lawan

        JButton buttonLadangLawan = new JButton();
        try {
            BufferedImage img = ImageIO.read(new File("src/main/java/org/gui/assets/ladanglawan.png"));
            Image resizedImage = img.getScaledInstance(240, 40, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImage);

            buttonLadangLawan.setIcon(icon);
            buttonLadangLawan.setPreferredSize(new Dimension(240, 40)); 
        } catch (Exception e) {
            e.printStackTrace();
        }

        buttonLadangLawan.setFocusable(false);
        buttonLadangLawan.setPreferredSize(new Dimension(240, 40));
        buttonLadangLawan.addActionListener(e -> {
            // logic to render ladang lawan
            currentLadang = 1;
            this.render();
        });

        JPanel buttonLadangLawanPanel = new JPanel();
        buttonLadangLawanPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonLadangLawanPanel.setBounds(760, 170, 300, 50); 
        buttonLadangLawanPanel.setBackground(Color.decode("#F1E4C3"));
        buttonLadangLawanPanel.add(buttonLadangLawan);

        this.add(buttonLadangLawanPanel);
        // Button save state

        JButton saveState = new JButton();
        try {
            BufferedImage img = ImageIO.read(new File("src/main/java/org/gui/assets/savestate.png"));
            Image resizedImage = img.getScaledInstance(240, 40, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImage);

            saveState.setIcon(icon);
            saveState.setPreferredSize(new Dimension(240, 40)); 
        } catch (Exception e) {
            e.printStackTrace();
        }

        saveState.setFocusable(true);
        saveState.setPreferredSize(new Dimension(240, 40));
        saveState.addActionListener(e -> {
            // logic ngesave state, render page save
            Save save = new Save(app);
            app.main_panel.removeAll();
            app.main_panel.add(save.page_save());
            app.main_panel.revalidate();
            app.main_panel.repaint();
        });
        JPanel saveStatePanel = new JPanel();
        saveStatePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        saveStatePanel.setBounds(760, 230, 300, 50);
        saveStatePanel.add(saveState);
        saveStatePanel.setBackground(Color.decode("#F1E4C3"));
        this.add(saveStatePanel);

        // Button load state
        JButton loadState = new JButton();
        try {
            BufferedImage img = ImageIO.read(new File("src/main/java/org/gui/assets/loadstate.png"));
            Image resizedImage = img.getScaledInstance(240, 40, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImage);

            loadState.setIcon(icon);
            loadState.setPreferredSize(new Dimension(240, 40)); 
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadState.setFocusable(false);
        loadState.setPreferredSize(new Dimension(240, 40));
        loadState.addActionListener(e -> {
            // logic ngerender load state
            Load load = new Load(app);
            app.main_panel.removeAll();
            app.main_panel.add(load.page_load());
            app.main_panel.revalidate();
            app.main_panel.repaint();
        });
        JPanel loadStatePanel = new JPanel();
        loadStatePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        loadStatePanel.setBounds(760, 290, 300, 50);
        loadStatePanel.setBackground(Color.decode("#F1E4C3"));

        loadStatePanel.add(loadState);
        this.add(loadStatePanel);

        // Button load plugin

        JButton loadPlugin = new JButton();
        try {
            BufferedImage img = ImageIO.read(new File("src/main/java/org/gui/assets/loadplugin.png"));
            Image resizedImage = img.getScaledInstance(240, 40, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImage);

            loadPlugin.setIcon(icon);
            loadPlugin.setPreferredSize(new Dimension(240, 40)); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadPlugin.setFocusable(false);
        loadPlugin.setPreferredSize(new Dimension(240, 40));
        loadPlugin.addActionListener(e -> {
            // logic ngerender load plugin
            Plugin plugin = new Plugin(app);
            app.main_panel.removeAll();
            app.main_panel.add(plugin.page_plugin());
            app.main_panel.revalidate();
            app.main_panel.repaint();
        });
        JPanel loadPluginPanel = new JPanel();
        loadPluginPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        loadPluginPanel.setBounds(760, 350, 300, 50);
        loadPluginPanel.add(loadPlugin);
        loadPluginPanel.setBackground(Color.decode("#F1E4C3"));
        this.add(loadPluginPanel);

        // toko
        JButton buttonToko = new JButton("Toko");
        buttonToko.setFocusable(false);
        buttonToko.setPreferredSize(new Dimension(290, 295));
        buttonToko.addActionListener(e -> {
            // logic ngerender toko
            Toko toko = new Toko();
            Shop shop = new Shop(app, toko);
            app.main_panel.removeAll();
            app.main_panel.add(shop.page_shop());
            app.main_panel.revalidate();
            app.main_panel.repaint();
        });
        JPanel buttonTokoPanel = new JPanel();
        buttonTokoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonTokoPanel.setBounds(760, 410, 300, 305);
        buttonTokoPanel.add(buttonToko);
        this.add(buttonTokoPanel);

        this.revalidate();
        this.repaint();
    }

    public void dragGestureRecognized(DragGestureEvent event) {
        this.sourceDragPanel = null;
        var cursor = Cursor.getDefaultCursor();
        var panel = (CardPanel) event.getComponent();

        if (event.getDragAction() == DnDConstants.ACTION_MOVE) {
            cursor = DragSource.DefaultMoveDrop;
        }
        this.sourceDragPanel = (CardPlaceholder) panel.getParent();
        try {
            event.startDrag(cursor, panel);
        } catch (Exception e) {
            // Do nothing
        }
    }

    public CardPlaceholder getSourceDragPanel() {
        return this.sourceDragPanel;
    }

    public void setNullSourceDragPanel() {
        this.sourceDragPanel = null;
    }
}
