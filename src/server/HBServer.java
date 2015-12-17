package server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author kothieringer
 */
public class HBServer implements Runnable {

    private int port;
    private ServerSocket socket;
    private Socket client;
    private Thread runner;
    private boolean isRunning;
    
    public HBServer(int port) {
        this.port = port;
        
        try {
            socket = new ServerSocket(port);
        } catch (Exception e) {
            System.out.println("cant open socket on port " + port);
        }
    }
    
    public void start() {
        isRunning = true;
        runner = new Thread(this);
        runner.start();
    }
    
    @Override
    public void run() {
        
        while (isRunning) {
            
            client = null;
            
            try {
                client = socket.accept();
            } catch (Exception e) {
                System.out.println("error connecting to client: " + e);
            }
            
            while (client != null) {
                processJizz();
            }
        }
    }
    
    private void processJizz() {
        try {
            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
        } catch (Exception e) {
        }
    }
    
    public static void main(String[] args) {
        HBServer server = new HBServer(16222);
        server.start();
    }
}
