package org.gui;

import javax.swing.*;
import java.awt.*;

public class App extends Default{
    JFrame frame = new JFrame();

    private void initialize() {
        frame.setTitle("HARVEST SUN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); 
        frame.setResizable(false);

        JPanel main = main_panel();

        JPanel empty1 = new JPanel();
        empty1.setPreferredSize(new Dimension(1100, 20));
        empty1.setBackground(Color.decode(getColor1()));
        JPanel empty3 = new JPanel();
        empty3.setPreferredSize(new Dimension(1100, 20));
        empty3.setBackground(Color.decode(getColor1()));
        JPanel empty2 = new JPanel();
        empty2.setPreferredSize(new Dimension(20, 660));
        empty2.setBackground(Color.decode(getColor1()));
        JPanel empty4 = new JPanel();
        empty4.setPreferredSize(new Dimension(20, 660));
        empty4.setBackground(Color.decode(getColor1()));

        frame.add(empty1, BorderLayout.NORTH);
        frame.add(empty3, BorderLayout.SOUTH);
        frame.add(main, BorderLayout.CENTER);
        frame.add(empty2, BorderLayout.EAST);
        frame.add(empty4, BorderLayout.WEST);

        frame.setVisible(true);

        frame.pack();
    }

    private JPanel main_panel() {
        JPanel panel = new JPanel(); 
        panel.setBackground(Color.decode(getColor1()));
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1060, 660));
        // Farm farm = new Farm();
        // panel.add(farm.page_farm());
        // Shop shop = new Shop();
        // panel.add(shop.page_shop());
        // Save save = new Save();
        // panel.add(save.page_save());
        Load load = new Load();
        panel.add(load.page_load());
        return panel;
    }
    
    public static void main(String[] args) {
        App p = new App();
        p.initialize();
    }
}
