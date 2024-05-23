package org.gui;

import javax.swing.*;
import java.awt.*;

public class CardPlaceholder extends JPanel {
    protected CardPanel panelCard;
    protected int width;
    protected int height;

    public CardPlaceholder(int x, int y, int width, int height) {
        this.setLayout(null);
        this.setBounds(x, y, width, height);
        this.setBackground(Color.BLUE);
        this.width = width;
        this.height = height;
    }

    public CardPanel getPanelCard() {
        return panelCard;
    }

    public void setPanelCard(CardPanel panel){
        this.panelCard = panel;
        this.panelCard.setBounds(0,0,this.width,this.height);
        this.panelCard.render();
        this.add(this.panelCard);
        this.revalidate();
        this.repaint();
    }

    public void setPanelEmpty(){
        this.remove(this.panelCard);
        this.panelCard=null;
        this.revalidate();
        this.repaint();
    }
}
