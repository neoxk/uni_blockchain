package network;

import java.io.IOException;
import java.io.InputStream;
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

//    public void read() {
//        new Thread(() -> {
//            while (connected) {
//                byte[] buff = new byte[2048];
//                try {
//                    int bytesRead = inStream.read(buff);
//                    if (bytesRead == -1) {
//                        connected = false;
//                        break;
//                    }
//
//                    byte[] msg = Arrays.copyOf(buff, bytesRead);
//                    msgHandler.handleMessage(msg);
//
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }).start();
//    }

    public void send(byte[] msg) throws IOException {
         this.outStream.write(msg);
         this.outStream.flush();
        System.out.println(msg + " sent");
    }
}
