package Game.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import GFX.Renderer;
import Game.Tiles.GoldStash;
import Game.Tiles.Tile;
import Util.MouseHandler;

public class BuildMenu implements UI.UIInterface {
    public BuildMenu() {
        addBuilding(new GoldStash(false, 0, 0));
        addBuilding(new GoldStash(false, 0, 0));
        addBuilding(new GoldStash(false, 0, 0));
        addBuilding(new GoldStash(false, 0, 0));
        addBuilding(new GoldStash(false, 0, 0));
        addBuilding(new GoldStash(false, 0, 0));
    }

    private void addBuilding(Tile building) {
        buildings.add(building);
        buildingSize.add(new Rectangle());
    }

    public void onUpdate() {
        if (pickedTile == null && MouseHandler.buttons[MouseHandler.MOUSE_LEFT].pressed) {
            
            int mousePos[] = Renderer.getMouseScreenPosition();
            for (int i = 0; i < buildingSize.size(); i++) {
                Rectangle rect = buildingSize.get(i);
                
                if (mousePos[0] >= rect.x && mousePos[0] <= rect.x + rect.width &&
                mousePos[1] >= rect.y && mousePos[1] <= rect.y + rect.height) {
                            // if there is no tile currently to be placed and we press on a tile
                    pickedTile = buildings.get(i);
                }
            }
        }
    }

    public void onRender(Graphics g) {

        startPos[0] = Renderer.getWindowWidth() * 3 / 10;
        startPos[1] = Renderer.getWindowHeight() * 9 / 10;
        buildMenuSize.x = startPos[0];
        buildMenuSize.y = startPos[1];
        buildMenuSize.width = startPos[0] + Renderer.getWindowWidth() * 4 / 10;
        buildMenuSize.height = startPos[1] + 40;

        g.setColor(new Color(0, 0, 0, 125));
        g.fillRoundRect(startPos[0], startPos[1], Renderer.getWindowWidth() * 4 / 10, 40, 15, 15);

        for (int i = 0; i < buildings.size(); i++) {
            Tile building = buildings.get(i);
            if (building == null)
                continue;

            int bX = (startPos[0] + 30) + (50 * i);
            building.drawBuildMenuTile(g, bX, startPos[1] + 5);
            buildingSize.get(i).x = bX;
            buildingSize.get(i).y = startPos[1] + 5;

            buildingSize.get(i).width = (Tile.tileGap + Tile.tileWidth * 2) / 2;
            buildingSize.get(i).height = (Tile.tileGap + Tile.tileHeight * 2) / 2;
        }

    }

    private static ArrayList<Tile> buildings = new ArrayList<Tile>();
    private static ArrayList<Rectangle> buildingSize = new ArrayList<Rectangle>();
    public static Rectangle buildMenuSize = new Rectangle();
    public static Object pickedTile = null;
    private int[] startPos = new int[2];
}
