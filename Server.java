package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        ServerSocket serverSocket=null;
        Socket socket;

        //create a listening socket
        try {
            serverSocket=new ServerSocket();
            serverSocket.bind(new InetSocketAddress("127.0.0.1",3000));
        }catch (SecurityException sec){
            System.err.println("Provide a port between 1024 and 65535. ");
        }catch (IOException io){
            System.err.println("The specified port number is already in use");
        }


        System.out.println("Server running at : "+serverSocket.getLocalSocketAddress()+" ...");


        //server loop
        while (true){
            try {
                socket=serverSocket.accept();
                System.out.println("" +
                        "From : " + socket.getLocalSocketAddress().toString() + ". " +
                        "Type:  " + new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine()
                );


                PrintStream printStream = new PrintStream(socket.getOutputStream());
                printStream.print("Hello world from Java server");
                printStream.flush();

                socket.close();
                socket=null;//this really works
            }catch (IOException io){
                io.printStackTrace();
            }


        }
    }

}
