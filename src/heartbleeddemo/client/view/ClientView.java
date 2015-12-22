package heartbleeddemo.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author da
 */
public class ClientView extends JFrame implements Observer {

    private JPanel panel;
    private JLabel addressLabel;
    private JLabel portLabel;
    private JTextField addressTextField;
    private JTextField portTextField;
    private JTextField messageTextField;
    private JButton connectButton;
    private JButton sendButton;
    private JButton saveButton;
    private JLabel statusLabel;
    private JTextArea statusTextArea;
    
    private ActionListener listener;
            
    public ClientView(ActionListener listener) {
        super("Heartbleed Demo Client");
        this.listener = listener;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();
        
        pack();
        setLocationRelativeTo(null);
    }
    
    public String getAddress() {
        return addressTextField.getText();
    }
    
    public int getPort() {
        try {
            return Integer.parseInt(portTextField.getText());
        } catch (Exception e) {
            return -1;
        }
    }
    
    public File getFile() {
        JFileChooser fc = new JFileChooser();
        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile();
        }
        return null;
    }
    
    public String getMessage() {
        return messageTextField.getText();
    }
    
    public void println(String msg) {
        statusTextArea.append(msg + "\n");
    }

    @Override
    public void update(Observable o, Object arg) {
        println(arg.toString());
    }
    
    private void initUI() {
        
        panel = new JPanel();
        panel.setBackground(Color.black);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(25, 25, 25, 25));
        
        JPanel addressPanel = new JPanel();
        addressLabel = new JLabel("IP:");
        addressPanel.add(addressLabel);
        addressTextField = new JTextField("localhost");
        addressTextField.setColumns(10);
        addressTextField.setHorizontalAlignment(JTextField.CENTER);
        addressPanel.add(addressTextField);
        
        portLabel = new JLabel("Port:");
        addressPanel.add(portLabel);
        portTextField = new JTextField("16222");
        portTextField.setColumns(5);
        portTextField.setHorizontalAlignment(JTextField.CENTER);
        addressPanel.add(portTextField);
        panel.add(addressPanel);
        
        JPanel buttonPanel = new JPanel();
        connectButton = new JButton("Connect");
        connectButton.addActionListener(listener);
        messageTextField = new JTextField("hallo");
        messageTextField.setColumns(10);
        messageTextField.setHorizontalAlignment(JTextField.CENTER);
        sendButton = new JButton("Send");
        sendButton.addActionListener(listener);
        saveButton = new JButton("Save");
        saveButton.addActionListener(listener);
        buttonPanel.add(connectButton);
        buttonPanel.add(messageTextField);
        buttonPanel.add(sendButton);
        buttonPanel.add(saveButton);
        panel.add(buttonPanel);
        
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusLabel = new JLabel("Status:");
        statusPanel.add(statusLabel, BorderLayout.NORTH);
        statusTextArea = new JTextArea(15, 40);
        statusTextArea.setEditable(false);
        statusTextArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        statusTextArea.setBackground(new Color(0xCCCCCC));
        statusTextArea.setMargin(new Insets(15, 15, 15, 15));
        statusPanel.add(statusTextArea, BorderLayout.CENTER);
        panel.add(statusPanel);
        
        add(panel);
    }
}
