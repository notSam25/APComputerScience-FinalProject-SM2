package Entities;

import java.awt.event.KeyEvent;

import GFX.Camera;
import GFX.Renderer;
import Game.Map;
import Util.KeyboardHandler;

public class Player extends Entity {
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

    @Override
    public void handleMovement() {
        handleCamera();
    }
}
