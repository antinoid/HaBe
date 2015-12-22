package heartbleeddemo.server.controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import heartbleeddemo.server.model.network.Server;
import heartbleeddemo.server.model.ram.VirtualRam;
import heartbleeddemo.server.view.ServerView;
import java.io.File;

/**
 *
 * @author kothieringer
 */
public class ServerController implements ActionListener {

    private ServerView view;
    private Server server;
    private VirtualRam ram;
    
    public ServerController() {
        
        EventQueue.invokeLater(() -> {
            view = new ServerView(this);
            view.setVisible(true);
            ram = new VirtualRam(0x40000);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        
        if (cmd.equals("start Server")) {
            view.println("creating server on port " + view.getPort());
            try {
                server = new Server(view.getPort(), view, ram);
                server.start();
                view.println("server running");
                view.println("listening for clients");
            } catch (Exception ex) {
                view.println("failed to create server");
            }
            
        } else if (cmd.equals("load File")) {
            File file = view.getFile();
            
            if (file != null) {
                ram.storeTextFile(file);
            }
        } else if (cmd.equals("test")) {
            server.send("test message");
            view.println("sending test message");
        }
    }
}
