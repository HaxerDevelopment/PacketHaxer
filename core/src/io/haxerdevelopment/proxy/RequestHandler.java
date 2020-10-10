package io.haxerdevelopment.proxy;

import java.io.*;
import java.net.*;

public class RequestHandler implements Runnable {

    private Socket socket;

    public RequestHandler(Socket socket)
    {
        this.socket = socket;
    }



    private void handle(Socket socket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String requestData = "";
        try {
            requestData = reader.readLine();
        }
        catch (Exception ex) {
            System.out.println("Cant read from stream, DO SOMETHING!!1!");
        }
        if (requestData == null)
            return;
        String url = requestData.substring(requestData.indexOf(' ') + 1, requestData.indexOf(' ', requestData.indexOf(' ') + 2));
        String requestType = requestData.substring(0, requestData.indexOf(' ')); // GET, POST, CONNECT
        System.out.print("Got a " + requestType + " request to URL " + url + ": ");
        if (requestType.contains("CONNECT"))
        {
            String urlCut = url; // Remove https://
            String pieces[] = urlCut.split(":");
            urlCut = pieces[0];
            int port  = Integer.valueOf(pieces[1]);
            try {
                InetAddress address = InetAddress.getByName(urlCut);
                Socket serverConnectionSocket = new Socket(address, port);

                String line = "HTTP/1.1 200 Connection established\r\n" +
                        "Proxy-Agent: packet-haxer/1.0\r\n";
                writer.write(line);

                try {
                    byte[] buffer = new byte[4096];
                    int read;
                    do {
                        read = serverConnectionSocket.getInputStream().read(buffer);
                        System.out.println(read);
                        if (read > 0) {
                            socket.getOutputStream().write(buffer, 0, read);
                            if (serverConnectionSocket.getInputStream().available() < 1) {
                                socket.getOutputStream().flush();
                            }
                        }
                    } while (read >= 0);
                }
                catch (SocketTimeoutException e) {

                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                serverConnectionSocket.close();
            } catch (SocketTimeoutException e) {
                String line = "HTTP/1.1 504 Timeout 10s\r\n";
                writer.write(line);
            }
        }
        else if (requestType.contains("GET"))
        {
            URL remoteURL = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)remoteURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("User-Agent", "Mozilla/4.0");
            conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            conn.setDoOutput(true);
            conn.setUseCaches(false);

            if (conn.getResponseCode() == 200)
            {
                BufferedReader serverReader =
                        new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String line = "HTTP/1.1 200 OK\r\n" +
                        "Proxy-agent: packet-haxer/1.0\r\n" +
                        "\r\n";

                writer.write(line);
                while((line = serverReader.readLine()) != null)
                    writer.write(line);
                System.out.println("answered");
            }
            else
            {
                System.out.println("http error (" + conn.getResponseCode() + ")");
                String line = "HTTP/1.1 200 OK\r\n" +
                        "Proxy-agent: packet-haxer/1.0\r\n";
                writer.write(line);
                writer.write("I am really sorry, but this proxy is not configured to handle your request :c");
            }
        }
        else
            System.out.println("unknown request type");
        writer.flush();
        writer.close();
    }

    @Override
    public void run() {
        try {
            handle(socket);
        }
        catch (Exception ex) {
            System.out.println("Unable to handle this packet, sorry :c");
        }
    }
}
