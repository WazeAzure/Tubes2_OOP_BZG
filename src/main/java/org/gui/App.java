package org.gui;

import javax.swing.*;
import java.awt.*;

public class App extends Default{
    JFrame frame = new JFrame();

    private void initialize() {
        frame.setTitle("Shop GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); 
        frame.setResizable(false);
        frame.setBackground(Color.decode("#F1E4C3"));

        JPanel main = main_panel();

        JPanel empty1 = new JPanel();
        empty1.setPreferredSize(new Dimension(1100, 20));
        JPanel empty3 = new JPanel();
        empty3.setPreferredSize(new Dimension(1100, 20));
        JPanel empty2 = new JPanel();
        empty2.setPreferredSize(new Dimension(20, 660));
        JPanel empty4 = new JPanel();
        empty4.setPreferredSize(new Dimension(20, 660));

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
        Shop shop = new Shop();
        panel.add(shop.page_shop());
        return panel;
    }
    
    public static void main(String[] args) {
        App p = new App();
        p.initialize();
    }
}
