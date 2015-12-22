package heartbleeddemo.server.model.network;

import heartbleeddemo.server.model.ram.VirtualRam;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

/**
 *
 * @author kothieringer
 */
public class Connection implements Runnable {
    
    private Socket socket;
    private InputStream in;
    private OutputStream out;
    private boolean isRunning = false;
    private VirtualRam ram;
    /**
     * create new connection instance
     * @param socket
     */
    public Connection(Socket socket, VirtualRam ram) {
        this.socket = socket;
        this.ram = ram;
    }
    
    public void start() {
        try {
            isRunning = true;
            in = socket.getInputStream();
            out = socket.getOutputStream();
            Thread thread = new Thread(this);
            thread.start();
        } catch (Exception e) {
            isRunning = false;
            e.printStackTrace();
        }
    }
    
    public void write(byte[] data) {
        try {
            out.write(data);
        } catch (Exception e) {
        }
    }
    
    public void close() {
        isRunning = false;
        try {
            in.close();
            out.close();
        } catch (Exception e) {
        }
    }

    @Override
    public void run() {
        byte[] buffer = new byte[0x20000];
        while (isRunning) {
            try {
                int bytesRead = 0;
                bytesRead = in.read(buffer);
                if (bytesRead > 0) {
                    ram.storeNetworkData(Arrays.copyOfRange(buffer, 0, bytesRead));
                    
                    byte[] echoData = ram.getNetworkData(0x900);
                    out.write(echoData);
                    out.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
