package io.haxerdevelopment;

import io.haxerdevelopment.replace.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class RulesWindow {
    private JPanel panel1;
    public JFrame frame;
    private JTable table1;
    private JButton deleteButton;
    private JTextField textField5;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton addButton;
    private JComboBox comboBox1;
    private JCheckBox isGlobalCheckBox;

    public RulesWindow() {
        frame = new JFrame();
        comboBox1.addItem(ReplaceType.OVERRIDE);
        comboBox1.addItem(ReplaceType.REDIRECT);
        comboBox1.addItem(ReplaceType.OVERRIDE_PAGE);
        comboBox1.addItem(ReplaceType.PLAIN);
        comboBox1.addItem(ReplaceType.QUERY);
        comboBox1.addItem(ReplaceType.REGEX);
        table1.setModel(new DefaultTableModel(new Object[] {"Type", "URL", "Match", "Replace", "IsGlobal"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.addRow(new Object[] {"Type", "URL", "match", "replace", "isGlobal"});
        frame.setSize(1050, 310);
        frame.setContentPane(panel1);
        frame.setVisible(false);
        WindowAdapter adapter = new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                frame.setVisible(false);
            }
        };
        frame.addWindowListener(adapter);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSelected();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReplaceRule rule = new ReplaceRule();
                rule.type = (ReplaceType)comboBox1.getSelectedItem() ;
                rule.url = textField2.getText();
                rule.match = textField3.getText();
                rule.replace = textField4.getText();
                rule.isGlobal = isGlobalCheckBox.isSelected();
                Globals.replaceManager.addRule(rule);
            }
        });
        table1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()== KeyEvent.VK_DELETE)
                    removeSelected();
                super.keyTyped(e);
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
