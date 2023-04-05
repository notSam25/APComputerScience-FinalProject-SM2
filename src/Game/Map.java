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
        renderTiles(graphics);
        
        if(!LoadScreen.isInGame())
            return;
        
        testPlaceBuilding(graphics);
    }

    private void testPlaceBuilding(Graphics graphics) {
        int mouseScreenPosition[] = Renderer.getMouseScreenPosition();
        int mouseWorldPos[] = Camera.screenToWorld(mouseScreenPosition[0], mouseScreenPosition[1]);
        int tileX = (mouseWorldPos[0] / (Tile.tileHeight + Tile.tileGap)) * (Tile.tileHeight + Tile.tileGap);
        int tileY = (mouseWorldPos[1] / (Tile.tileHeight + Tile.tileGap)) * (Tile.tileHeight + Tile.tileGap);
        int tileStartPos[] = Camera.worldToScreen(tileX, tileY);

        int buildingWidth = (Tile.tileHeight + Tile.tileGap) * 2, buildingHeight = buildingWidth;
        if (tileStartPos[0] >= 0 && tileStartPos[0] + buildingWidth <= Renderer.getWindowWidth()
                && tileStartPos[1] >= 0
                && tileStartPos[1] + buildingHeight <= Renderer.getWindowHeight()) {

            graphics.setColor(Color.red);
            graphics.fillRect(tileStartPos[0], tileStartPos[1], (Tile.tileHeight + Tile.tileGap) * 2,
                    (Tile.tileHeight + Tile.tileGap) * 2);
        }
    }

    /**
     * Renders all of the tiles
     * 
     * @param graphics the graphics component
     */
    public void renderTiles(Graphics graphics) {
        graphics.setColor(new Color(105,142,65,255));
        Tile tiles[][] = getTiles();
        for (int c = 0; c < tiles.length; c++) {
            for (int r = 0; r < tiles[c].length; r++) {
                int tileX = r * (Tile.tileWidth + Tile.tileGap);
                int tileY = c * (Tile.tileHeight + Tile.tileGap);

                int screenPos[] = Camera.worldToScreen(tileX, tileY);

                graphics.fillRect(screenPos[0], screenPos[1], Tile.tileWidth, Tile.tileHeight);
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
