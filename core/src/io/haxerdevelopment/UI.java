package io.haxerdevelopment;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UI {

    static Frame frame = new Frame("Haxer");
    static JFrame jframe = new JFrame("JHaxer");
    public static JLabel jlabel= new JLabel("000000000000");
    public static Label label1 = new Label("100");
    public static Button b2 = new Button("2");
    static Button b1 = new Button("1");

    public static void initializeUI(){
        initializeAwtUI();
        //initializeSwingUI();

    }
    static void initializeSwingUI(){
        jframe.setSize(1200, 600);
        jframe.setVisible(true);
        jframe.setLocation(1000,500);
        JButton jB1=new JButton("J123");
        JButton jB2 = new JButton("J321");
        JPanel buttons = new JPanel();
        buttons.setBounds(0,0, 1200, 600);
        buttons.add(jB1);
        buttons.add(jB2);
        buttons.add(jlabel);
        //jlabel.setLocation(100, 200);
        buttons.setBackground(new Color(255,0,0,100));
        jB1.setBackground(new Color(0,255,0,50));
        jB1.setBounds(0,0,640, 300);
        jB2.setSize(1000, 300);
        jframe.add(jlabel);
        //jframe.add(buttons);
//        jframe.add(jB1);
//        jframe.add(jB2);

    }
    static void initializeAwtUI(){
        frame.setVisible(true);
        frame.setSize(1200, 720);
        frame.setBackground(Color.GREEN);
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
        b2.setBackground(Color.white);
        b1.setBackground(Color.white);
        frame.add(b1);
        frame.add(b2);
        frame.add(label1);
        frame.addWindowListener(new WindowAdapter() {
                                public void windowClosing(WindowEvent we) {
                                    System.exit(0);
                                }
                            }
        );
    }
}
