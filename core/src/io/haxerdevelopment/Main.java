package io.haxerdevelopment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

import io.haxerdevelopment.proxy.CommonProxyHTTP;

public class Main {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        UI.initializeUI();
        new Thread(() -> {
            CommonProxyHTTP proxyHTTP = new CommonProxyHTTP(8888);
            proxyHTTP.listen();
        }).start();
        System.out.println("Enter packet to hax\n");
        //String s = reader.readLine();
        //int a = Integer.parseInt(s);
        System.out.println("Let's hack some packets");
        Thread.sleep(1000);
        for (int i = 0; i <= 100; i++) {
            System.out.println(i+"%");
            //UI.label1.setText(i + "%");
            Thread.sleep((100));
        }
        System.out.println("Successfully hacked");
    }

}
