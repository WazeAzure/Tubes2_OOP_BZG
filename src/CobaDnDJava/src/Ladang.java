import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Ladang extends JPanel {
    private int row;
    private int col;
    private List<DropTargetPanel> listOfDropTargetPanel;
    private List<CardPanel> listThrownCard;
    private boolean isExpanded;

    public Ladang(int row, int col){
        this.row = row;
        this.col = col;
        this.isExpanded = false;
        this.listThrownCard = new ArrayList<>();
        this.listOfDropTargetPanel = new ArrayList<>();
        for(int i=0;i < row;i++){
            for(int j=0;j < col;j++){
                this.listOfDropTargetPanel.add(new DropTargetPanel(i,j));
            }
        }
    }

//    Implementasi Bonus
//    private int getListIndex(int i, int j){
//        int idx;
//        if (isExpanded){
//
//        }else{
//            idx = j + (i*this.row);
//        }
//        return idx;
//    }
}
