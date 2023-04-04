package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

import GFX.Camera;
import GFX.Renderer;
import Game.Map;
import Game.Tile;
import Util.KeyboardHandler;

public class Player implements Entity {

    public Player(int w_x, int w_y) {
        this.worldPosition = new int[] { w_x, w_y };
    }

    public static void handleCamera() {
        if (KeyboardHandler.keys[KeyboardHandler.GetKey('e')].pressed)
            Camera.toggleBoundary();

        if (KeyboardHandler.keys[KeyboardHandler.GetKey('w')].down) {
            int moveSpeed = Camera.cameraMoveSpeed * (KeyboardHandler.keys[KeyEvent.VK_SHIFT].down ? 3 : 1);
            if (Camera.boundaryActive())
                Camera.adjustCameraY(-moveSpeed);
            else if (Camera.getCameraY() > 0)
                Camera.adjustCameraY(-moveSpeed);
            ;
        }

        if (KeyboardHandler.keys[KeyboardHandler.GetKey('a')].down) {
            int moveSpeed = Camera.cameraMoveSpeed * (KeyboardHandler.keys[KeyEvent.VK_SHIFT].down ? 3 : 1);
            if (Camera.boundaryActive())
                Camera.adjustCameraX(-moveSpeed);
            else if (Camera.getCameraX() > 0)
                Camera.adjustCameraX(-moveSpeed);
        }

        if (KeyboardHandler.keys[KeyboardHandler.GetKey('s')].down) {
            int moveSpeed = Camera.cameraMoveSpeed * (KeyboardHandler.keys[KeyEvent.VK_SHIFT].down ? 3 : 1);
            if (Camera.boundaryActive())
                Camera.adjustCameraY(moveSpeed);
            else if (Camera.getCameraY() < (Map.getMapHeight() * Camera.getScreenScale()[1])
                    - Renderer.getWindowHeight())
                Camera.adjustCameraY(moveSpeed);
        }

        if (KeyboardHandler.keys[KeyboardHandler.GetKey('d')].down) {
            int moveSpeed = Camera.cameraMoveSpeed * (KeyboardHandler.keys[KeyEvent.VK_SHIFT].down ? 3 : 1);
            if (Camera.boundaryActive())
                Camera.adjustCameraX(moveSpeed);
            else if (Camera.getCameraX() < (Map.getMapWidth() * Camera.getScreenScale()[0]) - Renderer.getWindowWidth())
                Camera.adjustCameraX(moveSpeed);
        }

        if (!Camera.boundaryActive()) {
            if (Camera.getCameraX() < 0)
                Camera.adjustCameraX(0);
            else if (Camera.getCameraX() > (Map.getMapWidth() * Camera.getScreenScale()[0]) - Renderer.getWindowWidth())
                Camera.setCameraX((int) (Map.getMapWidth() * Camera.getScreenScale()[0]) - Renderer.getWindowWidth());

            if (Camera.getCameraX() < 0)
                Camera.setCameraY(0);
            else if (Camera.getCameraX() > (Map.getMapHeight() * Camera.getScreenScale()[1])
                    - Renderer.getWindowHeight())
                Camera.setCameraY((int) (Map.getMapHeight() * Camera.getScreenScale()[1]) - Renderer.getWindowHeight());
        }

    }

    public int[] getScreenPosition() {
        return Camera.worldToScreen(worldPosition[0], worldPosition[1]);
    }

    public int[] getWorldPosition() {
        return worldPosition;
    }

    public void handleMovement() {
        this.worldPosition = Camera.screenToWorld(Renderer.getWindowWidth() / 2, Renderer.getWindowHeight() / 2);
        screenPosition = Camera.worldToScreen(worldPosition[0], worldPosition[1]);

        handleCamera();
    }
    /* 
    public Point calculateCircumferencePoint(int theta, int centerX, int centerY, int radius) {
        double radians = Math.toRadians(theta);
        int x = centerX + (int) Math.round(Math.cos(radians) * radius);
        int y = centerY + (int) Math.round(Math.sin(radians) * radius);
        return new Point(x, y);
    }*/

    private int theta = 0;

    public void onRender(Graphics g) {
        /*  test
        theta++;
        theta = (theta >= 360) ? 0 : theta;

        Point center = calculateCircumferencePoint(theta, screenPosition[0] + (Tile.tileWidth + Tile.tileGap) / 2, screenPosition[1]+ (Tile.tileWidth + Tile.tileGap) / 2, 10);
        int radius = 10;
        int diameter = radius * 2;
        g.setColor(Color.blue);
        g.fillOval(center.x - radius, center.y - radius,
                diameter, diameter);
        g.drawString("Theta: " + theta, 10, Renderer.getWindowHeight() - 30);
        // test

        */

        int mouse_x = Renderer.getMouseScreenPosition()[0], mouse_y = Renderer.getMouseScreenPosition()[1];
        double circleX = mouse_x - screenPosition[0], circleY = mouse_y - screenPosition[1];
        
        circleX /= Math.sqrt((Math.pow(circleX, 2)) + Math.pow(circleY, 2));
        circleY /= Math.sqrt((Math.pow(circleX, 2)) + Math.pow(circleY, 2));

        circleX *= 15;
        circleY *= 15;

        g.setColor(Color.red);
        g.fillOval(screenPosition[0] + (int)circleX, screenPosition[1] + (int)circleY, 20, 20);

        g.setColor(Color.black);
        g.fillOval(screenPosition[0] - Tile.tileGap, screenPosition[1] - Tile.tileGap,
                Tile.tileWidth + (Tile.tileGap * 3), Tile.tileHeight + (Tile.tileGap * 3));

        g.setColor(skinColor);
        g.fillOval(screenPosition[0], screenPosition[1], Tile.tileWidth + Tile.tileGap, Tile.tileHeight + Tile.tileGap);
        // System.out.println("ScreenPos: " + screenPosition[0] + " | " +
        // screenPosition[1]);
        // System.out.println("WorldPos: " + worldPosition[0] + " | " + worldPosition
        // [1]);
    }

    private static Color skinColor = new Color(252, 200, 117, 255);

    private int[] screenPosition;
    private int[] worldPosition;
}
