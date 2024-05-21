package org.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.*;

public class Farm extends JPanel implements DragGestureListener {
    private CardPlaceholder sourceDragPanel;

    public Farm(){
        this.setLayout(null);
        this.setBounds(0,0,1060,700);
        this.setBackground(Color.GREEN);
        LadangPanel ladangPanel = new LadangPanel();
        this.add(ladangPanel);
    }

    public void dragGestureRecognized(DragGestureEvent event) {

        var cursor = Cursor.getDefaultCursor();
        var panel = (CardPanel) event.getComponent();

        if (event.getDragAction() == DnDConstants.ACTION_MOVE) {
            cursor = DragSource.DefaultMoveDrop;
        }
        this.sourceDragPanel = (CardPlaceholder) panel.getParent();
        this.sourceDragPanel.setPanelEmpty();
        event.startDrag(cursor, panel);
    }

    public CardPlaceholder getSourceDragPanel(){
        return this.sourceDragPanel;
    }
}
