package org.gui;

import javax.swing.*;
import java.awt.*;

public class CardPlaceholder extends JPanel {
    private CardPanel panelCard;
    private int width;
    private int height;

    public CardPlaceholder(int x, int y, int width, int height) {
        this.setLayout(null);
        this.setBounds(x, y, width, height);
        this.setBackground(Color.BLUE);
        this.width = width;
        this.height = height;
    }

    public void setPanelCard(CardPanel panel){
        this.panelCard = panel;
        this.panelCard.setBounds(0,0,this.width,this.height);
        this.add(this.panelCard);
        this.revalidate();
        this.repaint();
    }

    public void setPanelEmpty(){
        this.remove(this.panelCard);
        this.revalidate();
        this.repaint();
    }
}
