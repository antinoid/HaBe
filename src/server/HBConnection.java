package server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author kothieringer
 */
public class HBConnection {

    private HBServer server;
    private Socket socket;
    private InputStream in;
    private OutputStream out;
    
    public boolean ok;
    
    public HBConnection() {
        
    }
}
