package GFX;

public class Camera {
    public static double[] getScreenScale() {
        return new double[] {
                maxCameraWidth / ((cameraOffset[0] + maxCameraWidth) - cameraOffset[0]),
                maxCameraHeight / ((cameraOffset[1] + maxCameraHeight) - cameraOffset[1])
        };
    }

    public static int[] screenToWorld(int x, int y) {
        double scaleX = getScreenScale()[0];
        double scaleY = getScreenScale()[1];

        int worldX = (int) ((x / scaleX) + cameraOffset[0]);
        int worldY = (int) ((y / scaleY) + cameraOffset[1]);
        // x / scale + cameraOffset= wx

        return new int[] { worldX, worldY };
    }

    public static int[] worldToScreen(int x, int y) {
        double scaleX = getScreenScale()[0];
        double scaleY = getScreenScale()[1];

        int viewportX = (x - cameraOffset[0]) * (int) scaleX;
        int viewportY = (y - cameraOffset[1]) * (int) scaleY;
        return new int[] { viewportX, viewportY };
    }

    public static int getCameraX() {
        return cameraOffset[0];
    }

    public static int getCameraY() {
        return cameraOffset[1];
    }

    public static void adjustCameraX(int offset) {
        cameraOffset[0] += offset;
    }

    public static void adjustCameraY(int offset) {
        cameraOffset[1] += offset;
    }

    public static void setCameraX(int value) {
        cameraOffset[0] = value;
    }

    public static void setCameraY(int value) {
        cameraOffset[1] = value;
    }

    public static int[] getCameraOffset() {
        return cameraOffset;
    }
    
    /*
     * cameraOffset: the world offset for the viewport
     * maxCameraWidth: the maximum width for the camera
     * maxCameraHeight: the maximum height for the camera
     * cameraMoveSpeed: the speed which the camera will move per render interval
     */
    private static int cameraOffset[] = { 0, 0 }, maxCameraWidth = Renderer.getWindowWidth(),
            maxCameraHeight = Renderer.getWindowHeight();
    public static int cameraMoveSpeed = 1, cameraSprintSpeed = 2;
}
