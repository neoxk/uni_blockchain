package network;

import blockchain.Blockchain;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class Connection {
    private Socket socket;
    private InputStream inStream;
    private OutputStream outStream;
    private MessageHandler msgHandler;
    private boolean connected;
    public Connection(int port, MessageHandler msgHandler) throws IOException {
        socket = new Socket("localhost", port);
//        this.inStream = socket.getInputStream();
        this.outStream = socket.getOutputStream();
        this.msgHandler = msgHandler;
        this.connected = true;
    }



    public void send(Blockchain msg) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(this.outStream);
         oos.writeObject(msg);
         oos.flush();
    }
}
