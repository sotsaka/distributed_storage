package server;
import server.HeartBeatSender;
import java.net.*;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import java.sql.Timestamp;


public class Server {
    public int serverNumber;
    Map<String, Long> neighborTable;
    String MANAGER_ADDRESS_STR = "127.0.0.1";
    int MANAGER_PORT = 4446;
    InetAddress MANAGER_ADDRESS;
    boolean running;
    HeartBeatSender hbs;
    String ADDRESS_STR = "192.168.10.";
    InetAddress ADDRESS;
    int PORT = 5556;



    public Server(int serverNumber){
        this.serverNumber = serverNumber;
        neighborTable = new HashMap<String,Long>();
        try{
            MANAGER_ADDRESS = InetAddress.getByName(MANAGER_ADDRESS_STR); 
            ADDRESS = InetAddress.getByName(ADDRESS_STR + Integer.toString(serverNumber));
            hbs = new HeartBeatSender (this.serverNumber, this.MANAGER_ADDRESS, this.MANAGER_PORT, this.ADDRESS, this.PORT);

            running = true;

        }
        catch(Exception e){
            System.out.println(e.toString());
            running = false;
        }
    }
    public void startHeartBeat(){
        this.hbs.startHeartBeat();
    }

    public static void main(String[] args) {
        if(args[0] == null){
            System.out.println("please provide server number");
        }
        else{
            Server s = new Server(Integer.valueOf(args[0]));
            s.startHeartBeat();
        }
    }
    public String toString(){
        return Integer.toString(this.serverNumber);
    }

}

