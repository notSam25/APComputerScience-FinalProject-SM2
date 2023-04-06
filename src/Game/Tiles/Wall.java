package Game.Tiles;

import java.awt.Color;
import java.awt.Graphics;

import GFX.Camera;

public class Wall extends Tile{
    public Wall(int tileX, int tileY) {
        this.worldPositionX = tileX;
        this.worldPositionY = tileY;
    }

    @Override
    public void drawBuildMenuTile(Graphics g, int x, int y) {
        // border
        g.setColor(Color.black);

        g.fillRoundRect(
                x,
                y,
                (tileGap + tileWidth * 2) / 2,
                (tileGap + tileHeight * 2) / 2,
                8,
                8);

        // inside
        g.setColor(Tile.tierOneColor);
        g.fillRoundRect(
                x + tileGap,
                y + tileGap,
                (tileWidth * 2) / 2 - (tileGap * 3) / 2,
                (tileHeight * 2) / 2 - (tileGap * 3) / 2,
                10,
                10);
    }

    @Override
    public void drawTile(Graphics g) {
        
        // border
        g.setColor(Color.black);
        int screenPosition[] = Camera.worldToScreen(worldPositionX, worldPositionY);
        g.fillRoundRect(
                screenPosition[0],
                screenPosition[1],
                tileGap + tileWidth,
                tileGap + tileHeight,
                8,
                8);

        // inside
        g.setColor(Tile.tierOneColor);
        g.fillRoundRect(
                screenPosition[0] + tileGap,
                screenPosition[1] + tileGap,
                tileWidth - tileGap,
                tileHeight - tileGap,
                10,
                10);
    }
}
