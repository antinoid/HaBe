package heartbleeddemo.server.model.network;

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
    
    public Server(int port, Observer obs) throws Exception {
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
    
    @Override
    public void run() {
        
        while (isRunning) {
            try {
                Socket client = socket.accept();
                notifyView("new client connected");
                con = new Connection(client);
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
