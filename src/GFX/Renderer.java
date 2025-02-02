package GFX;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Game.Map;
import Util.KeyboardHandler;

public class Renderer extends JFrame {
    private static class Panel extends JPanel {

        /*
         * An override of the component method that draws the window for the game.
         *
         * @note see Oracle documentation for information regarding this method.
         */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            this.setBackground(Color.green);

            curMap.drawMap(g);

            g.dispose();
        }

        private Map curMap = new Map();
    }

    /*
     * Starts the window for drawing the game.
     */
    public Renderer() {
        m_Panel.setDoubleBuffered(false);
        m_Panel.setPreferredSize(new Dimension(windowWidth, windowHeight));

        this.setTitle(m_WindowName);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLocation(new Point(0, 0));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(false);
        this.add(m_Panel);
        this.pack();
        this.setVisible(true);
        this.addKeyListener(KeyboardHandler.getListener());
    }

    /*
     * The logic handler for the game.
     */
    public void handleGame() {
        while (true) {
            // update values for the keyboard handler
            KeyboardHandler.update();

            // update the width for the window
            windowWidth = this.getContentPane().getWidth();
            windowHeight = this.getContentPane().getHeight();
            
            // handle camera updates
            Camera.handleCamera();

            // draw to the screen
            this.repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException | IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getWindowWidth() {
        return windowWidth;
    }

    public static int getWindowHeight() {
        return windowHeight;
    }

    private static int windowWidth = 800, windowHeight = 600;
    private static final Panel m_Panel = new Panel();
    private static final String m_WindowName = "Final Project | github.com/notSam25/";
}