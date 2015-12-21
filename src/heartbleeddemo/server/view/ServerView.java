package heartbleeddemo.server.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author da
 */
public class ServerView extends JFrame implements Observer {

    private JPanel panel;
    private JLabel portLabel;
    private JTextField portTextField;
    private JButton startButton;
    private JButton stopButton;
    private JButton loadButton;
    private JLabel statusLabel;
    private JTextArea statusTextArea;
    
    private ActionListener listener;
            
    public ServerView(ActionListener listener) {
        super("Heartbleed Demo Server");
        this.listener = listener;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();
        
        pack();
        setLocationRelativeTo(null);
    }
    
    public int getPort() {
        try {
            return Integer.parseInt(portTextField.getText());
        } catch (Exception e) {
            return -1;
        }
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
        
        JPanel portPanel = new JPanel();
        portLabel = new JLabel("Port:");
        portPanel.add(portLabel);
        portTextField = new JTextField("16222");
        portPanel.add(portTextField);
        panel.add(portPanel);
        
        JPanel buttonPanel = new JPanel();
        startButton = new JButton("start Server");
        startButton.addActionListener(listener);
        buttonPanel.add(startButton);
        stopButton = new JButton("stop Server");
        stopButton.addActionListener(listener);
        buttonPanel.add(stopButton);
        loadButton = new JButton("load File");
        loadButton.addActionListener(listener);
        buttonPanel.add(loadButton);
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
