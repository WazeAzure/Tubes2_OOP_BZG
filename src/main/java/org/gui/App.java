package org.gui;

import javax.swing.*;
import java.awt.*;

public class App extends Default {
    public static JFrame frame = new JFrame();
    public static JPanel main_panel = new JPanel();

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
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setLocation((screenWidth - frame.getWidth()) / 2, (screenHeight - frame.getHeight()) / 2 - 20);

        main_panel.setBackground(Color.decode(getColor1()));
        main_panel.setLayout(null);
        main_panel.setPreferredSize(new Dimension(1060, 700));

        Farm farm_player1 = new Farm(this);
        main_panel.add(farm_player1);

        JPanel empty1 = new JPanel();
        empty1.setPreferredSize(new Dimension(1100, 20));
        empty1.setBackground(Color.GREEN);
        JPanel empty3 = new JPanel();
        empty3.setPreferredSize(new Dimension(1100, 20));
        empty3.setBackground(Color.GREEN);
        JPanel empty2 = new JPanel();
        empty2.setPreferredSize(new Dimension(20, 660));
        empty2.setBackground(Color.decode(getColor1()));
        JPanel empty4 = new JPanel();
        empty2.setBackground(Color.GREEN);
        empty4.setPreferredSize(new Dimension(20, 660));
        empty4.setBackground(Color.GREEN);

        frame.add(empty1, BorderLayout.NORTH);
        frame.add(empty3, BorderLayout.SOUTH);
        frame.add(main_panel, BorderLayout.CENTER);
        frame.add(empty2, BorderLayout.EAST);
        frame.add(empty4, BorderLayout.WEST);

        String sound_track = "src\\main\\java\\org\\gui\\assets\\bs1.wav";
        Music se = new Music();
        se.setFile(sound_track);
        se.loop();

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        App p = new App();
        p.initialize();
    }
}
