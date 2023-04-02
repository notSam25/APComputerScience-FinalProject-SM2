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
                graphics.fillRect(-cameraPosition[0] + worldPosX, -cameraPosition[1] + worldPosY, tileWidth, tileHeight);
            }
        }
    }

    public static void moveCamera(CameraMovement moveDirection, int amount) {
        System.out.printf("Cx: %d | Cy: %d\n", cameraPosition[0], cameraPosition[1]);
        switch (moveDirection) {
            case DOWN:
                if (cameraPosition[1] < 500)
                cameraPosition[1]++;
                break;
                case LEFT:
                if (cameraPosition[0] > 0)
                cameraPosition[0]--;
                break;
                case RIGHT:
                if (cameraPosition[0] < 500)
                cameraPosition[0]++;
                break;
            case UP:
                if (cameraPosition[1] > 0)
                    cameraPosition[1]--;
                break;
            default:
                break;

        }
    }

    private static final int tileGap = 2, tileWidth = 20, tileHeight = 20;
    private static int cameraPosition[] = { 200, 200 };
}
