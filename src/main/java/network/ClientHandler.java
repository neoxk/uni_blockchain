package network;

import blockchain.Blockchain;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class ClientHandler {
    private Server server;
    private Socket client;
    private boolean connected;
    private InputStream inStream;
    private OutputStream outStream;
    public ClientHandler(Socket client, Server server) {
        this.client = client;
        this.server = server;
        try {
            this.outStream = client.getOutputStream();
            this.inStream = client.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.connected = true;

        read();
    }

    public void read() {
        new Thread(() -> {
            while (connected) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(this.inStream);
                    Blockchain blockchain = (Blockchain) ois.readObject();
                    this.server.handleMessage(blockchain);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
