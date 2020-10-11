package io.haxerdevelopment;

import io.haxerdevelopment.replace.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RulesWindow {
    private JPanel panel1;
    public JFrame frame;
    private JTable table1;
    private JButton button1;

    public RulesWindow() {
        frame = new JFrame();
        table1.setModel(new DefaultTableModel(new Object[] {"Type", "URL", "Match", "Replace", "IsGlobal"}, 0));
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.addRow(new Object[] {"Type", "URL", "match", "replace", "isGlobal"});
        frame.setSize(640, 310);
        frame.setContentPane(panel1);
        frame.setVisible(true);
        WindowAdapter adapter = new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                frame.setVisible(false);
            }
        };
        frame.addWindowListener(adapter);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSelected();
            }
        });
    }
    public void addRule(ReplaceRule rule) {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.addRow(new Object[] {rule.type, rule.url, rule.match, rule.replace, rule.isGlobal});
    }
    public void removeRule(ReplaceRule rule) {
        boolean isEquals=true;
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        for(int i =1; i < model.getRowCount(); i++){
            isEquals = true;
            for(int j = 0; j < model.getColumnCount(); j++) {
                switch (model.getColumnName(j)){
                    case "Type" -> {isEquals=rule.type==model.getValueAt(i, j);break;}
                    case "URL" -> {isEquals=rule.url==model.getValueAt(i, j);break;}
                    case "Match" -> {isEquals=rule.match==model.getValueAt(i, j);break;}
                    case "Replace" -> {isEquals=rule.replace==model.getValueAt(i, j);break;}
                    case "IsGlobal" -> {isEquals=rule.isGlobal==(Boolean) model.getValueAt(i, j);break;}
                }
                if(!isEquals)
                    break;
            }
            if(isEquals){
                model.removeRow(i);
                break;
            }
        }
    }
    public void removeSelected(){
        if(table1.getSelectedRow()>0) {
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            int index = table1.getSelectedRow();
            model.removeRow(index);
            Globals.replaceManager.removeRule(index - 1);
        }
    }
}
