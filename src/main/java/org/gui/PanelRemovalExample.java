package org.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelRemovalExample extends JFrame {
    private JPanel panel1;
    private JPanel panel2;
    private JButton removeButton;

    public PanelRemovalExample() {
        setTitle("Remove Panel Example");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panel1 = new JPanel(new BorderLayout());
        panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        panel2 = new JPanel();
        panel2.setBackground(Color.RED);
        panel2.setPreferredSize(new Dimension(100, 100));

        removeButton = new JButton("Remove Red Panel");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removePanel2();
            }
        });

        panel1.add(panel2, BorderLayout.CENTER);
        add(panel1, BorderLayout.CENTER);
        add(removeButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void removePanel2() {
        panel1.remove(panel2);
        panel1.revalidate();
        panel1.repaint();
    }

    public static void main(String[] args) {
        new PanelRemovalExample();
    }
}
