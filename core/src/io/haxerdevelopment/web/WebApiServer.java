package io.haxerdevelopment.web;

import com.sun.net.httpserver.HttpServer;
import io.haxerdevelopment.web.handlers.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Random;

public class WebApiServer {

    public String randomString(int length) {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = length;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public String accessToken;
    public boolean error = false;

    public WebApiServer() {
        accessToken = randomString(32);
        HttpServer server = null;
        try {
            server = HttpServer.create();
            server.bind(new InetSocketAddress(8765), 0);
            server.createContext("/" + accessToken + "/getAllRequests", new RequestListHandler());
            server.createContext("/" + accessToken + "/getStatus", new RequestStatusHandler());
            server.start();
        } catch (IOException exception) {
            error = true;
            exception.printStackTrace();
        }

    }
}
