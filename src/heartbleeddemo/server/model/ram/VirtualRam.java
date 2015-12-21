package heartbleeddemo.server.model.ram;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author da
 */
public class VirtualRam {

    private static final int RAM_SIZE = 2000000; // 2 MB
    private static byte[] RAM = new byte[RAM_SIZE];
    private String addressPointer = "null";
    
    public VirtualRam() {
        
        //Path path = Paths.get("D:\\Java\\test.txt");
    }
    
    public void loadDataFromDisc(Path path) {
        try {
            byte[] data = Files.readAllBytes(path);
            //write(data);
        } catch (Exception e) {
        }
    }
    
    public void getData(){
        
    }
    
    private boolean write(byte[] data, int address, boolean isLeetHackingPackage) {
        addressPointer = fakeMalloc(data.length, address);
        
        // in C:
        // if (ptr != null)
        if (addressPointer.equals("null")) {
            System.out.println("RAM Error: couldnt write data");
            return false;
        }
        try {
            System.arraycopy(data, 0, RAM, Integer.valueOf(addressPointer), data.length);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    private byte[] read(int address, int length) {
        byte[] data = null;
        return data;
    }
    
    // real malloc would be without address of course :)
    private String fakeMalloc(int size, int address) {
        return Integer.toHexString(address);
    }
    
    // not used, but could be implemented
    private void free(int address) {
        
    }
}
