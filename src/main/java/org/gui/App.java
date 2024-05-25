package org.gui;

import org.gameEngine.GameEngine;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Random;

public class App extends Default {
    public JFrame frame = new JFrame();
    public JPanel main_panel = new JPanel();
    public static Farm farm;
    public static GameEngine gameEngine = new GameEngine();

    private void initialize() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        frame.setTitle("HARVEST SUN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.pack();
        // frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        System.out.println(screenHeight);
        frame.setLocation((screenWidth - getWidth()) / 2, (screenHeight - getHeight()) / 2 - 20);

        main_panel.setBackground(Color.decode(getColor1()));
        main_panel.setLayout(null);
        main_panel.setPreferredSize(new Dimension(1060, 700));

        farm = new Farm(this);
        main_panel.add(farm);

        JPanel empty1 = new JPanel();
        empty1.setPreferredSize(new Dimension(1100, 20));
        empty1.setBackground(Color.decode(getColor3()));
        JPanel empty3 = new JPanel();
        empty3.setPreferredSize(new Dimension(1100, 20));
        empty3.setBackground(Color.decode(getColor3()));
        JPanel empty2 = new JPanel();
        empty2.setPreferredSize(new Dimension(20, 660));
        empty2.setBackground(Color.decode(getColor1()));
        JPanel empty4 = new JPanel();
        empty2.setBackground(Color.decode(getColor3()));
        empty4.setPreferredSize(new Dimension(20, 660));
        empty4.setBackground(Color.decode(getColor3()));

        frame.add(empty1, BorderLayout.NORTH);
        frame.add(empty3, BorderLayout.SOUTH);
        frame.add(main_panel, BorderLayout.CENTER);
        frame.add(empty2, BorderLayout.EAST);
        frame.add(empty4, BorderLayout.WEST);

        String sound_track = "src/main/java/org/gui/assets/bs1.wav";
        Music se = new Music();
        se.setFile(sound_track);
        se.setVolume(0.85f);
//        se.loop();

        frame.pack();
        frame.setVisible(true);
        ShuffleCardDialog shuffleCardDialog = new ShuffleCardDialog(this);
        shuffleCardDialog.render(this.gameEngine.getCurrentPemain().getActiveDeck().remainingSlot());
    }

    float timeLeft;
    TitledBorder border;
    JPanel bearPanel;
    public void bearAttackPanel() {
        JPanel transparentPanel = new JPanel();
        transparentPanel.setOpaque(false);
//        transparentPanel.setPreferredSize(new Dimension(200, 150));
        transparentPanel.setBounds(0,0,300,300);
        TitledBorder border = new TitledBorder(BorderFactory.createLineBorder(Color.RED, 2));
//        border.setTitleJustification(TitledBorder.CENTER);
//        border.setTitlePosition(TitledBorder.TOP);
        transparentPanel.setBorder(border);
        this.border = border;
        this.bearPanel=transparentPanel;

        farm.add(transparentPanel);
        farm.repaint();
        farm.revalidate();
//        addMouseListener(this);
//        addMouseMotionListener(this);

        // Bear attack thread
        new Thread(() -> {
            try {
                Thread.sleep(5000); // Simulate bear attack after 5 seconds
                Boolean bearAttack = true;
//                SwingUtilities.invokeLater(() -> triggerBearAttack()); // Update UI in EDT

                // Timer for countdown
                float timeLeft = 30;
                this.timeLeft=timeLeft;
                while (timeLeft > 0 && bearAttack) {
                    timeLeft -= 0.1;
                    SwingUtilities.invokeLater(() -> updateTimerDisplay());
                    Thread.sleep(100); // Update every 0.1 seconds
                }

                if (bearAttack) {
                    // Bear attack ended (handle game logic here)
                    SwingUtilities.invokeLater(() -> {
                        border.setTitle("Bear Defeated!");
                        border.setBorder(BorderFactory.createEmptyBorder()); // Remove red border
                    });
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // ... (rest of the code is the same as before, but with the following additions)

    private void updateTimerDisplay() {
        border.setTitle("Bear Attack! Time Left: " + String.format("%.1f", timeLeft));
        this.bearPanel.repaint();
    }

    public static void main(String[] args) {
        App p = new App();
        p.initialize();
    }
}
