package com.codecool;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;

public class TestPath {

    @WebRoute(path = "/")
    public void indexPage(HttpExchange data) throws IOException {
        String message = "Root called.";
        System.out.println(message + data.getRequestURI().getPath());
        myResponse(data, message);
    }

    @WebRoute(path = "/users")
    public void userPage(HttpExchange data) throws IOException {
        String message = "User page called.";
        System.out.println(message + data.getRequestURI().getPath());
        myResponse(data, message);
    }

    @WebRoute(path="/trash")
    public void trashBin(HttpExchange data) throws IOException {
        String message = "Someone's looking for something in the trashbin. ";
        System.out.println(message + data.getRequestURI().getPath());
        myResponse(data, message);
    }


    private void myResponse(HttpExchange requestData, String response) throws IOException {
        requestData.sendResponseHeaders(200, response.length());
        OutputStream os = requestData.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
