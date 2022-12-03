package manager;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import java.util.ArrayList;
import manager.StartServer;
import server.Server;
import manager.HeartBeatReceiver;

public class Manager {
    int replica_number;
    ArrayList<String> serverList;
    HeartBeatReceiver hbr;
    public Manager(){
        this.serverList = new ArrayList<String>();
        this.replica_number = 2;
        hbr = new HeartBeatReceiver();
    }
    // public void startServers(){
    //     for (int i=0; i < this.replica_number; i++){
    //         Server s = new StartServer(i).start();
    //         System.out.println(s.toString());
    //     }
    //     System.out.println("done");
    // }
    public void startHeartBeatReceiver(){
        this.hbr.startHeartBeat();
    }

    public static void main(String[] args) {
        Manager man = new Manager();
        man.startHeartBeatReceiver();
        try{
            while(true){
                Thread.sleep(1000);
                System.out.println("still alive");
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
