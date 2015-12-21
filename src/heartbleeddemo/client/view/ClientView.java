package heartbleeddemo.client.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author da
 */
public class ClientView extends JFrame {

    //private static final int FRAME_WIDTH = 500;
    //private static final int FRAME_HEIGHT = 300;

    JPanel loginPanel;
    JPanel mainPanel;
    JLabel addressLabel;
    JLabel portLabel;
    JTextField addressTextField;
    JTextField portTextField;
    JButton loginButton;
    
    public ClientView() {
        super("Heartbleed Demo Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createLoginPanel();
        
        pack();
        setLocationRelativeTo(null);
    }
    
    public void addListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }
    
    public String getAddress() {
        return addressTextField.getText();
    }
    
    public int getPort() {
        return Integer.parseInt(portTextField.getText());
    }
    
    private void createLoginPanel() {
        loginPanel = new JPanel();
        addressLabel = new JLabel("Address:");
        portLabel = new JLabel("Port:");
        addressTextField = new JTextField("localhost");
        portTextField = new JTextField("16222");
        loginButton = new JButton("Login");
        
        loginPanel.add(addressLabel);
        loginPanel.add(addressTextField);
        loginPanel.add(portLabel);
        loginPanel.add(portTextField);
        loginPanel.add(loginButton);
        
        add(loginPanel);
    }
}
