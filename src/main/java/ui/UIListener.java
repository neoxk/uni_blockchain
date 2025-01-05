package ui;

public interface UIListener {
    void onStartServer(String nodeName);
    void onConnect(int port);
    void onMine();
}
