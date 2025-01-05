package ui;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;

public class Window extends JFrame{
    private JPanel panel1;
    private JTextArea textArea1;
    private JTextField nodeNameField;
    private JButton connectButton;
    private JButton mineButton;
    private JTextField connectPortField;
    private JButton connectPortButton;
    private JLabel serverStatusLabel;
    private JTextPane ledgerPane;
    private JTextPane logPane;
    private UIListener listener;


    public Window(UIListener listener ) {
        this.listener = listener;
        setContentPane(panel1);
        setTitle("Blockchain");
        setSize(900,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        connectButton.addActionListener((l) -> {
            listener.onStartServer(nodeNameField.getText());
        });

        connectPortButton.addActionListener((l) -> {
            listener.onConnect(Integer.parseInt(connectPortField.getText()));
        });

        mineButton.addActionListener((l) -> {
            listener.onMine();
        });


        setVisible(true);
    }

    public void setServerStarted(int port) {
        if (port == -1) {
            serverStatusLabel.setText("Offline");
        } else {
            serverStatusLabel.setText("Online: PORT " + port );
            connectPortButton.setEnabled(true);
            connectButton.setEnabled(false);
        }
    }

    public void setConnectionEstablished(int port) {
        addLogMsg("Connected to port: " + port, Color.GREEN);
    }

    public void addLogMsg(String msg, Color color) {
        SwingUtil.appendToTextPane(logPane, msg + "\n", color);
    }

    public void clearLedger() {ledgerPane.setText("");}

    public void addLedgerMsg(String msg, Color color) {
        SwingUtil.appendToTextPane(ledgerPane, msg + "\n", color);
    }


}

class SwingUtil {
    public static void appendToTextPane(JTextPane tp, String msg, Color c) {
            StyleContext sc = StyleContext.getDefaultStyleContext();
            AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

            aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
            aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

            int len = tp.getDocument().getLength();
            tp.setCaretPosition(len);
            tp.setCharacterAttributes(aset, false);
            tp.replaceSelection(msg);
        }
    }
