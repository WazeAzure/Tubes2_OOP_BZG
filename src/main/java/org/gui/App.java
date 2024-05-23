package org.gui;

import javax.swing.*;
import java.awt.*;

public class App extends Default {
    protected int page = 6;
    JFrame frame;
    JPanel mainPanel;
    Farm farm1;
    Farm farm2;
    Shop shop;
    Save save;
    Load load;
    Plugin plugin;

    public App() {
        farm1 = new Farm(this);
        farm2 = new Farm(this);
        shop = new Shop(this);
        save = new Save(this);
        load = new Load(this);
        plugin = new Plugin(this);
    }

    private void initialize() {
        JFrame frame = new JFrame();

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
        frame.setLocation((screenWidth - getWidth())/2, (screenHeight - getHeight())/2 - 20);

        mainPanel = main_panel();
        frame.add(mainPanel, BorderLayout.CENTER);

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
        frame.add(empty2, BorderLayout.EAST);
        frame.add(empty4, BorderLayout.WEST);

        String sound_track = "src\\main\\java\\org\\gui\\assets\\bs1.wav";
        Music se = new Music();
        se.setFile(sound_track);
        se.loop();

        frame.pack();
        frame.setVisible(true);
    }

    private JPanel main_panel() {
        JPanel panel = new JPanel(new CardLayout());
        panel.setBackground(Color.decode(getColor1()));
        panel.setPreferredSize(new Dimension(1060, 660));

        panel.add(farm1.page_farm(), "farm1");
        panel.add(farm2.page_farm(), "farm2");
        panel.add(shop.page_shop(), "shop");
        panel.add(save.page_save(), "save");
        panel.add(load.page_load(), "load");
        panel.add(plugin.page_plugin(), "plugin");

        showPage(panel, page);

        return panel;
    }

    public void showPage(JPanel panel, int page) {
        CardLayout cl = (CardLayout) (panel.getLayout());
        switch (page) {
            case 1:
                cl.show(panel, "farm1");
                break;
            case 2:
                cl.show(panel, "farm2");
                break;
            case 3:
                cl.show(panel, "shop");
                break;
            case 4:
                cl.show(panel, "save");
                break;
            case 5:
                cl.show(panel, "load");
                break;
            case 6:
                cl.show(panel, "plugin");
                break;
        }
    }

    public void updateMainPanel() {
        showPage(mainPanel, page);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        App p = new App();
        p.initialize();
    }
}
