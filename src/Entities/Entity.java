package Entities;

import java.awt.Graphics;

public interface Entity {
    public void onRender(Graphics g);
    public void handleMovement();

    int worldPosition[] = new int[]{0, 0};
}
