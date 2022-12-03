package server;
import java.net.*;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HeartBeatSender implements Runnable{
    int serverNumber;
    InetAddress MANAGER_ADDRESS;
    int MANAGER_PORT;
    InetAddress ADDRESS;
    int PORT;
    boolean running;

    HeartBeatSender(int serverNumber, InetAddress manager_address, int maneger_port, InetAddress address, int port){
        this.serverNumber = serverNumber;
        this.MANAGER_ADDRESS = manager_address;
        this.MANAGER_PORT = maneger_port;
        this.ADDRESS = address;
        this.PORT = port;
    }
    @Override
    public void run(){
        byte[] buf = null;
        DatagramPacket packet = null;
        DatagramSocket socket = null;
        //se creeaza un socket pe un port oarecare
        try{
            socket = new DatagramSocket(this.PORT, this.ADDRESS);
        
            try {
                //trimite un pachet catre toti clientii din grup
                String s = new String(String.valueOf(serverNumber));
                buf = s.getBytes();
                packet = new DatagramPacket(buf, buf.length, this.MANAGER_ADDRESS, this.MANAGER_PORT);
                while(running){
                    socket.send(packet);
                    System.out.println("sending heartbeat " + s);
                    Thread.sleep(2000);
                }
            }catch(Exception e1){
                System.out.println(e1.toString());
            }
        }catch(SocketException e){
            System.out.println(e.toString());
        }finally {
            socket.close();
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