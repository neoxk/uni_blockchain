package network;

import java.io.IOException;
import java.io.InputStream;
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
        System.out.println("New connection: " + client.getPort() + "->" + server.getPort());

        read();
    }

    public void read() {
        new Thread(() -> {
            while (connected) {
                try {
                    byte[] buff = new byte[2048];
                    int bytesRead = this.inStream.read(buff);
                    if (bytesRead == -1) {
                        connected = false;
                        break;
                    }

                    byte[] msg = Arrays.copyOf(buff, bytesRead);
                    this.server.handleMessage(msg);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public void send(byte[] msg) {
        try {
            outStream.write(msg);
            outStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
