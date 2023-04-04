package Entities.Tools;

import java.awt.Graphics;

public class Tool {
    // Right in to(OL)

    public enum TOOL_RARITY {
        STONE,
        CERAMIC,
        IRON,
        GOLD,
        DIAMOND,
        EMERALD,
        AMETHYST,
        RUBY
    };

    public Tool(TOOL_RARITY toolRarity) {
        this.toolRarity = toolRarity;
    }
    
    public int[] getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(int[] leftHand) {
        this.leftHand = leftHand;
    }

    public int[] getRightHand() {
        return rightHand;
    }

    public void setRightHand(int[] rightHand) {
        this.rightHand = rightHand;
    }

    void onRender(Graphics graphics) {};

    protected int[] leftHand, rightHand;
    protected TOOL_RARITY toolRarity;
}
