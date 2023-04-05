package Game;

import java.awt.Graphics;
import java.util.ArrayList;

import Entities.Entity;
import Entities.Player;

public class GameHandler {

    public GameHandler() {
        this.entities.add(new Player(0, 0));
    }
    
    public void handleGame() {
        if(!LoadScreen.isInGame())
            return;

        // first index of the entity array will always be the player
        entities.get(0).handleMovement();

    }

    public void drawEntities(Graphics g) {
        for(Entity entity : entities) {
            entity.onRender(g);
        }
    }


    ArrayList<Entity> entities = new ArrayList<Entity>();
}

