package Game;

import java.awt.Graphics;

public class Map {

    public void drawMap(Graphics graphics, int cameraPosition[]) {
        renderTiles(graphics, cameraPosition);
    }

    public void renderTiles(Graphics graphics, int cameraPosition[]) {
        Tile tiles[][] = getTiles();
        for (int c = 0; c < tiles.length; c++) {
            for (int r = 0; r < tiles[c].length; r++) {
                Tile curTile = tiles[c][r];
                int worldPosX = r * Tile.tileWidth + Tile.tileGap * r,
                        worldPosY = c * Tile.tileHeight + Tile.tileGap * c;
                graphics.fillRect(-cameraPosition[0] + worldPosX, -cameraPosition[1] + worldPosY, Tile.tileWidth,
                Tile.tileHeight);
            }
        }
    }

    public static Tile[][] getTiles() {
        return gameTiles;
    }

    private final static int horozontalTiles = 20, verticleTiles = 20;
    private static Tile gameTiles[][] = new Tile[verticleTiles][horozontalTiles];
}
