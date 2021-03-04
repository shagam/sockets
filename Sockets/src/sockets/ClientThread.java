/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import sockets.Sockets;


/**
 *
 * @author eli
 */
public class ClientThread extends Thread {

    @Override
    public void run() {
        try {
      //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        for(int i=0; i<5; i++){
            //establish socket connection to server
            String hostName = host.getHostName();
            hostName = "212.235.115.193"; // kaminsky external ip
            socket = new Socket(hostName, Sockets.port);
            //write to socket using ObjectOutputStream
               //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server");
            if(i==4)oos.writeObject("exit");
            else oos.writeObject(""+i);
            //read the server response message
            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Client Message: " + message);
            //close resources
            ois.close();
            oos.close();
            Thread.sleep(100);
        }
         
        } catch (Exception e) {
            System.out.println(e.getStackTrace() + e.getMessage());
        }
    }    
}
