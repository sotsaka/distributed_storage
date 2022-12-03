package DTOs;
import java.net.*;

public class Message {
    int type; //0 - heartbeat signal
    String content;

    public Message(){
        this.type = -1;
        this.content = "";
    }
    public Message(int type, String content){
        this.type = type;
        this.content = content;
    }
    public byte[] encode(){
        return (Integer.toString(this.type) + "%20" +this.content).getBytes();
    }
    public Message decode(DatagramPacket request){
        String s = new String(request.getData(), request.getOffset(), request.getLength());
        System.out.println(s);
        return new Message();
    }
}
