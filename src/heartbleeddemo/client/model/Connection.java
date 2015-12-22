package heartbleeddemo.client.model;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

/**
 * Socket to send and receive data via TCP
 * @author da
 */
public class Connection extends Observable implements Runnable {

    private final String address;
    private final int port;
    private Socket socket;
    private InputStream in;
    private OutputStream out;
    private boolean isRunning = false;
    
    private byte[] data;
    
    public Connection(String address, int port, Observer obs) {
        addObserver(obs);
        this.address = address;
        this.port = port;
    }
    
    /**
     * connect to server and start listening for messages from server
     */
    public void start() {
        try {
            isRunning = true;
            socket = new Socket(address, port);
            in = socket.getInputStream();
            out = socket.getOutputStream();
            notifyView("connected to server");
            Thread thread = new Thread(this);
            thread.start();
        } catch (Exception e) {
            isRunning = false;
            notifyView(e.getMessage());
        }
    }
    
    /**
     * send data to server
     * @param data
     */
    public void send(byte[] data) {
        try {
            out.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @return data stored in buffer
     */
    public byte[] getData() {
        return data;
    }
    
    @Override
    public void run() {
        byte[] buffer = new byte[0x20000];
        while (isRunning) {
            try {
                int bytesRead = 0;
                bytesRead = in.read(buffer);
                if (bytesRead > 0) {
                    notifyView("message received, click save button to store buffer to file");
                    data = new byte[bytesRead];
                    System.arraycopy(buffer, 0, data, 0, bytesRead);
                }
            } catch (Exception e) {
                notifyView("connection lost");
                isRunning = false;
            }
        }
    }
    
    private void notifyView(String message) {
        setChanged();
        notifyObservers(message);
    }
}
