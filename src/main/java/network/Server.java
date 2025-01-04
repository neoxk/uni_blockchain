package network;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements MessageHandler{
    private int port;
    private boolean running;
    private ServerSocket serverSocket;
    private ArrayList<ClientHandler> clients;
    public Server(int tryPort) {
        this.port = tryPort;
    }

    public void start() {
        running = true;
        new Thread(() -> {
            serverSocket = createServerSocket();
            while (running) {
                try {
                    Socket newClient = serverSocket.accept();
                    clients.add(new ClientHandler(newClient, this));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();
    }


    private ServerSocket createServerSocket() {
        ServerSocket socket;

        while (true) {
            try {
                socket = new ServerSocket(this.port);
                return socket;
            } catch (BindException e) {
                this.port++;
                continue;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        running = false;
    }

    public int getPort() {
        return this.port;
    }

    public void handleMessage(byte[] message) {

    }
}
