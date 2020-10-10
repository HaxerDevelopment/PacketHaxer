package io.haxerdevelopment.proxy;

import java.io.*;
import java.net.*;

public class CommonProxyHTTP {
    private ServerSocket serverSocket;
    private int port;

    public CommonProxyHTTP(int port)
    {
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
        }
        catch (Exception ex) {
            System.out.println("Failed to bind to port " + port);
        }
    }

    public void listen()
    {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(new RequestHandler(socket));
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
