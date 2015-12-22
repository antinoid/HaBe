package heartbleeddemo.server.model.ram;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author da
 */
public class VirtualRam {

    private static final int TEXTFILE_OFFSET = 0x10000; // 64kB offset for textfile
    private byte[] data;
    private int networkDataPointer = 0x0;
    
    public VirtualRam(int size) {
        data = new byte[size];
    }
    
    /**
     * Store file in Virtual Ram
     * max filesize is 64 kB
     * @param file 
     */
    public void storeTextFile(File file) {
        
        InputStream input = null;
        byte[] buffer = new byte[0x10000];
        try {
            input = new FileInputStream(file);
            int bytesRead = input.read(buffer);
            System.arraycopy(buffer, 0, data, TEXTFILE_OFFSET, bytesRead);
            input.close();
        } catch (Exception e) {
        } finally {
            try {
                if (input != null)
                    input.close(); 
            } catch (Exception e) {
            }
        }
    }
    
    public void storeNetworkData(byte[] networkData) {
        networkDataPointer = 0x10000 - networkData.length - 10;
        System.arraycopy(networkData, 0, data, networkDataPointer, networkData.length);
    }
    
    public byte[] getNetworkData(int length) {
        return Arrays.copyOfRange(data, networkDataPointer, networkDataPointer + length);
    }
}
