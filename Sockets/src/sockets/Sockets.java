
//package com.journaldev.socket;
package sockets;

import java.io.*;  
import java.net.*; 
        


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author eli
 */


  
public class Sockets {
    public static int port = 8022;
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) throws IOException, ClassNotFoundException,UnknownHostException {
  
        if (args.length != 1) {
            System.out.println("Error: Need argument: server | client | both");
            System.exit(-1);
        }
        String cmd = args [0];
        char cmdChar = cmd.charAt(0);
        if (cmdChar == 's') {
            Thread t1 = new Thread(new ServerThread (), "t1");
            System.out.println("Starting Runnable Server thread");
            t1.start();              
        }
        else if (cmdChar == 'c') {
            Thread t2 = new Thread(new ClientThread (), "t2");
            System.out.println("Starting Runnable Client thread");
            t2.start();             
        }
        else if (cmdChar == 'b') {
            Thread t1 = new Thread(new ServerThread (), "t1");
            System.out.println("Starting Runnable client threads");
            t1.start();              
            Thread t2 = new Thread(new ClientThread (), "t2");
            System.out.println("Starting Runnable server threads");
            t2.start();            
        }
        else {
            System.out.println("Error: Need argument: server | client | both");
            System.exit(-1);            
        }
    }    
}

