package org.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.*;

public class Farm extends JPanel implements DragGestureListener {
    private CardPlaceholder sourceDragPanel;
    private boolean isDragging;

    public Farm(){
        this.setLayout(null);
        this.setBounds(0,0,1060,700);
        this.setBackground(Color.GREEN);
        LadangPanel ladangPanel = new LadangPanel(this);
        this.add(ladangPanel);
    }

    public void dragGestureRecognized(DragGestureEvent event) {
        if(this.isDragging){return;};
        this.isDragging=true;
        var cursor = Cursor.getDefaultCursor();
        var panel = (CardPanel) event.getComponent();

        if (event.getDragAction() == DnDConstants.ACTION_MOVE) {
            cursor = DragSource.DefaultMoveDrop;
        }
        this.sourceDragPanel = (CardPlaceholder) panel.getParent();
        event.startDrag(cursor, panel);
//        this.sourceDragPanel.setPanelEmpty();
    }

    public CardPlaceholder getSourceDragPanel(){
        return this.sourceDragPanel;
    }

    public void setIsDragging(boolean isDragging){
        this.isDragging=isDragging;
    }

    public boolean getIsDragging(){
        return isDragging;
    }
}
