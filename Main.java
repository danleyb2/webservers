package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        ServerSocket serverSocket=null;
        Socket socket;

        //create a listening socket
        try {
            serverSocket=new ServerSocket(3000);
        }catch (SecurityException sec){
            System.err.println("Provide a port between 1024 and 65535. ");
        }catch (IOException io){
            System.err.println("The specified port number is already in use");
        }


        System.out.println("Server running... ");


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
