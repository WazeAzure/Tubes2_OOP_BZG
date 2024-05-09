import javax.swing.*;
import java.awt.*;

public class DropTargetPanel extends JPanel {
    private Integer ladangX;
    private Integer ladangY;
    private JPanel panelCard;

    public DropTargetPanel(Integer i, Integer j){
        this.ladangX = i;
        this.ladangY = j;
        this.panelCard = null;
        this.setMinimumSize(new Dimension(50,50));
        this.setBackground(Color.BLACK);
    }

    public void setPanelCard(JPanel newPanel){
        this.panelCard = newPanel;
        this.add(this.panelCard);
        revalidate();
    }
}
