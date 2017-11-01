package com.codecool;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    public static void main(String[] args) throws IOException {

        System.out.println("starting, woot woot");

        HttpServer server = HttpServer.create(new InetSocketAddress(8888), 0);
        server.createContext("/", new Handler());
        server.start();
    }
}
