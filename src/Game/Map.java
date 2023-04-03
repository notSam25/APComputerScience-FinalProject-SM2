package Game;

import java.awt.Graphics;

import GFX.Camera;

public class Map {

    public void drawMap(Graphics graphics) {
        renderTiles(graphics);
    }

    public void renderTiles(Graphics graphics) {
        Tile tiles[][] = getTiles();
        for (int c = 0; c < tiles.length; c++) {
            for (int r = 0; r < tiles[c].length; r++) {
                int tileX = r * (Tile.tileWidth + Tile.tileGap);
                int tileY = c * (Tile.tileHeight + Tile.tileGap);

                int screenPos[] = Camera.worldToScreen(tileX, tileY);

                graphics.drawRect(screenPos[0], screenPos[1], Tile.tileWidth, Tile.tileHeight);
            }
        }
    }

    public static Tile[][] getTiles() {
        return gameTiles;
    }

    public static int getMapWidth() {
        return mapWidth;
    }

    public static int getMapHeight() {
        return mapHeight;
    }

    private final static int horizontalTiles = 150, verticalTiles = 150,
            mapWidth = horizontalTiles * (Tile.tileWidth + Tile.tileGap),
            mapHeight = verticalTiles * (Tile.tileHeight + Tile.tileGap);
    private static Tile gameTiles[][] = new Tile[verticalTiles][horizontalTiles];
}
