package Game;

import java.awt.Color;
import java.awt.Graphics;

import GFX.Camera;
import GFX.Renderer;

public class Map {

    /**
     * Draws the map
     * 
     * @param graphics the graphics component
     */
    public void drawMap(Graphics graphics) {
        /* Example box render
        int mousePosition[] = Renderer.getMouseScreenPosition();
        int tile[] = Camera.screenToWorld(mousePosition[0], mousePosition[1]);
        int tileX = (tile[0] / (Tile.tileHeight + Tile.tileGap)) * (Tile.tileHeight + Tile.tileGap);
        int tileY = (tile[1] / (Tile.tileHeight + Tile.tileGap)) * (Tile.tileHeight + Tile.tileGap);
        int sol[] = Camera.worldToScreen(tileX, tileY);

        graphics.setColor(Color.red);

        graphics.fillRect(sol[0], sol[1], 50, 50);
        */
        
        renderTiles(graphics);
    }
    /**
     * Renders all of the tiles
     * 
     * @param graphics the graphics component
     */
    public void renderTiles(Graphics graphics) {
        graphics.setColor(Color.black);
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
