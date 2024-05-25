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
        JButton nextButton = new JButton();
        nextButton.setBackground(Color.decode(app.getColor1()));
        try {
            BufferedImage img = ImageIO.read(new File("src/main/java/org/gui/assets/next.png"));
            Image resizedImage = img.getScaledInstance(120, 40, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImage);

            nextButton.setIcon(icon);
            nextButton.setPreferredSize(new Dimension(120, 40)); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        nextButton.setFocusable(false);
        nextButton.setEnabled(!(App.gameEngine.getGameState() == 1));
        nextButton.setPreferredSize(new Dimension(130, 40));
        nextButton.addActionListener(e -> {
            // logic ganti player terus render player selanjutnya
            if(app.gameEngine.getTurn() == 20){
                DialogMenang dialogMenang = new DialogMenang(app);
                dialogMenang.render();
            }
            else{
                App.gameEngine.nextTurn();
                this.render();
                ShuffleCardDialog shuffleCardDialog = new ShuffleCardDialog(app);
                shuffleCardDialog.render(app.gameEngine.getCurrentPemain().getActiveDeck().remainingSlot());
            }
        });
        JPanel nextButtonPanel = new JPanel();
        nextButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        nextButtonPanel.add(nextButton);
        nextButtonPanel.setBackground((Color.decode("#F1E4C3")));
        nextButtonPanel.setBounds(565, 405, 150, 50);
        this.add(nextButtonPanel);

        
        // info deck

        JLabel deck = new JLabel(App.gameEngine.getCurrentPemain().getShuffleDeck().getRemainingCard() + "/40");
        deck.setBounds(0,0,40, 20);
        // deck.setPreferredSize(new Dimension(150, 150)); 
        deck.setBackground(Color.decode("#614124"));

        deck.setFont(new Font("Serif", Font.BOLD, 30));
        JPanel flowPanel = new JPanel();
        flowPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        flowPanel.setBounds(0, 30, 150, 150);
        flowPanel.add(deck);
        flowPanel.setBackground(Color.decode("#C6A969"));
        JPanel deckPanel = new JPanel();
        deckPanel.setLayout(null);
        deckPanel.add(flowPanel);
        deckPanel.setBounds(565, 495+80, 150, 100);
        deckPanel.setBackground(Color.decode("#C6A969"));
        this.add(deckPanel);

        // KOLOM KANAN
        // label jumlah turn
        JLabel turnLabel = new JLabel("Turn : " + App.gameEngine.getTurn());
        turnLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JPanel turnLabelPanel = new JPanel();
        turnLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        turnLabelPanel.add(turnLabel);
        turnLabelPanel.setBounds(790, 20, 240, 40);
        turnLabelPanel.setBackground(Color.decode("#C6A969"));
        this.add(turnLabelPanel);

        // Info player 1 + gulden 

        ImageIcon originalIcon = new ImageIcon("src/main/java/org/gui/assets/gulden.png");

        Image scaledImage = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); 
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        JLabel infoPlayer1 = new JLabel("Player 1 : " + app.gameEngine.getListPemain().get(0).getUang() + " ");
        infoPlayer1.setFont(new Font("Arial", Font.BOLD, 16));
        infoPlayer1.setIcon(resizedIcon);
        infoPlayer1.setHorizontalTextPosition(JLabel.LEFT); 
        infoPlayer1.setVerticalTextPosition(JLabel.CENTER);

        JPanel infoPlayer1Panel = new JPanel();
        infoPlayer1Panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        infoPlayer1Panel.add(infoPlayer1);
        infoPlayer1Panel.setBounds(790, 60 + 20, 240, 40);
        infoPlayer1Panel.setBackground(Color.decode("#F1E4C3"));

        this.add(infoPlayer1Panel);



        // label gulden Player 2

        ImageIcon originalIcon2 = new ImageIcon("src/main/java/org/gui/assets/gulden.png");

        Image scaledImage2 = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); 
        ImageIcon resizedIcon2 = new ImageIcon(scaledImage);

        JLabel infoPlayer2 = new JLabel("Player 2 : " + app.gameEngine.getListPemain().get(0).getUang() + " ");
        infoPlayer2.setFont(new Font("Arial", Font.BOLD, 16));
        infoPlayer2.setIcon(resizedIcon);
        infoPlayer2.setHorizontalTextPosition(JLabel.LEFT); 
        infoPlayer2.setVerticalTextPosition(JLabel.CENTER);
        JPanel infoPlayer2Panel = new JPanel();
        infoPlayer2Panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        infoPlayer2Panel.add(infoPlayer2);
        infoPlayer2Panel.setBounds(790, 110+20, 240, 40);
        infoPlayer2Panel.setBackground(Color.decode("#F1E4C3"));
        this.add(infoPlayer2Panel);

        // Button ladang lawan
        if (App.gameEngine.getGameState() != 3) {
            JButton buttonLadangLawan = new JButton();
            buttonLadangLawan.setEnabled(!(App.gameEngine.getGameState() == 1));
            try {
                BufferedImage img = ImageIO.read(new File("src/main/java/org/gui/assets/ladanglawan.png"));
                // Kalo balik: src/main/java/org/gui/assets/back_f.png
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
                App.gameEngine.setGameState(3);
                this.render();

                // add music
                String sound_track = "src/main/java/org/gui/assets/horse.wav";
                Music se = new Music();
                se.setFile(sound_track);
                se.play();
            });

            JPanel buttonLadangLawanPanel = new JPanel();
            buttonLadangLawanPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            buttonLadangLawanPanel.setBounds(760, 170+20, 300, 50);
            buttonLadangLawanPanel.setBackground(Color.decode("#F1E4C3"));
            buttonLadangLawanPanel.add(buttonLadangLawan);

            this.add(buttonLadangLawanPanel);
        } else { // Button Kembali
            JButton buttonLadangLawan = new JButton();
            buttonLadangLawan.setEnabled(!(App.gameEngine.getGameState() == 1));
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
                App.gameEngine.setGameState(2);
                this.render();

                // add music
                String sound_track = "src/main/java/org/gui/assets/horse.wav";
                Music se = new Music();
                se.setFile(sound_track);
                se.play();
            });

            JPanel buttonLadangLawanPanel = new JPanel();
            buttonLadangLawanPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            buttonLadangLawanPanel.setBounds(760, 170+20, 300, 50);
            buttonLadangLawanPanel.setBackground(Color.decode("#F1E4C3"));
            buttonLadangLawanPanel.add(buttonLadangLawan);

            this.add(buttonLadangLawanPanel);
        }

        // Button save state

        JButton saveState = new JButton();
        saveState.setEnabled(!(App.gameEngine.getGameState() == 1));
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
        saveStatePanel.setBounds(760, 230+20, 300, 50);
        saveStatePanel.add(saveState);
        saveStatePanel.setBackground(Color.decode("#F1E4C3"));
        this.add(saveStatePanel);

        // Button load state
        JButton loadState = new JButton();
        loadState.setEnabled(!(App.gameEngine.getGameState() == 1));
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
        loadStatePanel.setBounds(760, 290+20, 300, 50);
        loadStatePanel.setBackground(Color.decode("#F1E4C3"));

        loadStatePanel.add(loadState);
        this.add(loadStatePanel);

        // Button load plugin

        JButton loadPlugin = new JButton();
        loadPlugin.setEnabled(!(App.gameEngine.getGameState() == 1));
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
        loadPluginPanel.setBounds(760, 350+20, 300, 50);
        loadPluginPanel.add(loadPlugin);
        loadPluginPanel.setBackground(Color.decode("#F1E4C3"));
        this.add(loadPluginPanel);

        // toko
        JButton buttonToko = new JButton();
        buttonToko.setEnabled(!(App.gameEngine.getGameState() == 1));
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

            // add music
            String sound_track = "src/main/java/org/gui/assets/pshop.wav";
            Music se = new Music();
            se.setFile(sound_track);
            se.play();
        });
        JPanel buttonTokoPanel = new JPanel();
        buttonTokoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonTokoPanel.setBounds(760, 450, 300, 305);
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
