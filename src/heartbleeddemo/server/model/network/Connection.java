package heartbleeddemo.server.model.network;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import heartbleeddemo.server.controller.OldServerController;

/**
 *
 * @author kothieringer
 */
public class Connection {
    
    private Socket socket;
    private InputStream in;
    private OutputStream out;
    private boolean isRunning = false;
    
    /**
     * create new connection instance
     */
    public Connection(Socket socket) {
        this.socket = socket;
    }
    
    public void start() {
        isRunning = true;
        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();
            byte[] buffer;
            int size;
            while (isRunning) {
                if ((size = in.available()) != 0) {
                    buffer = new byte[size];
                    in.read(buffer, 0, size);
                }
            }
        } catch (Exception e) {
        }
    }
    
    public void send() {
        
    }
    
    public void close() {
        isRunning = false;
        try {
            in.close();
            out.close();
        } catch (Exception e) {
        }
    }
}
