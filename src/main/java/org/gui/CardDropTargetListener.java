package org.gui;

import java.awt.dnd.*;

public class CardDropTargetListener extends DropTargetAdapter {

    private final DropTarget dropTarget;
    private CardPlaceholder panel;
    Farm dgl;

    public CardDropTargetListener(CardPlaceholder panel, Farm dgl) {
        this.panel = panel;
        this.dgl = dgl;

        dropTarget = new DropTarget(panel, DnDConstants.ACTION_MOVE,
                this, true, null);
    }
    public void drop(DropTargetDropEvent event) {
        try {

            event.acceptDrop(DnDConstants.ACTION_MOVE);
            var tr = event.getTransferable();
            if (event.isDataFlavorSupported(CardPanel.card)) {
                CardPanel card1 = (CardPanel) tr.getTransferData(CardPanel.card);
                CardPlaceholder sourcePanel = this.dgl.getSourceDragPanel();
                sourcePanel.setPanelEmpty();
                var ds = new DragSource();
                this.panel.setPanelCard(card1);
                ds.createDefaultDragGestureRecognizer(card1, DnDConstants.ACTION_MOVE, this.dgl);
                event.dropComplete(true);
                return;
            }

            event.rejectDrop();
        } catch (Exception e) {
            e.printStackTrace();
            event.rejectDrop();
        }
    }
}
