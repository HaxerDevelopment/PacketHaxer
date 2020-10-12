package io.haxerdevelopment.web.handlers.api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import io.haxerdevelopment.Globals;
import io.haxerdevelopment.proxy.logging.LogPacket;

import java.io.IOException;
import java.io.OutputStream;

public class RequestCheckLoginHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        StringBuilder builder = new StringBuilder();

        builder.append("success");

        byte[] bytes = builder.toString().getBytes();
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.sendResponseHeaders(200, bytes.length);

        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}
