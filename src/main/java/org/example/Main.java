package org.example;

import org.example.application.echo.EchoApplication;
import org.example.server.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(new EchoApplication());
        server.start();
    }
}
