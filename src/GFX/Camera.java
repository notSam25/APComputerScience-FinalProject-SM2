package GFX;

import java.awt.Color;
import java.awt.Graphics;

import Game.Map;
import Game.Tile;

public class Camera {

    public static enum CameraMovement {
        UP,
        DOWN,
        LEFT,
        RIGHT
    };

    public static void moveCamera(CameraMovement moveDirection, int amount) {
        System.out.printf("Cx: %d | Cy: %d\n", cameraPosition[0], cameraPosition[1]);
        switch (moveDirection) {
            case DOWN:
                if (canMoveOutsideBounds)
                    cameraPosition[1] += amount;
                else if (cameraPosition[1] < Map.getTiles()[1].length * Tile.tileHeight + Map.getTiles()[1].length * Tile.tileGap - Renderer.getWindowHeight())
                    cameraPosition[1] += amount;
                break;
            case LEFT:
                if (canMoveOutsideBounds)
                    cameraPosition[0] -= amount;
                else if (cameraPosition[0] > -Tile.tileGap)
                    cameraPosition[0] -= amount;
                break;
            case RIGHT:
                if (canMoveOutsideBounds)
                    cameraPosition[0] += amount;
                else if (cameraPosition[0] < Map.getTiles()[0].length * Tile.tileWidth + Map.getTiles()[0].length * Tile.tileGap - Renderer.getWindowWidth())
                    cameraPosition[0] += amount;
                break;
            case UP:
                if (canMoveOutsideBounds)
                    cameraPosition[1] -= amount;
                else if (cameraPosition[1] > -Tile.tileGap)
                    cameraPosition[1] -= amount;
                break;
            default:
                break;

        }
    }

    public static void toggleBoundary() {
        canMoveOutsideBounds = !canMoveOutsideBounds;
    }

    public static int[] getCameraPosition() {
        return cameraPosition;
    }

    private static int cameraPosition[] = { -2, -2 };
    private static boolean canMoveOutsideBounds = false;
}
