package Entities;

import java.awt.Graphics;

public interface Entity {
    public void onRender(Graphics g);
    public void handleMovement();

    int worldPosition[] = {0, 0};
}
