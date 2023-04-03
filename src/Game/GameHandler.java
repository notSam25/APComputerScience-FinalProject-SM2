package Game;

import java.util.ArrayList;

import Entities.Entity;
import Entities.Player;

public class GameHandler {

    public GameHandler() {
        this.entities.add(new Player(52, 52));
    }
    public void handleGame() {
        // first index of the entity array will always be the player
        entities.get(0).handleMovement();
        
    }



    ArrayList<Entity> entities = new ArrayList<Entity>();
}

