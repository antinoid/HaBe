package heartbleeddemo.server.model.network;

import heartbleeddemo.server.model.ram.VirtualRam;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author da
 */
public class Server extends Observable implements Runnable {

    private Connection con;
    private ServerSocket socket;
    private boolean isRunning = false;
    private VirtualRam ram;
    
    public Server(int port, Observer obs, VirtualRam ram) throws Exception {
        this.ram = ram;
        addObserver(obs);
        try {
            socket = new ServerSocket(port);
        } catch (Exception e) {
        }
    }
    
    public void start() {
        isRunning = true;
        Thread listener = new Thread(this);
        listener.start();
    }
    
    public void stop() {
        isRunning = false;
        try {
            if (socket != null) {
                socket.close();                
            }
        } catch (Exception e) {
        }
    }
    
    public void send(String msg) {
        con.write(msg.getBytes());
    }
    
    @Override
    public void run() {
        
        while (isRunning) {
            try {
                Socket client = socket.accept();
                notifyView("new client connected");
                con = new Connection(client, ram);
                con.start();
            } catch (Exception e) {
            }
        }
    }
    
    private void notifyView(String message) {
        setChanged();
        notifyObservers(message);
    }
}
