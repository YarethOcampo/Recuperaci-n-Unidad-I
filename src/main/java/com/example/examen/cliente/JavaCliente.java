package com.example.examen.cliente;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class JavaCliente {
    public static void main( String[] args ) throws MalformedURLException, XmlRpcException {
        Scanner leer = new Scanner(System.in);
        String name;
        String lastname;
        String email;
        String password;
        int status;
        int opc;
        int id;
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL( new URL( "http://localhost:1200" ) );

        System.out.println("1. Registrar" +
                "2. Actualizar" +
                "3.-Eliminar" +
                "4. Mostrar");
        opc= leer.nextInt();
        switch (opc){
            case 1:
                System.out.println("ingrese el nombre");
                name = leer.next();
                System.out.println("ingrese el apellido");
                lastname = leer.next();
                System.out.println("ingrese el email");
                email = leer.next();
                System.out.println("ingrese el password");
                password = leer.next();
                System.out.println("ingrese el estatus");
                status = leer.nextInt();
                XmlRpcClient client = new XmlRpcClient();
                client.setConfig( config );
                Object[] params = { name,lastname,email,password,status };
                boolean result = (boolean) client.execute( "Handler.createUser",  params);
                System.out.println( result );
                break;
            case 2:

                System.out.println("ingrese el nombre");
                name = leer.next();
                System.out.println("ingrese el apellido");
                lastname = leer.next();
                System.out.println("ingrese el email");
                email = leer.next();
                System.out.println("ingrese el password");
                password = leer.next();
                System.out.println("ingrese el estatus");
                status = leer.nextInt();
                System.out.println("ingrese el id");
                id = leer.nextInt();
                XmlRpcClient client2 = new XmlRpcClient();
                client2.setConfig( config );
                Object[] params2 = { name,lastname,email,password,status ,id};
                boolean result2 = (boolean) client2.execute( "Handler.UpdateUser",  params2);
                System.out.println( result2 );
                break;
            case 3:
                System.out.println("ingrese el id");
                id = leer.nextInt();
                XmlRpcClient client3 = new XmlRpcClient();
                client3.setConfig( config );
                Object[] params3 = {id};
                boolean result3 = (boolean) client3.execute( "Handler.UpdateUser",  params3);
                System.out.println( result3 );
                break;
            case 4:
                XmlRpcClient client4 = new XmlRpcClient();
                client4.setConfig( config );
                Object[] params4 = {true};
                String result4 = (String) client4.execute( "Handler.findAll",params4);
                System.out.println(result4);
                break;
        }

    }

}