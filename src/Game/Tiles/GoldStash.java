package Game.Tiles;

import java.awt.Color;
import java.awt.Graphics;

import GFX.Camera;

public class GoldStash extends Tile {
    public GoldStash(boolean isTopLeft, int tileX, int tileY) {
        this.isTopLeft = isTopLeft;
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
        if (!this.isTopLeft)
            return;

        // border
        g.setColor(Color.black);
        int screenPosition[] = Camera.worldToScreen(worldPositionX, worldPositionY);
        g.fillRoundRect(
                screenPosition[0],
                screenPosition[1],
                tileGap + tileWidth * 2,
                tileGap + tileHeight * 2,
                8,
                8);

        // inside
        g.setColor(Tile.tierOneColor);
        g.fillRoundRect(
                screenPosition[0] + (tileGap * 2),
                screenPosition[1] + (tileGap * 2),
                (tileWidth * 2) - (tileGap * 3),
                (tileHeight * 2) - (tileGap * 3),
                10,
                10);
    }
}
