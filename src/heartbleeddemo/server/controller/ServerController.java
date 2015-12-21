package heartbleeddemo.server.controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import heartbleeddemo.server.model.network.Server;
import heartbleeddemo.server.view.ServerView;

/**
 *
 * @author kothieringer
 */
public class ServerController implements ActionListener {

    private ServerView view;
    private Server server;
    
    public ServerController() {
        
        EventQueue.invokeLater(() -> {
            view = new ServerView(this);
            view.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        
        if (cmd.equals("start Server")) {
            view.println("creating server on port " + view.getPort());
            try {
                server = new Server(view.getPort(), view);
                server.start();
                view.println("server running");
                view.println("listening for clients");
            } catch (Exception ex) {
                view.println("failed to create server");
            }
            
        }
    }
}
