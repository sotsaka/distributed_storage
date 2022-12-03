package server;
import java.net.*;
import java.io.*;

public class InstructionReceiver implements Runnable{
    public boolean running;
    public static final int PORT = 5556;
    public DatagramSocket socket = null;
    public DatagramPacket request, response = null;
    
    InstructionReceiver(){
        try{
            this.socket = new DatagramSocket(PORT);
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
                /*
                 * instruction liste
                 * 5556 start/stop heartbeat with a token please public key of manager is stored
                 * stop server (stop heart beat and finish stockage tasks)
                 * maybe try to separate ports for files and sytems tasks
                 * 5557 comm with neighbors in table to exchange info
                 * 5558 get file
                 */
                switch(expression) {
                    case x:
                      // code block
                      break;
                    case y:
                      // code block
                      break;
                    default:
                      // code block
                  }
                  
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
        } finally {
            this.socket.close();
        }
        
    }
    public void startInstructionReceveiver(){
        this.running = true;
        Thread senderThread = new Thread(this);
        senderThread.start();
    }
    public void stopInstructionReceveiver(){
        this.running = false;
    }
}
