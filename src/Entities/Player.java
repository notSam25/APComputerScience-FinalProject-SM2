package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;

import Entities.Tools.Spear;
import Entities.Tools.Tool.TOOL_RARITY;
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

    private void drawPlayerHands(Graphics g, int mouse_x, int mouse_y) {
        double circleX = mouse_x - screenPosition[0], circleY = mouse_y - screenPosition[1];

        double distance =  Math.sqrt((Math.pow(circleX, 2)) + Math.pow(circleY, 2));
        circleX /= distance;
        circleY /= distance;
        
        circleX *= 15;
        circleY *= 15;
        
        double theta = Math.atan2(circleY, circleX);

        double distance2 = 20.0;
        double offset = (Math.PI / 180) * 45.0;
        
        double leftHandX = distance2 * Math.cos(theta + offset);
        double leftHandY = distance2 * Math.sin(theta + offset);

        double rightHandX = distance2 * Math.cos(theta - offset);
        double rightHandY = distance2 * Math.sin(theta - offset);
        
        // draw the tool
        this.tool.setLeftHand(new int[]{screenPosition[0] + (int)leftHandX, screenPosition[1] + (int)leftHandY});
        this.tool.setRightHand(new int[]{screenPosition[0] + (int)rightHandX, screenPosition[1] + (int)rightHandY});
        
        this.tool.onRender(g);

        // right hand
        g.setColor(outlineColor);
        g.fillOval((int)(screenPosition[0] + (int)leftHandX - (handSize * 1.6) / 2), (int)(screenPosition[1] + (int)leftHandY - (handSize * 1.6) / 2), (int)(handSize * 1.6), (int)(handSize * 1.6));
        
        g.setColor(skinColor);
        g.fillOval(screenPosition[0] + (int)leftHandX - handSize / 2, screenPosition[1] + (int)leftHandY - handSize / 2, handSize, handSize);

        // left hand
        g.setColor(outlineColor);
        g.fillOval((int)(screenPosition[0] + (int)rightHandX - (handSize * 1.6) / 2), (int)(screenPosition[1] + (int)rightHandY - (handSize * 1.6) / 2), (int)(handSize * 1.6), (int)(handSize * 1.6));
        
        g.setColor(skinColor);
        g.fillOval(screenPosition[0] + (int)rightHandX - handSize / 2, screenPosition[1] + (int)rightHandY - handSize / 2, handSize, handSize);
    }

    public void onRender(Graphics g) {
        drawPlayerHands(g, Renderer.getMouseScreenPosition()[0], Renderer.getMouseScreenPosition()[1]);

        // draw the player circles
        g.setColor(outlineColor);
        g.fillOval(screenPosition[0] - (Tile.tileWidth + (Tile.tileGap * 3)) / 2, screenPosition[1] - (Tile.tileWidth + (Tile.tileGap * 3)) / 2,
                Tile.tileWidth + (Tile.tileGap * 3), Tile.tileHeight + (Tile.tileGap * 3));

        g.setColor(skinColor);
        g.fillOval(screenPosition[0] - (Tile.tileWidth + Tile.tileGap) / 2, screenPosition[1] - (Tile.tileWidth + Tile.tileGap) / 2, Tile.tileWidth + Tile.tileGap, Tile.tileHeight + Tile.tileGap);
    }

    private Spear tool = new Spear(TOOL_RARITY.STONE);

    private static int handSize = 10;

    public static Color skinColor = new Color(252, 200, 117, 255), outlineColor = new Color(51, 51, 51, 255);

    private int[] screenPosition;
    private int[] worldPosition;
}
