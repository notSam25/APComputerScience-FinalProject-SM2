package Entities.Tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import Entities.Player;

public class Spear extends Tool {

    public Spear(TOOL_RARITY toolRarity) {
        super(toolRarity);
    }

    @Override
    public
    void onRender(Graphics graphics) {
        Graphics2D g2 = (Graphics2D)graphics;
        
        g2.setStroke(new BasicStroke(5));
        graphics.setColor(Player.outlineColor);
        graphics.drawLine(this.leftHand[0], this.leftHand[1], this.rightHand[0], this.rightHand[1]);
    }
}
