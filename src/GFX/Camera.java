package GFX;

import Game.Map;

public class Camera {

    public static enum CameraMovement {
        UP,
        DOWN,
        LEFT,
        RIGHT
    };

    public static void toggleBoundary() {
        canMoveOutsideBounds = !canMoveOutsideBounds;
    }

    public static double[] getScreenScale() {
        return new double[]{
            maxCameraWidth / ((cameraOffset[0] + maxCameraWidth) - cameraOffset[0]),
            maxCameraHeight / ((cameraOffset[1] + maxCameraHeight) - cameraOffset[1])
        };
    }

    public static int[] worldToScreen(int x, int y) {
        double scaleX = getScreenScale()[0];
        double scaleY = getScreenScale()[1];

        int viewportX = (x - cameraOffset[0]) * (int)scaleX;
        int viewportY = (y - cameraOffset[1]) * (int)scaleY;
        return new int[] {viewportX, viewportY};
    }

    public static void moveCamera(CameraMovement moveDirection, int amount) {
        switch (moveDirection) {
            case DOWN:
                if (canMoveOutsideBounds)
                    cameraOffset[1] += amount;
                else if (cameraOffset[1] < (Map.getMapHeight() * getScreenScale()[1]) - Renderer.getWindowHeight())
                    cameraOffset[1] += amount;
                break;
            case LEFT:
                if (canMoveOutsideBounds)
                    cameraOffset[0] -= amount;
                else if (cameraOffset[0] > 0)
                    cameraOffset[0] -= amount;
                break;
            case RIGHT:
                if (canMoveOutsideBounds)
                    cameraOffset[0] += amount;
                else if (cameraOffset[0] < (Map.getMapWidth() * getScreenScale()[0]) - Renderer.getWindowWidth())
                    cameraOffset[0] += amount;
                break;
            case UP:
                if (canMoveOutsideBounds)
                    cameraOffset[1] -= amount;
                else if (cameraOffset[1] > 0)
                    cameraOffset[1] -= amount;
                break;
            default:
                break;

        }
    }

    public static int[] getCameraOffset() {
        return cameraOffset;
    }

    private static int cameraOffset[] = {0, 0}, maxCameraWidth = Renderer.getWindowWidth(), maxCameraHeight = Renderer.getWindowHeight();
    private static boolean canMoveOutsideBounds = false;
}
