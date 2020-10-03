package com.company;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;

public class UI {
    static Frame frame = new Frame("Haxer");
    public static Label label1 = new Label("100");
    static Button b2 = new Button("2");
    static Button b1 = new Button("1");

    public static void InitializeUI(){
        frame.setVisible(true);
        frame.setSize(1200, 720);
        b1.setSize(600, 360);
        b1.addActionListener(e -> System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSAAAAAAAAAAAAAAAAAAASSSSSSSSSSSSSSSSSS"));
        b2.setSize(600, 360);
        b2.setLocation(600,360);
        label1.setLocation(200,500);
        label1.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 25));
        ActionListener b2L = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Hax");
            }
        };
        b2.addActionListener(b2L);
        frame.add(b1);
        frame.add(b2);
        frame.add(label1);
    }
}
