package io.haxerdevelopment.web.handlers.api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import io.haxerdevelopment.Globals;
import io.haxerdevelopment.proxy.logging.LogPacket;
import io.haxerdevelopment.replace.ReplaceRule;

import java.io.IOException;
import java.io.OutputStream;

public class RequestRulesHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        StringBuilder builder = new StringBuilder();

        builder.append("[");
        for (ReplaceRule rule : Globals.replaceManager.replaceRules) {
            builder.append("{\"type\":\"")
                    .append(rule.type)
                    .append("\",\"match\":\"")
                    .append(rule.match)
                    .append("\",\"replace\":\"")
                    .append(rule.replace)
                    .append("\",\"url\":\"")
                    .append(rule.url)
                    .append("\",\"isGlobal\":\"")
                    .append(rule.isGlobal)
                    .append("\"},");
        }
        if (builder.charAt(builder.length() - 1) != '[')
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
