package io.haxerdevelopment.web.handlers.api;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import io.haxerdevelopment.Globals;
import io.haxerdevelopment.proxy.logging.LogPacket;

import java.io.IOException;
import java.io.OutputStream;

public class RequestListHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        StringBuilder builder = new StringBuilder();

        builder.append("[");
        for (LogPacket packet : Globals.logManager.logPackets) {
            builder.append("{\"packetBody\":\"")
                    .append(packet.packetBody
                            .replace("\"", "\\\"")
                            .replace("\n", " "))
                    .append("\",\"destination\":\"")
                    .append(packet.destination)
                    .append("\"},");
        }
        builder.delete(builder.length() - 1, builder.length());
        builder.append("]");

        byte[] bytes = builder.toString().getBytes();
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.sendResponseHeaders(200, bytes.length);

        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}