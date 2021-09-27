package com.example.examen.severt;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.webserver.WebServer;

import java.io.IOException;
import java.util.logging.Handler;

public class JavaServer {
    public static void main( String[] args ) throws XmlRpcException, IOException {
        System.out.println("Iniciando servidor RPC…");
        PropertyHandlerMapping mapping = new PropertyHandlerMapping();
        mapping.addHandler("Handler", Handler.class);

        WebServer server = new WebServer(1200);
        server.getXmlRpcServer().setHandlerMapping(mapping);
        server.start();
        System.out.println("Esperando petición");
    }
}
