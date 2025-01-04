import blockchain.controller;
import ui.Window;

public class Main {
    public static int PORT = 76000;
    public static blockchain.controller controller;

    public static void main(String[] args) {
        Main.controller = new controller();
        Window window = new Window(Main.controller);
        Main.controller.setWindow(window);
    }



}
