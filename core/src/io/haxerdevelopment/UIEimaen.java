package io.haxerdevelopment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UIEimaen {
    public JTable table1;
    private JPanel panel1;

    public UIEimaen() {
        table1.setModel(new DefaultTableModel(new Object[] {"Source", "Destination"}, 0));
        JFrame frame = new JFrame();
        frame.setSize(640, 310);
        frame.setContentPane(panel1);
        frame.setVisible(true);
        WindowAdapter adapter = new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        };
        frame.addWindowListener(adapter);
    }
}
