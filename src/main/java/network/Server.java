package network;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements MessageHandler{
    @Getter
    private int port;
    private boolean running;
    private ServerSocket serverSocket;
    private ArrayList<ClientHandler> clients = new ArrayList<>();
    private ArrayList<Connection> connections = new ArrayList<>();

    @Setter
    private MessageHandler msgHander;
    public Server(int tryPort) {
        this.port = tryPort;
        serverSocket = createServerSocket();
    }

    public void start() {
        running = true;
        new Thread(() -> {
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

    public boolean connectTo(int port) {
       try {
            Connection connection = new Connection(port, new MessageHandler() {
                @Override
                public void handleMessage(byte[] msg) {
                    System.out.println(msg);
                }
            });

            connections.add(connection);
            return true;

       } catch (IOException e) {
           return false;
       }
    }


    private ServerSocket createServerSocket() {
        ServerSocket socket;

        while (true) {
            try {
                socket = new ServerSocket(this.port);
                System.out.println(this.port);
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


    public void handleMessage(byte[] message) {
        System.out.println(message + "received");
    }
}
