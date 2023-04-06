package Game.UI;

import java.awt.Graphics;
import java.util.ArrayList;

public class UI {

    public interface UIInterface {
        public void onRender(Graphics g);
        public void onUpdate();
    }

    public UI() {
        interfaces.add(new BuildMenu());
    }

    public void handlerRender(Graphics g) {
        for (UIInterface curInterface : interfaces) {
            curInterface.onRender(g);
        }
    }

    public void handleUpdate() {
        for (UIInterface curInterface : interfaces) {
            curInterface.onUpdate();
        }
    }

    private static ArrayList<UIInterface> interfaces = new ArrayList<UIInterface>();
}
