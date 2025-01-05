import ui.Logger;
import ui.Window;

public class Main {
   public static void main(String[] args) {
       App app = new App();
       Window window = new Window(app);
       Logger.window = window;
       app.setWindow(window);
   }
}
