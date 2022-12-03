package manager;

import server.Server;

public class StartServer implements Runnable{
    int serverNumber;
    public StartServer(int serverNumber){
        this.serverNumber = serverNumber;
    }
    @Override
    public void run() {
        Server s = new Server(this.serverNumber);
        System.out.println("start server" + s.serverNumber);
    }
    public int getServerNumber(){
        return this.serverNumber;
    }
}