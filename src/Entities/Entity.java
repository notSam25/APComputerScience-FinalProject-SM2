package Entities;

import java.awt.Graphics;

import Game.Map;
import Game.Tiles.Tile;

public class Entity {
    public void onRender(Graphics g) {
    };

    public void handleMovement() {
    };

    public static boolean isInsideOccupiedTile(int x, int y) {
        Tile[][] tiles = Map.getTiles();

        int curTileX = (x / (Tile.tileWidth + Tile.tileGap)) * (Tile.tileWidth + Tile.tileGap);
        int curTileY = (y / (Tile.tileHeight + Tile.tileGap)) * (Tile.tileHeight + Tile.tileGap);
        int iX = curTileX / (Tile.tileHeight + Tile.tileGap);
        int iY = curTileY / (Tile.tileWidth + Tile.tileGap);
        Tile curTile = tiles[iY][iX];
        
        if (curTile != null)
            return true;
        else
            return false;
    }

    protected static int[] screenPosition = { 0, 0 };
    protected static int[] worldPosition = { 0, 0 };
}
