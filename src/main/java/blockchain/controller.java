package blockchain;

import network.Connection;
import network.MessageHandler;
import network.Server;
import ui.Window;

import java.io.IOException;

public class controller {

    private Window window;
    public controller() {

    }
    public void connect(int port)  {
        try {
            Connection connection = new Connection(port, new MessageHandler() {
                @Override
                public void handleMessage(byte[] msg) {
                    System.out.println(msg);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void startServer(String nodeName) {
        Server server = new Server(7600);
        this.window.setServerStatus(server.getPort());
        server.start();
    }
    public void mine() {}

    public void setWindow(Window window) {
        this.window = window;
    }
}
