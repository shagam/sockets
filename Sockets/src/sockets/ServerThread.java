/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import sockets.Sockets;
/**
 *
 * @author eli
 */
// IOException,ClassNotFoundException        
public class ServerThread extends Thread  {

//    public MyThread(String name) {
//        super(name);
//    }

    
    private static ServerSocket server;
    @Override
    public void run() {
        
        try {
     //create the socket server object
        server = new ServerSocket(Sockets.port);
        //keep listens indefinitely until receives 'exit' call or program terminates
        while(true){
            System.out.println("Server Waiting for the client request");
            //creating socket and waiting for client connection
            Socket socket = server.accept();
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
            String message = (String) ois.readObject();
            System.out.println("Server eMssage Received: " + message);
            //create ObjectOutputStream object
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //write object to Socket
            oos.writeObject("Hi Client "+message);
            //close resources
            ois.close();
            oos.close();
            socket.close();
            //terminate the server if client sends exit request
            if(message.equalsIgnoreCase("exit")) break;
        }
        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        server.close();

        } catch (Exception e) {
            System.out.println(e.getStackTrace() + e.getMessage());
        }

    }
        
}