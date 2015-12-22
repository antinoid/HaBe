package heartbleeddemo.client.controller;

import heartbleeddemo.client.model.Connection;
import heartbleeddemo.client.view.ClientView;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * @author da
 */
public class ClientController implements ActionListener {

    ClientView view;
    Connection con;
    
    public ClientController() {
        
        EventQueue.invokeLater(() -> {
            view = new ClientView(this);
            view.setVisible(true);
            
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        
        if (cmd.equals("Connect")) {
            con = new Connection(view.getAddress(), view.getPort(), view);
            con.start();
        } else if (cmd.equals("Send")) {
            con.send(view.getMessage().getBytes());
        } else if (cmd.equals("Save")) {
            File file = view.getFile();
            FileOutputStream out = null;
            if (file != null) {
                try {
                    out = new FileOutputStream(file);
                    out.write(con.getData());
                    out.flush();
                } catch (Exception ex) {
                } finally {
                    try {
                        if(out != null) { out.close(); }
                    } catch (Exception ex) {
                        System.out.println(ex);
                        //view.println(ex.toString());
                    }
                }
            }
        }
    }
}
