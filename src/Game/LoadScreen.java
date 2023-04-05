package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import GFX.Renderer;
import Util.KeyboardHandler;

public class LoadScreen {

    public static void startMenu(Graphics g) {
        g.setColor(Color.white);
        g.drawString("Press Enter To Play", Renderer.getWindowWidth() / 2 - 60, Renderer.getWindowHeight() / 2);

        if (KeyboardHandler.keys[KeyEvent.VK_ENTER].down)
            isInGame = true;
    }

    public static boolean isInGame() {
        return isInGame;
    }

    private static boolean isInGame = false;
}
