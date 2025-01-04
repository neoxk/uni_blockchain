package ui;

import javax.swing.*;

public class Window extends JFrame{
    private JPanel panel1;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextField nodeNameField;
    private JButton connectButton;
    private JButton mineButton;
    private JTextField connectPortField;
    private JButton connectPortButton;
    private JLabel serverStatusLabel;

    private blockchain.controller controller;

    public Window(blockchain.controller controller ) {
        this.controller = controller;
        setContentPane(panel1);
        setTitle("Blockchain");
        setSize(900,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        connectButton.addActionListener((l) -> {
            controller.startServer(nodeNameField.getText());
        });

        connectPortButton.addActionListener((l) -> {
            controller.connect(Integer.parseInt(connectPortField.getText()));
        });


        setVisible(true);
    }

    public void setServerStatus(int port) {
        if (port == -1) {
            serverStatusLabel.setText("Offline");
        } else {
            serverStatusLabel.setText("Online: PORT " + port );
        }
    }


}
