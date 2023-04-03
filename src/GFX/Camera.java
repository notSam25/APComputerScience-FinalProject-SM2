package GFX;

import java.awt.event.KeyEvent;

import Game.Map;
import Util.KeyboardHandler;

public class Camera {
    public static void toggleBoundary() {
        canMoveOutsideBounds = !canMoveOutsideBounds;
    }

    public static double[] getScreenScale() {
        return new double[] {
                maxCameraWidth / ((cameraOffset[0] + maxCameraWidth) - cameraOffset[0]),
                maxCameraHeight / ((cameraOffset[1] + maxCameraHeight) - cameraOffset[1])
        };
    }

    public static int[] screenToWorld(int x, int y) {
        double scaleX = getScreenScale()[0];
        double scaleY = getScreenScale()[1];

        int worldX = (int)(x / scaleX) + cameraOffset[0];
        int worldY = (int)(y / scaleY) + cameraOffset[1];
        //x / scale + cameraOffset= wx

        return new int[]{worldX, worldY};
    }

    public static int[] worldToScreen(int x, int y) {
        double scaleX = getScreenScale()[0];
        double scaleY = getScreenScale()[1];

        int viewportX = (x - cameraOffset[0]) * (int) scaleX;
        int viewportY = (y - cameraOffset[1]) * (int) scaleY;
        return new int[] { viewportX, viewportY };
    }

    public static void handleCamera() {
        if (KeyboardHandler.keys[KeyboardHandler.GetKey('e')].pressed) {
            canMoveOutsideBounds = !canMoveOutsideBounds;
        }
        
        if (KeyboardHandler.keys[KeyboardHandler.GetKey('w')].down) {
            int moveSpeed = cameraMoveSpeed * (KeyboardHandler.keys[KeyEvent.VK_SHIFT].down ? 3 : 1);
            if (canMoveOutsideBounds)
                cameraOffset[1] -= moveSpeed;
            else if (cameraOffset[1] > 0)
                cameraOffset[1] -= moveSpeed;
        }

        if (KeyboardHandler.keys[KeyboardHandler.GetKey('a')].down) {
            int moveSpeed = cameraMoveSpeed * (KeyboardHandler.keys[KeyEvent.VK_SHIFT].down ? 3 : 1);
            if (canMoveOutsideBounds)
                cameraOffset[0] -= moveSpeed;
            else if (cameraOffset[0] > 0)
                cameraOffset[0] -= moveSpeed;
        }

        if (KeyboardHandler.keys[KeyboardHandler.GetKey('s')].down) {
            int moveSpeed = cameraMoveSpeed * (KeyboardHandler.keys[KeyEvent.VK_SHIFT].down ? 3 : 1);
            if (canMoveOutsideBounds)
                cameraOffset[1] += moveSpeed;
            else if (cameraOffset[1] < (Map.getMapHeight() * getScreenScale()[1]) - Renderer.getWindowHeight())
                cameraOffset[1] += moveSpeed;
        }

        if (KeyboardHandler.keys[KeyboardHandler.GetKey('d')].down) {
            int moveSpeed = cameraMoveSpeed * (KeyboardHandler.keys[KeyEvent.VK_SHIFT].down ? 3 : 1);
            if (canMoveOutsideBounds)
                cameraOffset[0] += moveSpeed;
            else if (cameraOffset[0] < (Map.getMapWidth() * getScreenScale()[0]) - Renderer.getWindowWidth())
                cameraOffset[0] += moveSpeed;
        }

        if (!canMoveOutsideBounds) {
            if (cameraOffset[0] < 0)
                cameraOffset[0] = 0;
            else if (cameraOffset[0] > (Map.getMapWidth() * getScreenScale()[0]) - Renderer.getWindowWidth())
                cameraOffset[0] = (int) (Map.getMapWidth() * getScreenScale()[0]) - Renderer.getWindowWidth();

            if (cameraOffset[1] < 0)
                cameraOffset[1] = 0;
            else if (cameraOffset[1] > (Map.getMapHeight() * getScreenScale()[1]) - Renderer.getWindowHeight())
                cameraOffset[1] = (int) (Map.getMapHeight() * getScreenScale()[1]) - Renderer.getWindowHeight();
        }

    }

    /*
     * cameraOffset: the world offset for the viewport
     * maxCameraWidth: the maximum width for the camera
     * maxCameraHeight: the maximum height for the camera
     * canMoveOutsideBounds: a boolean dictating wether the camera can escape the
     * bounds of the map
     * cameraMoveSpeed: the speed which the camera will move per render interval
     */
    private static int cameraOffset[] = { 0, 0 }, maxCameraWidth = Renderer.getWindowWidth(),
            maxCameraHeight = Renderer.getWindowHeight(), cameraMoveSpeed = 1;
    private static boolean canMoveOutsideBounds = false;
}
