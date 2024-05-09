import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;

public class CobaDnd {

    public static class ComplexDnD extends JFrame
            implements DragGestureListener {

        private DragSourcePanel leftPanel;
        private DropTargetPanel rightPanel;

        public ComplexDnD() {

            initUI();
        }

        private void initUI() {

//            var colourBtn = new JButton("Choose Color");
//            colourBtn.setFocusable(false);

            leftPanel = new DragSourcePanel();

            CardPanel cp = new CardPanel("nama","gambar");

            leftPanel.setPanelCard(cp);

            rightPanel = new DropTargetPanel(0,1);

            var mtl = new MyDropTargetListener(rightPanel);

            var ds = new DragSource();
//            ds.createDefaultDragGestureRecognizer(leftPanel,
//                    DnDConstants.ACTION_COPY, this);

            ds.createDefaultDragGestureRecognizer(cp,
                    DnDConstants.ACTION_MOVE, this);

            createLayout(leftPanel, rightPanel);

            setTitle("Complex drag and drop example");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
        }

        public void dragGestureRecognized(DragGestureEvent event) {

            var cursor = Cursor.getDefaultCursor();
            var panel = (CardPanel) event.getComponent();

//            var color = panel.getBackground();

            if (event.getDragAction() == DnDConstants.ACTION_MOVE) {
                cursor = DragSource.DefaultCopyDrop;
            }

            event.startDrag(cursor, panel);
        }

        private class MyDropTargetListener extends DropTargetAdapter {

            private final DropTarget dropTarget;
            private DropTargetPanel panel;

            public MyDropTargetListener(DropTargetPanel panel) {
                this.panel = panel;

                dropTarget = new DropTarget(panel, DnDConstants.ACTION_MOVE,
                        this, true, null);
            }


            public void drop(DropTargetDropEvent event) {

                try {

                    event.acceptDrop(DnDConstants.ACTION_MOVE);
                    var tr = event.getTransferable();
//                    var col = (Color) tr.getTransferData(TransferableColor.colorFlavor);
//                    if (event.isDataFlavorSupported(TransferableColor.colorFlavor)) {
                    if (event.isDataFlavorSupported(CardPanel.card)) {
                        CardPanel card1 = (CardPanel) tr.getTransferData(CardPanel.card);
                        this.panel.setPanelCard(card1);
//                        ladang.getKartu(i,j);
//                        ladang.drop(i, j, card1)
//                        this.panel.add(card1);
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

        private void createLayout(JComponent... arg) {

            var pane = getContentPane();
            var gl = new GroupLayout(pane);
            pane.setLayout(gl);

            gl.setAutoCreateContainerGaps(true);
            gl.setAutoCreateGaps(true);

            gl.setHorizontalGroup(gl.createSequentialGroup()
                    .addComponent(arg[0])
                    .addGap(30)
                    .addComponent(arg[1])
                    .addGap(30)
//                    .addComponent(arg[2])
            );

            gl.setVerticalGroup(gl.createParallelGroup()
                    .addComponent(arg[0])
                    .addComponent(arg[1])
//                    .addComponent(arg[2])
            );

            pack();
        }

        public static void main(String[] args) {

            EventQueue.invokeLater(() -> {

                var ex = new ComplexDnD();
                ex.setVisible(true);
            });
        }
    }

//    static class TransferableColor implements Transferable {
//
//        protected static final DataFlavor colorFlavor =
//                new DataFlavor(Color.class, "A Color Object");
//
//        protected static final DataFlavor[] supportedFlavors = {
//                colorFlavor,
//                DataFlavor.stringFlavor,
//        };
//
//        private final Color color;
//
//        public TransferableColor(Color color) {
//
//            this.color = color;
//        }
//
//        public DataFlavor[] getTransferDataFlavors() {
//
//            return supportedFlavors;
//        }
//
//        public boolean isDataFlavorSupported(DataFlavor flavor) {
//
//            return flavor.equals(colorFlavor) ||
//                    flavor.equals(DataFlavor.stringFlavor);
//        }
//
//
//        public Object getTransferData(DataFlavor flavor)
//                throws UnsupportedFlavorException {
//
//            if (flavor.equals(colorFlavor)) {
//                return color;
//            } else if (flavor.equals(DataFlavor.stringFlavor)) {
//                return color.toString();
//            } else {
//                throw new UnsupportedFlavorException(flavor);
//            }
//        }
//    }
}
