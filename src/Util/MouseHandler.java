package Util;

import java.awt.*;
import java.awt.event.*;

public class MouseHandler {
    public static final int MOUSE_LEFT = 0, MOUSE_MIDDLE = 1, MOUSE_RIGHT = 2;

    public static final Button[] buttons = new Button[3];

    static {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button();
        }
    }

    private static Point mousePosition;

    public static Point getMouseScreenPosition() {
        return mousePosition;
    }

    public static void update() {
        for (Button button : buttons) {
            button.pressed = button.down && !button.last;
            button.last = button.down;
        }

        mousePosition = MouseInfo.getPointerInfo().getLocation();
    }

    public static class Button {
        public boolean down, pressed, pressedTick, last;
    }

    public static java.awt.event.MouseListener getListener() {
        return new java.awt.event.MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                MouseHandler.buttons[e.getButton() - 1].down = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                MouseHandler.buttons[e.getButton() - 1].down = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        };
    }
}