package heartbleeddemo.client.controller;

import heartbleeddemo.client.model.Connection;
import heartbleeddemo.client.view.ClientView;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author da
 */
public class ClientController implements ActionListener {

    ClientView view;
    Connection con;
    
    public ClientController() {
        
        EventQueue.invokeLater(() -> {
            view = new ClientView();
            view.addListener(this);
            view.setVisible(true);
            
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        
        if (cmd.equals("Login")) {
            
            con = new Connection(view.getAddress(), view.getPort());
            con.start();
        }
    }
}
