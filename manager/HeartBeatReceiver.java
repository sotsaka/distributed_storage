package manager;

import java.net.*;
import java.io.*;

public class HeartBeatReceiver implements Runnable{
    public boolean running;
    public int increment;
    public static final int MANAGER_PORT = 4446;
    public DatagramSocket socket = null;
    public DatagramPacket request, response = null;
    
    HeartBeatReceiver(){
        try{
            this.socket = new DatagramSocket(MANAGER_PORT);
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }
    public void run(){
        this.startReceiving();
    }
    public void startReceiving(){
        try{
            while(running){
                //Creating the package in which the application will be received
                byte[] buf = new byte[256];
                request = new DatagramPacket(buf, buf.length);
                //Pick up the application package
                this.socket.receive(request);
                //Please indicate the address and port from which the request comes from
                InetAddress adresa = request.getAddress();
                int port = request.getPort();
                System.out.println("Adresa si port client: " + adresa + ":" + port);
                String s = "Hello " + new String(request.getData(), request.getOffset(), request.getLength());
                System.out.println(s);
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
        } finally {
            this.socket.close();
        }
        
    }
    public void startHeartBeat(){
        this.running = true;
        Thread senderThread = new Thread(this);
        senderThread.start();
    }
    public void stopHeartBeat(){
        this.running = false;
    }
}
