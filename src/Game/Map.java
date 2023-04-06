package Game;

import java.awt.Color;
import java.awt.Graphics;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import GFX.Camera;
import GFX.Renderer;
import Game.Tiles.GoldStash;
import Game.Tiles.Tile;
import Game.Tiles.Wall;
import Game.UI.BuildMenu;
import Util.MouseHandler;

public class Map {

    /**
     * Draws the map
     * 
     * @param graphics the graphics component
     */
    public void drawMap(Graphics graphics) {
        renderTiles(graphics);

        if (!LoadScreen.isInGame())
            return;

        testPlaceBuilding(graphics);
    }

    /**
     * Check if the block coordinate is in bounds
     * 
     * @param r
     * @param c
     * @return in bounds
     */
    public Boolean inBounds(int r, int c) {
        return r > 0 && r < Map.horizontalTiles && c > 0 && c < Map.verticalTiles;
    }

    private void testPlaceBuilding(Graphics graphics) {
        if (BuildMenu.pickedTile == null)
            return;

        int mouseScreenPosition[] = Renderer.getMouseScreenPosition();
        int mouseWorldPos[] = Camera.screenToWorld(mouseScreenPosition[0], mouseScreenPosition[1]);
        int tileX = (mouseWorldPos[0] / (Tile.tileHeight + Tile.tileGap)) * (Tile.tileHeight + Tile.tileGap);
        int tileY = (mouseWorldPos[1] / (Tile.tileHeight + Tile.tileGap)) * (Tile.tileHeight + Tile.tileGap);
        int tileStartPos[] = Camera.worldToScreen(tileX, tileY);
        int buildingWidth = (Tile.tileHeight + Tile.tileGap) * 2, buildingHeight = buildingWidth;
        if (tileStartPos[0] >= 0 && tileStartPos[0] + buildingWidth <= Renderer.getWindowWidth()
                && tileStartPos[1] >= 0
                && tileStartPos[1] + buildingHeight <= Renderer.getWindowHeight()) {

            int highlightX = 0, highlightY = 0;
            if (BuildMenu.pickedTile.getClass() == GoldStash.class) {
                highlightY = (Tile.tileHeight + Tile.tileGap) * 2;
                highlightX = (Tile.tileWidth + Tile.tileGap) * 2;
            } else if (BuildMenu.pickedTile.getClass() == Wall.class) {
                highlightY = (Tile.tileHeight + Tile.tileGap);
                highlightX = (Tile.tileWidth + Tile.tileGap);
            }

            // handle outline renderer
            graphics.setColor(Color.red);
            graphics.fillRect(tileStartPos[0], tileStartPos[1], highlightX, highlightY);

            int playerPosX = Renderer.getWindowHeight() / 2,
                    playerPosY = Renderer.getWindowHeight() / 2;

            // handle placing of new tiles
            if (MouseHandler.buttons[MouseHandler.MOUSE_LEFT].pressed
                    // mouse pressed
                    && !(mouseScreenPosition[0] >= BuildMenu.buildMenuSize.x
                            && mouseScreenPosition[0] <= BuildMenu.buildMenuSize.width
                            && mouseScreenPosition[1] >= BuildMenu.buildMenuSize.y
                            && mouseScreenPosition[1] <= BuildMenu.buildMenuSize.height)
                    // outside of building ui
                    && !(playerPosX >= tileX && playerPosX <= tileX + (Tile.tileWidth + Tile.tileGap) * 2
                            && playerPosY >= tileY && playerPosY <= tileY + (Tile.tileHeight + Tile.tileGap) * 2)) {
                // outside bounds of rectangle
                int iX = tileX / (Tile.tileHeight + Tile.tileGap);
                int iY = tileY / (Tile.tileWidth + Tile.tileGap);

                if (BuildMenu.pickedTile.getClass() == GoldStash.class) {
                    for (int c = 0; c <= 1; c++)
                        for (int r = 0; r <= 1; r++)
                            if(inBounds(iX + c, iY + r))
                                getTiles()[iY + c][iX + r] = new GoldStash((c == 0 && r == 0 ? true : false), tileX, tileY);
                } else if (BuildMenu.pickedTile.getClass() == Wall.class) {
                    if(inBounds(iX, iY))
                        getTiles()[iY][iX] = new Wall(tileX, tileY);
                }

                BuildMenu.pickedTile = null;
            }
        }
    }

    /**
     * Renders all of the tiles
     * 
     * @param graphics the graphics component
     */
    public void renderTiles(Graphics graphics) {
        Tile tiles[][] = getTiles();
        for (int c = 0; c < tiles.length; c++) {
            for (int r = 0; r < tiles[c].length; r++) {
                Tile curTile = tiles[c][r];
                if (curTile == null) {
                    graphics.setColor(new Color(105, 142, 65, 255));
                    int tileX = r * (Tile.tileWidth + Tile.tileGap);
                    int tileY = c * (Tile.tileHeight + Tile.tileGap);

                    int screenPos[] = Camera.worldToScreen(tileX, tileY);

                    graphics.fillRect(screenPos[0], screenPos[1], Tile.tileWidth, Tile.tileHeight);
                } else
                    curTile.drawTile(graphics);
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
