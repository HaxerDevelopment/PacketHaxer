package io.haxerdevelopment.web.handlers.api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import io.haxerdevelopment.Globals;
import io.haxerdevelopment.proxy.logging.LogPacket;

import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;

public class RequestStatusHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        StringBuilder builder = new StringBuilder();

        NumberFormat format = NumberFormat.getInstance();
        builder.append("{\"threads_running\":")
                .append(Globals.loadManager.activeThreadCount)
                .append(",\"ram_usage\":\"")
                .append(format.format(Runtime.getRuntime().totalMemory() / 1024 / 1024))
                .append("MB\"}");

        byte[] bytes = builder.toString().getBytes();
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.sendResponseHeaders(200, bytes.length);

        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}
