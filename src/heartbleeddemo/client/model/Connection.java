package heartbleeddemo.client.model;

import java.net.Socket;

/**
 * Socket to send and receive data via TCP
 * @author da
 */
public class Connection implements Runnable {

    private String address;
    private int port;
    private Socket socket;
    
    public Connection(String address, int port) {
        this.address = address;
        this.port = port;
    }
    
    /**
     * connect to server and start listening for messages from server
     */
    public void start() {
        try {
            System.out.println("address = " + address);
            System.out.println("port = " + port);
            socket = new Socket(address, port);
            Thread thread = new Thread(this);
            thread.start();
        } catch (Exception e) {
            System.out.println("connection fail: " + e.getMessage());
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception e) {
                System.out.println("fail: " + e.getMessage());
            }
        }
    }
    
    /**
     * send data to server
     * @param data
     * @param packetSize the (fake) size of the package (max 64 kb)
     */
    public void send(byte[] data, int packetSize) {
        
    }
    
    @Override
    public void run() {
        
        long before = System.currentTimeMillis();
        int i = 0;
        while (true) {
            if (System.currentTimeMillis() - before < 1000000) {
                System.out.println(i);
                i++;
                before = System.currentTimeMillis();
            }
        }
    }
}
