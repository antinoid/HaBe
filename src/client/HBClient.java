package client;

import java.net.Socket;

/**
 *
 * @author kothieringer
 */
public class HBClient {

    private String address;
    private int port;
    private Socket socket;
    
    public HBClient(String address, int port) {
        this.address = address;
        this.port = port;
    }
    
    public void start() {
        
        try {
            socket = new Socket(address, port);
        } catch (Exception e) {
            System.out.println("connection error: " + e);
        }
    }
    
    public void jizz(String sperm) {
        
    }
    
    public static void main(String[] args) {
        HBClient client = new HBClient("localhost", 16222);
        client.start();
        
    }
}
