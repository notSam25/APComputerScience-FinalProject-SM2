package Game.Tiles;

import java.awt.Color;
import java.awt.Graphics;

public class Tile {

    public void drawTile(Graphics g) {

    }

    public void drawBuildMenuTile(Graphics g, int x, int y) {

    }

    public int getWorldPositionX() {
        return worldPositionX;
    }
    public int getWorldPositionY() {
        return worldPositionY;
    }

    public static final int tileGap = 2, tileWidth = 28, tileHeight = 28;
    protected int worldPositionX = -1, worldPositionY = -1;
    protected boolean isTopLeft = false;
    protected final static Color tierOneColor = new Color(127,86,57,255);
}
