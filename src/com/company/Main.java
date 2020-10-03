package com.company;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        Frame frame = new Frame("Haxer");
        frame.setVisible(true);
        frame.setSize(1200, 720);
        Button b1 = new Button("12");
        b1.addActionListener(e -> System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSAAAAAAAAAAAAAAAAAAASSSSSSSSSSSSSSSSSS"));
        frame.add(b1);
        frame.add(b1);
        System.out.println("Enter packet to hax\n");
        //String s = reader.readLine();
        //int a = Integer.parseInt(s);
        System.out.println("Let's hack some packets");
        Thread.sleep(1000);
        for (int i = 0; i <= 100; i++) {
            System.out.println(i+"%");
            Thread.sleep((100));
        }
        System.out.println("Successfully hacked");
    }
}