package io.haxerdevelopment;

import io.haxerdevelopment.replace.ReplaceManager;
import io.haxerdevelopment.replace.ReplaceRule;
import io.haxerdevelopment.replace.ReplaceType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UIEimaen {
    public JTable table1;
    private JPanel panel1;
    private JButton button1;
    public RulesWindow rulesWindow = new RulesWindow();
    //private JTable table2;

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
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rulesWindow.frame.setLocation(frame.getLocation().x+25, frame.getLocation().y+25);
                rulesWindow.frame.setVisible(true);

                ReplaceRule rule = new ReplaceRule();
                rule.type = ReplaceType.REDIRECT;
                //rule.match = "a";
                rule.isGlobal=true;
                rule.replace = "https://www.anilibria.tv";
                //Globals.replaceManager.removeRule(rule);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
