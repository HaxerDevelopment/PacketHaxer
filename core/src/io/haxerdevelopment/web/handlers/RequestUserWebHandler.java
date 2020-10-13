package io.haxerdevelopment.web.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import io.haxerdevelopment.Globals;
import io.haxerdevelopment.proxy.logging.LogPacket;

import java.io.IOException;
import java.io.OutputStream;

public class RequestUserWebHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        StringBuilder builder = new StringBuilder();

        String justAWebsite = "<!DOCTYPE html><html lang=\"en\"><head> <meta charset=\"UTF-8\"> " +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> " +
                "<link rel=\"stylesheet\" href=\"style.css\"> <link rel=\"stylesheet\" " +
                "href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" " +
                "integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" " +
                "crossorigin=\"anonymous\"> <title>Y</title></head><body class=\"bg-dark\"> " +
                "<nav class=\"navbar navbar-expand-md navbar-dark fixed-top bg-dark\"> " +
                "<a class=\"navbar-brand\" href=\"#\">PacketHaxer Web</a> <button class=\"navbar-toggler\" " +
                "type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarCollapse\" " +
                "aria-controls=\"navbarCollapse\" aria-expanded=\"false\" aria-label=\"Toggle navigation\"> " +
                "<span class=\"navbar-toggler-icon\"></span> </button> <div class=\"collapse navbar-collapse\" " +
                "id=\"navbarCollapse\"> <ul class=\"navbar-nav mr-auto\"> <li class=\"nav-item active\"> " +
                "<a class=\"nav-link\" href=\"#\">Dashboard <span class=\"sr-only\">(current)</span></a> " +
                "</li><li class=\"nav-item\"> <a class=\"nav-link\" href=\"#\">Link</a> </li><li class=\"nav-item\"> " +
                "<a class=\"nav-link disabled\" href=\"#\" tabindex=\"-1\" aria-disabled=\"true\">Disabled</a> " +
                "</li></ul> </div></nav> <main role=\"main\" class=\"container\" style=\"padding-top: 5rem\"> " +
                "<div class=\"row my-3\"> " +
                "<div class=\"col-sm-4 p-3 text-white bg-primary rounded shadow-sm mx-2\"> " +
                "<h4>Server status</h4> <small class=\"text-white-50\" id=\"status\"></small> </div><div " +
                "class=\"col-sm p-3 text-white bg-success rounded shadow-sm mx-2\"> <h4>Active rules</h4> " +
                "<small class=\"text-white-50\" id=\"status\"></small> <table class=\"table table-dark rounded\"> " +
                "<thead> <tr> <th scope=\"col\">Type</th> <th scope=\"col\">Match</th> <th scope=\"col\">Replace</th> " +
                "<th scope=\"col\">Url</th> <th scope=\"col\">Is Global</th> </tr></thead> " +
                "<tbody id=\"table-rules-body\"> </tbody> </table> </div></div><div class=\"row my-3\"> " +
                "<div class=\"col-sm-12 p-3 text-white bg-primary rounded shadow-sm mx-2\"> " +
                "<h4>Request history (outgoing)</h4> <small class=\"text-white-50\" id=\"status\"></small> " +
                "<table class=\"table table-dark rounded\"> <thead> <tr> <th scope=\"col\">Origin</th> " +
                "<th scope=\"col\">Destination</th> </tr></thead> <tbody id=\"table-requests-body\"> " +
                "</tbody> </table> </div></div><div class=\"modal fade\" id=\"exampleModal\" tabindex=\"-1\" " +
                "aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\"> <div class=\"modal-dialog\"> " +
                "<div class=\"modal-content\"> <div class=\"modal-header\"> <h5 class=\"modal-title\" " +
                "id=\"exampleModalLabel\">Authentication</h5> <button type=\"button\" class=\"close\" " +
                "data-dismiss=\"modal\" aria-label=\"Close\"> <span aria-hidden=\"true\">&times;</span> " +
                "</button> </div><div class=\"modal-body\"> Please, enter the key provided in application's " +
                "stdout: <input id=\"input-accesscode\" type=\"text\" class=\"form-control mt-2\"> </div><div " +
                "class=\"modal-footer\"> <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">" +
                "Discard</button> <button id=\"save-accesscode\" type=\"button\" class=\"btn btn-primary\">Login" +
                "</button> </div></div></div></div></main> " +
                "<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" " +
                "integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" " +
                "crossorigin=\"anonymous\"></script> <script " +
                "src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js\" " +
                "integrity=\"sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN\" " +
                "crossorigin=\"anonymous\"></script> " +
                "<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\" " +
                "integrity=\"sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV\" " +
                "crossorigin=\"anonymous\"></script> <script>var accesscode=\"\"; $('#exampleModal').modal('show'); " +
                "$('#save-accesscode').click(()=>{accesscode=$('#input-accesscode').val(); " +
                "fetch(`http://localhost:8765/api/${accesscode}/checkLogin`).then(response=> " +
                "response.text()).then(body=>{if (body==\"success\"){$('#exampleModal').modal('hide'); " +
                "setInterval(()=>{fetch(`http://localhost:8765/api/${accesscode}/getStatus`).then(response=> " +
                "response.text()).then(body=>{var json=JSON.parse(body); $('#status').html(`Threads running: " +
                "s${json.threads_running}<br>RAM allocated: ${json.ram_usage}`);}); " +
                "fetch(`http://localhost:8765/api/${accesscode}/getRules`).then(response=> response.text())" +
                ".then(body=>{var json=JSON.parse(body); $('#table-rules-body').children().remove(\"*\"); " +
                "json.forEach(element=>{$('#table-rules-body').append(`<tr><td>${element.type}</td><td>" +
                "${element.match}</td><td>${element.replace}</td><td>${element.url}</td><td>${element.isGlobal}" +
                "</td></tr>`);});}); fetch(`http://localhost:8765/api/${accesscode}/getAllRequests`)" +
                ".then(response=> response.text()).then(body=>{var json=JSON.parse(body); $('#table-requests-body')" +
                ".children().remove(\"*\"); json.forEach(element=>{$('#table-requests-body').append" +
                "(`<tr><td>localhost</td><td>${element.destination}</td></tr>`);});});}, 1000);}});}) " +
                "</script></body></html>";
        builder.append(justAWebsite);

        byte[] bytes = builder.toString().getBytes();
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.sendResponseHeaders(200, bytes.length);

        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}
