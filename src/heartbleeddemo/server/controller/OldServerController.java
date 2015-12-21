package heartbleeddemo.server.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author kothieringer
 */
public class OldServerController implements Runnable, ActionListener {

    private int port;
    private ServerSocket socket;
    private Socket client;
    private Thread runner;
    private boolean isRunning;
    
    public OldServerController(int port) {
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
                System.out.println("new client");
            } catch (Exception e) {
                System.out.println("error connecting to client: " + e);
            }
            
            if (client != null) {
                //processJizz();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
    private void processJizz() {
        try {
            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            
            in.close();
            out.close();
        } catch (Exception e) {
        }
    }
    
    public static void main(String[] args) {
        OldServerController server = new OldServerController(16222);
        server.start();
    }
}
