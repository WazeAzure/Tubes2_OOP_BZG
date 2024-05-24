package org.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.dnd.*;
import java.awt.image.BufferedImage;
import java.io.File;

import org.toko.*;

public class Farm extends JPanel implements DragGestureListener {
    private CardPlaceholder sourceDragPanel;
    private App app;
    public static Integer currentLadang;

    private class ImageLabel extends JLabel {
        private Image backgroundImage;

        public ImageLabel(String text, Image backgroundImage) {
            super(text);
            this.backgroundImage = backgroundImage;
            setFont(new Font("Serif", Font.BOLD, 30));
            setHorizontalTextPosition(CENTER);
            setVerticalTextPosition(CENTER);
            setForeground(Color.BLACK); // Set text color
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

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
        JPanel imgPanel = new JPanel();
        imgPanel.setLayout(null);

        try {
            BufferedImage img = ImageIO.read(new File("src/main/java/org/gui/assets/p1.png"));
            Image resizedImage = img.getScaledInstance(150, 355, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImage);

            JLabel imgLabel = new JLabel(icon);
            imgLabel.setBounds(0, 0, 150, 355);
            imgPanel.add(imgLabel);
            imgPanel.setPreferredSize(new Dimension(150, 255));
        } catch (Exception e) {
            e.printStackTrace();
        }

        imgPanel.setBounds(565, 0, 150, 355); 
        this.add(imgPanel);

        // label nama dari currentplayer
        JLabel namaCurrPlayer = new JLabel("Player " + App.gameEngine.getCurrentPemain().getPlayerNumber());
        namaCurrPlayer.setFont(new Font("Serif", Font.BOLD, 20));
        namaCurrPlayer.setBackground(Color.decode("#F1E4C3"));
        JPanel namaCurrPlayerPanel = new JPanel();
        namaCurrPlayerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        namaCurrPlayerPanel.setBackground(Color.decode("#F1E4C3"));
        namaCurrPlayerPanel.add(namaCurrPlayer);
        namaCurrPlayerPanel.setBounds(562, 355, 150, 30);
        this.add(namaCurrPlayerPanel);

        // Button next
        JButton nextButton = new JButton("Next");
        nextButton.setFocusable(false);
        nextButton.setEnabled(!BearAttack.isBearAtack);
        nextButton.setPreferredSize(new Dimension(130, 40));
        nextButton.addActionListener(e -> {
            // logic ganti player terus render player selanjutnya
            App.gameEngine.nextTurn();
            this.render();
            ShuffleCardDialog shuffleCardDialog = new ShuffleCardDialog(app);
            shuffleCardDialog.render(app.gameEngine.getCurrentPemain().getActiveDeck().remainingSlot());
        });
        JPanel nextButtonPanel = new JPanel();
        nextButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        nextButtonPanel.add(nextButton);
        nextButtonPanel.setBounds(565, 405, 150, 50);
        this.add(nextButtonPanel);

        // info deck

        BufferedImage imgb = null;
        try {
            imgb = ImageIO.read(new File("src/main/java/org/gui/assets/totalcard.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel deck = new ImageLabel(App.gameEngine.getCurrentPemain().getShuffleDeck().getRemainingCard() + "/40", imgb);
        deck.setPreferredSize(new Dimension(150, 150)); 

        deck.setFont(new Font("Serif", Font.BOLD, 30));
        JPanel flowPanel = new JPanel();
        flowPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        flowPanel.setBounds(0, 30, 150, 150);
        flowPanel.add(deck);
        flowPanel.setBackground(Color.decode("#F1E4C3"));
        JPanel deckPanel = new JPanel();
        deckPanel.setLayout(null);
        deckPanel.add(flowPanel);
        deckPanel.setBounds(565, 495, 150, 200);
        deckPanel.setBackground(Color.decode("#F1E4C3"));
        this.add(deckPanel);

        // KOLOM KANAN
        // label jumlah turn
        JLabel turnLabel = new JLabel("Turn : " + App.gameEngine.getTurn());
        JPanel turnLabelPanel = new JPanel();
        turnLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        turnLabelPanel.add(turnLabel);
        turnLabelPanel.setBounds(760, 0, 300, 40);
        this.add(turnLabelPanel);

        // label gulden Player 1
        JLabel infoPlayer1 = new JLabel("Player 1 : " + app.gameEngine.getListPemain().get(0).getUang() + " Gulden");
        JPanel infoPlayer1Panel = new JPanel();
        infoPlayer1Panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        infoPlayer1Panel.add(infoPlayer1);
        infoPlayer1Panel.setBounds(760, 60, 300, 40);
        this.add(infoPlayer1Panel);

        // label gulden Player 2
        JLabel infoPlayer2 = new JLabel("Player 2 : " + app.gameEngine.getListPemain().get(1).getUang() + " Gulden");
        JPanel infoPlayer2Panel = new JPanel();
        infoPlayer2Panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        infoPlayer2Panel.add(infoPlayer2);
        infoPlayer2Panel.setBounds(760, 110, 300, 40);
        this.add(infoPlayer2Panel);

        // Button ladang lawan

        JButton buttonLadangLawan = new JButton();
        buttonLadangLawan.setEnabled(!BearAttack.isBearAtack);
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
        saveState.setEnabled(!BearAttack.isBearAtack);
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
        loadState.setEnabled(!BearAttack.isBearAtack);
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
        loadPlugin.setEnabled(!BearAttack.isBearAtack);
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
        JButton buttonToko = new JButton();
        buttonToko.setEnabled(!BearAttack.isBearAtack);
        try {
            BufferedImage img = ImageIO.read(new File("src/main/java/org/gui/assets/shops.png"));
            Image resizedImage = img.getScaledInstance(220, 220, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImage);

            buttonToko.setIcon(icon);
            buttonToko.setPreferredSize(new Dimension(220, 220)); 
        } catch (Exception e) {
            e.printStackTrace();
        }

        buttonToko.setFocusable(false);
        buttonToko.setPreferredSize(new Dimension(220, 220));
        buttonToko.setOpaque(false);
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
        buttonTokoPanel.setOpaque(false);
        buttonTokoPanel.add(buttonToko);

        this.add(buttonTokoPanel);

//        JPanel transparentPanel = new JPanel();
//        transparentPanel.setOpaque(false);
//        TitledBorder border = new TitledBorder(BorderFactory.createLineBorder(Color.RED, 7),"TES");
//        transparentPanel.setBounds(0,0,300,300);
//        transparentPanel.setBorder(border);
//        this.add(transparentPanel);
//        this.setComponentZOrder(transparentPanel,0);

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
