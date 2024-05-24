package org.gui;

import org.gameEngine.GameEngine;

import javax.swing.*;
import java.awt.*;

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

    public static void main(String[] args) {
        App p = new App();
        p.initialize();
    }
}
