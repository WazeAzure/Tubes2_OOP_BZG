import javax.swing.*;
import java.awt.*;

public class DragSourcePanel extends JPanel{
    private CardPanel panelCard;

    public DragSourcePanel(){
        this.setMinimumSize(new Dimension(50,50));
        this.setBackground(Color.BLACK);
    }

    public void setPanelCard(CardPanel panel){
        this.panelCard = panel;
        this.add(this.panelCard);
        revalidate();
    }

    public CardPanel getPanelCard(){
        return this.panelCard;
    }
}
