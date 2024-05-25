package org.gui;

import org.ladang.Ladang;

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
                CardPlaceholder sourcePanel = this.dgl.getSourceDragPanel();
                String sourceClass = sourcePanel.getClass().getName();
                String targetClass = this.panel.getClass().getName();
                CardPanel card1 = (CardPanel) tr.getTransferData(CardPanel.card);
                try {
                    if (sourceClass.equals("org.gui.DeckAktifPlaceHolder") && targetClass.equals("org.gui.DeckAktifPlaceHolder")) {
                        DeckAktifPlaceHolder source = (DeckAktifPlaceHolder) sourcePanel;
                        DeckAktifPlaceHolder dest = (DeckAktifPlaceHolder) this.panel;
                        if (App.gameEngine.getGameState() != 3) {
                            App.gameEngine.dndDeckDeck(source.getIdx(), dest.getIdx());
                        }
                    } else if (sourceClass.equals("org.gui.PetakLadangPlaceholder") && targetClass.equals("org.gui.PetakLadangPlaceholder")){
                        PetakLadangPlaceholder source = (PetakLadangPlaceholder) sourcePanel;
                        PetakLadangPlaceholder dest = (PetakLadangPlaceholder) this.panel;
                        if (App.gameEngine.getGameState() != 3) {
                            App.gameEngine.dndLadangLadang(source.getCol(), source.getRow(), dest.getCol(), dest.getRow());
                        }
                    } else if (sourceClass.equals("org.gui.DeckAktifPlaceHolder") && targetClass.equals("org.gui.PetakLadangPlaceholder")) {
                        DeckAktifPlaceHolder source = (DeckAktifPlaceHolder) sourcePanel;
                        PetakLadangPlaceholder dest = (PetakLadangPlaceholder) this.panel;
                        if (App.gameEngine.getGameState() != 3) {
                            App.gameEngine.dndDeckLadang(source.getIdx(), dest.getRow(), dest.getCol());
                        }else {
                            App.gameEngine.dndDeckLadangMusuh(source.getIdx(), dest.getRow(), dest.getCol());
                        }
                    } else {
                        event.rejectDrop();
                        return;
                    }
                    var ds = new DragSource();
                    ds.createDefaultDragGestureRecognizer(card1, DnDConstants.ACTION_MOVE, this.dgl);
                    event.dropComplete(true);
                } catch (Exception b) {
                    System.out.println("Error: " + b.getMessage());
                }
            } else {
                event.rejectDrop();
            }
        } catch (Exception e) {
            event.dropComplete(false);
        }
        event.dropComplete(true);
        this.dgl.render();
        this.dgl.revalidate();
        this.dgl.repaint();
    }
}
