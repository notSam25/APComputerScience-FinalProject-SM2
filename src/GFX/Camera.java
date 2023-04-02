package GFX;

import java.awt.Color;
import java.awt.Graphics;

import Game.Map;

public class Camera {

    public enum CameraMovement {
        UP,
        DOWN,
        LEFT,
        RIGHT
    };

    public void render(Graphics graphics, Map mapToDraw) {
        graphics.setColor(Color.black);

        int tiles[][] = mapToDraw.tiles;
        for (int c = 0; c < tiles.length; c++) {
            for (int r = 0; r < tiles[c].length; r++) {
                int worldPosX = r * tileWidth + tileGap * r,
                        worldPosY = c * tileHeight + tileGap * c;
                graphics.fillRect(-cameraPosition[0] + worldPosX, -cameraPosition[1] + worldPosY, tileWidth,
                        tileHeight);
            }
        }
    }

    public static void moveCamera(CameraMovement moveDirection, int amount) {
        System.out.printf("Cx: %d | Cy: %d\n", cameraPosition[0], cameraPosition[1]);
        switch (moveDirection) {
            case DOWN:
                if (canMoveOutsideBounds)
                    cameraPosition[1] += amount;
                else if (cameraPosition[1] < Map.tiles[1].length * tileWidth - 3 * (tileGap * Map.tiles[1].length))
                    cameraPosition[1] += amount;
                break;
            case LEFT:
                if (canMoveOutsideBounds)
                    cameraPosition[0] -= amount;
                else if (cameraPosition[0] > 0)
                    cameraPosition[0] -= amount;
                break;
            case RIGHT:
                if (canMoveOutsideBounds)
                    cameraPosition[0] += amount;
                else if (cameraPosition[0] < Map.tiles[0].length * tileWidth - 3 * (tileGap * Map.tiles[0].length))
                    cameraPosition[0] += amount;
                break;
            case UP:
                if (canMoveOutsideBounds)
                    cameraPosition[1] -= amount;
                else if (cameraPosition[1] > 0)
                    cameraPosition[1] -= amount;
                break;
            default:
                break;

        }
    }

    public static void toggleBoundary() {
        canMoveOutsideBounds = !canMoveOutsideBounds;
    }

    private static final int tileGap = 2, tileWidth = 50, tileHeight = 50;
    private static int cameraPosition[] = { 200, 200 };
    private static boolean canMoveOutsideBounds = false;
}
